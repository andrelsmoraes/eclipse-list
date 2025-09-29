package andrelsmoraes.eclipselist.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

/**
 * Configuration class for setting up the DynamoDB client.
 */
@Configuration
public class DynamoDbConfig {

    @Value("${dynamo.region}")
    private String region;

    @Value("${dynamo.endpoint}")
    private String endpoint;

    @Value("${dynamo.access-key}")
    private String accessKey;

    @Value("${dynamo.secret-key}")
    private String secretKey;

    @Bean
    public DynamoDbClient getDynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                ))
                .build();
    }
}
