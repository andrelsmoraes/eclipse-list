package andrelsmoraes.eclipselist.application.service;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service responsible for producing events related to Eclipse entities.
 * It sends messages to Kafka topics when an Eclipse is created or deleted.
 */
@Service
public record EclipseEventProducer(
        KafkaTemplate<String, String> kafkaTemplate,
        EclipseDataMapper eclipseDataMapper
) {

    private static final Logger logger = LoggerFactory.getLogger(EclipseEventProducer.class);

    /**
     * Sends a creation event for the given Eclipse to the "eclipse-creation" Kafka topic.
     *
     * @param eclipse The Eclipse entity that was created.
     */
    public void sendEclipseCreationEvent(Eclipse eclipse) {
        logger.info("Sending eclipse creation event for Eclipse ID: {}", eclipse.id());
        kafkaTemplate.send("eclipse-creation", eclipseDataMapper.toString(eclipse));
    }

    /**
     * Sends a deletion event for the Eclipse with the given ID to the "eclipse-deletion" Kafka topic.
     *
     * @param eclipseId The ID of the Eclipse entity that was deleted.
     */
    public void sendEclipseDeletionEvent(UUID eclipseId) {
        logger.info("Sending eclipse deletion event for Eclipse ID: {}", eclipseId);
        kafkaTemplate.send("eclipse-deletion", eclipseId.toString());
    }
}
