package andrelsmoraes.eclipselist.infrastructure.mapper;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.infrastructure.document.EclipseDocument;
import andrelsmoraes.eclipselist.infrastructure.entity.EclipseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EclipseDataMapperTest {

    private static final String VALID_JSON = """
            {
              "id": "88a2634d-da85-4b91-9add-b6b7331b283d",
              "date": "2025-04-08",
              "type": "SOLAR_TOTAL",
              "regionIds": [
                "9eb65e23-8e76-4524-85f5-d570ea3cf3ea",
                "4d777c2e-5e99-49b3-a805-43f8b326f1ae"
              ]
            }
            """;

    private EclipseDataMapper mapper;


    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mapper = new EclipseDataMapper(objectMapper);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2025-04-08", "1999-12-31", "2000-01-01"})
    void toModelShouldConvertEntityToDomainModel(String date) {
        EclipseEntity entity = new EclipseEntity(
                UUID.randomUUID().toString(),
                date,
                Type.SOLAR_TOTAL.name(),
                List.of(UUID.randomUUID().toString())
        );

        Eclipse result = mapper.toModel(entity);

        assertEquals(entity.getId(), result.id().toString());
        assertEquals(LocalDate.parse(date, DateTimeFormatter.ISO_DATE), result.date());
        assertEquals(Type.SOLAR_TOTAL, result.type());
        assertEquals(entity.getRegionIds().size(), result.regionIds().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2025-04-08", "1999-12-31", "2000-01-01"})
    void toEntityShouldConvertDomainModelToEntity(String date) {
        Eclipse eclipse = new Eclipse(
                UUID.randomUUID(),
                LocalDate.parse(date, DateTimeFormatter.ISO_DATE),
                Type.LUNAR_PARTIAL,
                List.of(UUID.randomUUID())
        );

        EclipseEntity result = mapper.toEntity(eclipse);

        assertEquals(eclipse.id().toString(), result.getId());
        assertEquals(date, result.getDate());
        assertEquals(Type.LUNAR_PARTIAL.name(), result.getType());
        assertEquals(eclipse.regionIds().size(), result.getRegionIds().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {VALID_JSON})
    void toModelShouldConvertJsonStringToDomainModel(String json) {
        Eclipse result = mapper.toModel(json);


        assertNotNull(result);
        assertEquals("88a2634d-da85-4b91-9add-b6b7331b283d", result.id().toString());
        assertEquals(LocalDate.of(2025, 4, 8), result.date());
        assertEquals(Type.SOLAR_TOTAL, result.type());
        assertEquals(2, result.regionIds().size());
        assertEquals(UUID.fromString("9eb65e23-8e76-4524-85f5-d570ea3cf3ea"), result.regionIds().get(0));
        assertEquals(UUID.fromString("4d777c2e-5e99-49b3-a805-43f8b326f1ae"), result.regionIds().get(1));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            """
            {
              "id": "88a2634d-da85-4b91-9add-b6b7331b283d",
              "date": "2025-04-42",
              "type": "SOLAR_TOTAL",
              "regionIds": [
                "9eb65e23-8e76-4524-85f5-d570ea3cf3ea",
                "4d777c2e-5e99-49b3-a805-43f8b326f1ae"
              ]
            }
            """
    })
    void toModelShouldThrowExceptionForInvalidJsonDate(String json) {
        assertThrows(RuntimeException.class, () -> mapper.toModel(json));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            """
            {
              "id": "88a2634d-da85-4b91-9add-b6b7331b283d",
              "date": "2025-04-08",
              "type": "SUPERNOVA",
              "regionIds": [
                "9eb65e23-8e76-4524-85f5-d570ea3cf3ea",
                "4d777c2e-5e99-49b3-a805-43f8b326f1ae"
              ]
            }
            """
    })
    void toModelShouldThrowExceptionForInvalidType(String json) {
        assertThrows(RuntimeException.class, () -> mapper.toModel(json));
    }

    @ParameterizedTest
    @ValueSource(strings = {VALID_JSON})
    void toDocumentShouldConvertJsonStringToDocument(String json) {
        EclipseDocument result = mapper.toDocument(json);

        assertNotNull(result);
        assertEquals("88a2634d-da85-4b91-9add-b6b7331b283d", result.getId());
        assertEquals("2025-04-08", result.getDate());
        assertEquals("SOLAR_TOTAL", result.getType());
        assertEquals(2, result.getRegionIds().size());
        assertEquals("9eb65e23-8e76-4524-85f5-d570ea3cf3ea", result.getRegionIds().get(0));
        assertEquals("4d777c2e-5e99-49b3-a805-43f8b326f1ae", result.getRegionIds().get(1));
    }
}
