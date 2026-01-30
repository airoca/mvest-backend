package mvest.core.auth.dto;

public record PlatformUserDTO(String platformId) {

    public static PlatformUserDTO of(String platformId) {
        return new PlatformUserDTO(platformId);
    }
}
