package mvest.core.global.config;

import mvest.core.CoreApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = CoreApplication.class)
public class FeignClientConfig {
}
