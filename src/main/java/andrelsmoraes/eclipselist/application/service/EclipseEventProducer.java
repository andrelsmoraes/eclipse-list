package andrelsmoraes.eclipselist.application.service;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record EclipseEventProducer(
        KafkaTemplate<String, String> kafkaTemplate,
        EclipseDataMapper eclipseDataMapper
) {

    public void sendEclipseCreationEvent(Eclipse eclipse) {
        kafkaTemplate.send("eclipse-creation", eclipseDataMapper.toString(eclipse));
    }

    public void sendEclipseDeletionEvent(UUID eclipseId) {
        kafkaTemplate.send("eclipse-deletion", eclipseId.toString());
    }
}
