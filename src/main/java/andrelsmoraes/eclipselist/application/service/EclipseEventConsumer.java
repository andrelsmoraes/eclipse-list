package andrelsmoraes.eclipselist.application.service;

import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import andrelsmoraes.eclipselist.infrastructure.repository.EclipseElasticsearchRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public record EclipseEventConsumer(
    EclipseElasticsearchRepository elasticEclipseRepository,
    EclipseDataMapper eclipseDataMapper
) {

    @KafkaListener(topics = "eclipse-creation", groupId = "eclipse-consumer-group")
    public void handleEclipseCreationEvent(String message) {
        try {
            elasticEclipseRepository.save(eclipseDataMapper.toDocument(message));
        } catch (Exception e) {
            throw new RuntimeException("Error saving eclipse to Elasticsearch.", e);
        }
    }

    @KafkaListener(topics = "eclipse-deletion", groupId = "eclipse-consumer-group")
    public void handleEclipseDeletionEvent(String eclipseId) {
        try {
            elasticEclipseRepository.deleteById(eclipseId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting eclipse from Elasticsearch with id: " + eclipseId, e);
        }
    }
}
