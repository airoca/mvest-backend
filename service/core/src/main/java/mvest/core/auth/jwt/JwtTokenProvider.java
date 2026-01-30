package mvest.core.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import mvest.core.auth.constant.AuthConstant;
import mvest.core.auth.dto.ClaimDTO;
import mvest.core.auth.dto.response.JwtTokenDTO;
import mvest.core.global.code.AuthErrorCode;
import mvest.core.global.exception.AuthException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider implements InitializingBean {

    private static final String ANONYMOUS_USER = "anonymous";

    @Value("${jwt.user.access_token_expiration_time}")
    private Long accessTokenExpirationTime;
    @Value("${jwt.user.refresh_token_expiration_time}")
    private Long refreshTokenExpirationTime;
    @Value("${jwt.user.secret}")
    private String secretKey;

    private Key singingKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.singingKey = Keys.hmacShaKeyFor(encodedKey.getBytes());
    }

    public JwtTokenDTO generateTokenPair(Long userId) {
        return JwtTokenDTO.of(
                generateToken(userId, true),
                generateToken(userId, false)
        );
    }

    public String generateToken(Long userId, boolean isAccessToken) {
        final Date now = new Date();
        final Date expirationDate = generateExpirationDate(now, isAccessToken);
        final Claims claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(expirationDate);

        claims.put(AuthConstant.USER_ID, userId);
        claims.put(AuthConstant.TOKEN_TYPE, isAccessToken);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(singingKey)
                .compact();
    }

    private Date generateExpirationDate(Date now, boolean isAccessToken) {
        if (isAccessToken) {
            return new Date(now.getTime() + accessTokenExpirationTime);
        }
        return new Date(now.getTime() + refreshTokenExpirationTime);
    }

    public Claims getClaims(final String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(singingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }
    }

    public ClaimDTO getClaimFromToken(String token) {
        Claims claims = getClaims(token);
        return ClaimDTO.of(
                Long.valueOf(claims.get(AuthConstant.USER_ID).toString()),
                Boolean.parseBoolean(claims.get(AuthConstant.TOKEN_TYPE).toString())
        );
    }

    public static Object validatePrincipal(final Object principal) {
        if (ANONYMOUS_USER.equals(principal)) {
            throw new AuthException(AuthErrorCode.UNAUTHORIZED);
        }
        return principal;
    }
}
