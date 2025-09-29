package andrelsmoraes.eclipselist.infrastructure.repository;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import andrelsmoraes.eclipselist.infrastructure.entity.EclipseEntity;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

/**
 * Implementation of the EclipseRepository interface using DynamoDB as the data store.
 */
@Repository
public class EclipseRepositoryImpl implements EclipseRepository {

    private final DynamoDbTable<EclipseEntity> dynamoDbTable;
    private final EclipseDataMapper eclipseMapper;

    public EclipseRepositoryImpl(
            DynamoDbEnhancedClient dynamoDbEnhancedClient,
            EclipseDataMapper eclipseMapper
    ) {
        this.dynamoDbTable = dynamoDbEnhancedClient.table("Eclipse", TableSchema.fromBean(EclipseEntity.class));
        this.eclipseMapper = eclipseMapper;
    }

    /**
     * Saves an Eclipse entity to the data store.
     *
     * @param eclipse The Eclipse entity to be saved.
     */
    @Override
    public void save(Eclipse eclipse) {
        dynamoDbTable.putItem(eclipseMapper.toEntity(eclipse));
    }

    /**
     * Retrieves all Eclipse entities from the data store.
     *
     * @return A list of all Eclipse entities.
     */
    @Override
    public List<Eclipse> listAll() {
        return dynamoDbTable.scan()
                .items()
                .stream()
                .map(eclipseMapper::toModel)
                .toList();
    }

    /**
     * Deletes an Eclipse entity by its ID.
     *
     * @param id The ID of the Eclipse entity to be deleted.
     */
    @Override
    public void deleteById(String id) {
        dynamoDbTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
