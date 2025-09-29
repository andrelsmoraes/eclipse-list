package andrelsmoraes.eclipselist.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic getEclipseCreationTopic() {
        return TopicBuilder.name("eclipse-creation")  // Topic for creation
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic getEclipseDeletionTopic() {
        return TopicBuilder.name("eclipse-deletion")  // Topic for deletion
                .partitions(1)
                .replicas(1)
                .build();
    }
}
