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

/**
 * Mapper class for converting between Eclipse domain model, entity, document, and JSON string representations.
 */
@Component
public record EclipseDataMapper(ObjectMapper objectMapper) {

    /**
     * Converts an Eclipse entity to an Eclipse domain model.
     * It is used to convert data from the database representation to the domain model.
     *
     * @param entity the EclipseEntity to convert
     * @return the corresponding Eclipse domain model
     */
    public Eclipse toModel(EclipseEntity entity) {
        LocalDate date = LocalDate.parse(entity.getDate(), DateTimeFormatter.ISO_DATE);
        return new Eclipse(
                UUID.fromString(entity.getId()),
                date,
                Type.valueOf(entity.getType()),
                entity.getRegionIds().stream().map(UUID::fromString).toList()
        );
    }

    /**
     * Converts an Eclipse document to an Eclipse domain model.
     * It is used to convert data from the search engine representation to the domain model.
     *
     * @param document the EclipseDocument to convert
     * @return the corresponding Eclipse domain model
     */
    public Eclipse toModel(EclipseDocument document) {
        return new Eclipse(
                UUID.fromString(document.getId()),
                LocalDate.parse(document.getDate(), DateTimeFormatter.ISO_DATE),
                Type.valueOf(document.getType()),
                document.getRegionIds().stream().map(UUID::fromString).toList()
        );
    }

    /**
     * Converts a JSON string representation of an Eclipse to an Eclipse domain model.
     * It is used to convert data from external sources (like files or APIs) to the domain model.
     *
     * @param serialized the JSON string to convert
     * @return the corresponding Eclipse domain model
     */
    public Eclipse toModel(String serialized) {
        try {
            return objectMapper.readValue(serialized, Eclipse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing eclipse object.", e);
        }
    }

    /**
     * Converts an Eclipse domain model to an Eclipse entity.
     * It is used to convert data from the domain model to the database representation.
     *
     * @param eclipse the Eclipse domain model to convert
     * @return the corresponding EclipseEntity
     */
    public EclipseEntity toEntity(Eclipse eclipse) {
        String formattedDate = eclipse.date().format(DateTimeFormatter.ISO_DATE);
        return new EclipseEntity(
                eclipse.id().toString(),
                formattedDate,
                eclipse.type().name(),
                eclipse.regionIds().stream().map(UUID::toString).toList()
        );
    }

    /**
     * Converts an Eclipse domain model to an Eclipse document.
     * It is used to convert data from the domain model to the search engine representation.
     *
     * @param eclipse the Eclipse domain model to convert
     * @return the corresponding EclipseDocument
     */
    public EclipseDocument toDocument(Eclipse eclipse) {
        String formattedDate = eclipse.date().format(DateTimeFormatter.ISO_DATE);
        return new EclipseDocument(
                eclipse.id().toString(),
                formattedDate,
                eclipse.type().name(),
                eclipse.regionIds().stream().map(UUID::toString).toList()
        );
    }

    /**
     * Converts a JSON string representation of an Eclipse to an Eclipse document.
     * It is used to convert data from external sources (like files or APIs) to the search engine representation.
     *
     * @param serialized the JSON string to convert
     * @return the corresponding EclipseDocument
     */
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

    /**
     * Converts an Eclipse domain model to its JSON string representation.
     * It is used to convert data from the domain model to a format suitable for external sources (like files or APIs).
     *
     * @param eclipse the Eclipse domain model to convert
     * @return the JSON string representation of the Eclipse
     */
    public String toString(Eclipse eclipse) {
        try {
            return objectMapper.writeValueAsString(eclipse);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing eclipse object.", e);
        }
    }
}
