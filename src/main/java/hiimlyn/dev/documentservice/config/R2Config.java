package hiimlyn.dev.documentservice.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
@AllArgsConstructor
public class R2Config {
	private final R2Props props;

	@Bean
	public S3Client r2Client() {
		S3Configuration serviceConfig = S3Configuration.builder()
				// path-style is required for R2
				.pathStyleAccessEnabled(true)
				// disable AWS4 chunked uploads
				.chunkedEncodingEnabled(false)
				.build();

		return S3Client.builder()
				.region(Region.of("auto"))
				.endpointOverride(URI.create(props.getEndpoint()))
				.credentialsProvider(StaticCredentialsProvider.create(
						AwsBasicCredentials.create(
								props.getAccessKey(),
								props.getSecretKey())))
				.serviceConfiguration(serviceConfig)
				.build();
	}
}
