package andrelsmoraes.eclipselist.application.service;

import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import andrelsmoraes.eclipselist.infrastructure.repository.EclipseElasticsearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service responsible for consuming events related to Eclipse entities.
 * It listens to Kafka topics for creation and deletion events and updates the Elasticsearch repository accordingly.
 */
@Service
public record EclipseEventConsumer(
    EclipseElasticsearchRepository elasticEclipseRepository,
    EclipseDataMapper eclipseDataMapper
) {

    private static final Logger logger = LoggerFactory.getLogger(EclipseEventConsumer.class);

    /**
     * Listens for Eclipse creation events on the "eclipse-creation" Kafka topic.
     * When a message is received, it converts the message to an Eclipse document and saves it to Elasticsearch.
     *
     * @param message The message containing the Eclipse data in string format.
     */
    @KafkaListener(topics = "eclipse-creation", groupId = "eclipse-consumer-group")
    public void handleEclipseCreationEvent(String message) {
        try {
            logger.info("Received eclipse creation event. Saving it to Elasticsearch with: {}", message);
            elasticEclipseRepository.save(eclipseDataMapper.toDocument(message));
        } catch (Exception e) {
            throw new RuntimeException("Error saving eclipse to Elasticsearch.", e);
        }
    }

    /**
     * Listens for Eclipse deletion events on the "eclipse-deletion" Kafka topic.
     * When a message is received, it deletes the corresponding Eclipse document from Elasticsearch by its ID.
     *
     * @param eclipseId The ID of the Eclipse to be deleted, in string format.
     */
    @KafkaListener(topics = "eclipse-deletion", groupId = "eclipse-consumer-group")
    public void handleEclipseDeletionEvent(String eclipseId) {
        try {
            logger.info("Received eclipse deletion event. Deleting it from Elasticsearch with id: {}", eclipseId);
            elasticEclipseRepository.deleteById(eclipseId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting eclipse from Elasticsearch with id: " + eclipseId, e);
        }
    }
}
