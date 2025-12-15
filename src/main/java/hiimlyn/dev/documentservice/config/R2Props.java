package hiimlyn.dev.documentservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "cloudflare.r2")
@Configuration
@Getter
@Setter
public class R2Props {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
}
