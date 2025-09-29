package andrelsmoraes.eclipselist.infrastructure.repository;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.infrastructure.entity.EclipseEntity;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EclipseRepositoryTest {

    @Mock
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    @Mock
    private DynamoDbTable<EclipseEntity> dynamoDbTable;

    @Mock
    private PageIterable<EclipseEntity> pageIterable;

    @Mock
    private EclipseDataMapper eclipseMapper;

    private EclipseRepositoryImpl eclipseRepository;

    @BeforeEach
    void setUp() {
        when(dynamoDbEnhancedClient.table(anyString(), any(TableSchema.class)))
                .thenReturn(dynamoDbTable);
        eclipseRepository = new EclipseRepositoryImpl(dynamoDbEnhancedClient, eclipseMapper);
    }

    @Test
    void saveShouldPersistEclipseEntity() {
        UUID id = UUID.randomUUID();
        LocalDate date = LocalDate.of(2025, 4, 8);

        Eclipse eclipse = new Eclipse(id, date, Type.SOLAR_TOTAL, new ArrayList<>());
        EclipseEntity eclipseEntity = new EclipseEntity(
                id.toString(),
                "20025-04-08",
                Type.SOLAR_TOTAL.name(),
                new ArrayList<>()
        );

        when(eclipseMapper.toEntity(eclipse))
                .thenReturn(eclipseEntity);

        eclipseRepository.save(eclipse);

        verify(dynamoDbTable).putItem(eclipseEntity);
    }

    @Test
    void listAllShouldReturnMappedEclipseList() {
        UUID id = UUID.randomUUID();
        LocalDate date = LocalDate.of(2025, 4, 8);

        Eclipse eclipse = new Eclipse(id, date, Type.SOLAR_TOTAL, new ArrayList<>());
        EclipseEntity eclipseEntity = new EclipseEntity(
                id.toString(),
                "20025-04-08",
                Type.SOLAR_TOTAL.name(),
                new ArrayList<>()
        );

        SdkIterable<EclipseEntity> sdkIterable = () -> List.of(eclipseEntity).iterator();

        when(dynamoDbTable.scan()).thenReturn(pageIterable);
        when(pageIterable.items()).thenReturn(sdkIterable);
        when(eclipseMapper.toModel(eclipseEntity)).thenReturn(eclipse);

        List<Eclipse> result = eclipseRepository.listAll();

        assertEquals(1, result.size());
        assertEquals(eclipse, result.getFirst());
    }

    @Test
    void deleteByIdShouldRemoveEclipseEntity() {
        UUID id = UUID.randomUUID();

        eclipseRepository.deleteById(id.toString());

        verify(dynamoDbTable).deleteItem(any(Consumer.class));
    }

    @Test
    void listAllShouldReturnEmptyListWhenNoItemsExist() {
        SdkIterable<EclipseEntity> sdkIterable = Collections::emptyIterator;

        when(dynamoDbTable.scan()).thenReturn(pageIterable);
        when(pageIterable.items()).thenReturn(sdkIterable);

        List<Eclipse> result = eclipseRepository.listAll();

        assertTrue(result.isEmpty());
    }
}
