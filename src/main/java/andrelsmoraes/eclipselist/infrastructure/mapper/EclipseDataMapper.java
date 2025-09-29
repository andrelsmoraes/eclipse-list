package andrelsmoraes.eclipselist.infrastructure.mapper;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.infrastructure.document.EclipseDocument;
import andrelsmoraes.eclipselist.infrastructure.entity.EclipseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public record EclipseDataMapper(ObjectMapper objectMapper) {

    public Eclipse toModel(EclipseEntity entity) {
        LocalDate date = LocalDate.parse(entity.getDate(), DateTimeFormatter.ISO_DATE);
        return new Eclipse(
                UUID.fromString(entity.getId()),
                date,
                Type.valueOf(entity.getType()),
                entity.getRegionIds().stream().map(UUID::fromString).toList()
        );
    }

    public Eclipse toModel(EclipseDocument document) {
        return new Eclipse(
                UUID.fromString(document.getId()),
                LocalDate.parse(document.getDate(), DateTimeFormatter.ISO_DATE),
                Type.valueOf(document.getType()),
                document.getRegionIds().stream().map(UUID::fromString).toList()
        );
    }

    public Eclipse toModel(String serialized) {
        try {
            return objectMapper.readValue(serialized, Eclipse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing eclipse object.", e);
        }
    }

    public EclipseEntity toEntity(Eclipse eclipse) {
        String formattedDate = eclipse.date().format(DateTimeFormatter.ISO_DATE);
        return new EclipseEntity(
                eclipse.id().toString(),
                formattedDate,
                eclipse.type().name(),
                eclipse.regionIds().stream().map(UUID::toString).toList()
        );
    }

    public EclipseDocument toDocument(Eclipse eclipse) {
        String formattedDate = eclipse.date().format(DateTimeFormatter.ISO_DATE);
        return new EclipseDocument(
                eclipse.id().toString(),
                formattedDate,
                eclipse.type().name(),
                eclipse.regionIds().stream().map(UUID::toString).toList()
        );
    }

    public EclipseDocument toDocument(String serialized) {
        Eclipse eclipse = toModel(serialized);
        String formattedDate = eclipse.date().format(DateTimeFormatter.ISO_DATE);
        return new EclipseDocument(
                eclipse.id().toString(),
                formattedDate,
                eclipse.type().name(),
                eclipse.regionIds().stream().map(UUID::toString).toList()
        );
    }

    public String toString(Eclipse eclipse) {
        try {
            return objectMapper.writeValueAsString(eclipse);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing eclipse object.", e);
        }
    }
}
