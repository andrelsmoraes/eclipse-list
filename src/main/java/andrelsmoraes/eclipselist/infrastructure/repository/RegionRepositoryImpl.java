package andrelsmoraes.eclipselist.infrastructure.repository;

import andrelsmoraes.eclipselist.domain.model.Region;
import andrelsmoraes.eclipselist.domain.repository.RegionRepository;
import andrelsmoraes.eclipselist.infrastructure.entity.RegionEntity;
import andrelsmoraes.eclipselist.infrastructure.mapper.RegionDataMapper;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

/**
 * Implementation of the RegionRepository interface using DynamoDB as the data store.
 */
@Repository
public class RegionRepositoryImpl implements RegionRepository {

    private final DynamoDbTable<RegionEntity> dynamoDbTable;
    private final RegionDataMapper mapper;

    public RegionRepositoryImpl(DynamoDbEnhancedClient dynamoDbEnhancedClient, RegionDataMapper mapper) {
        this.dynamoDbTable = dynamoDbEnhancedClient.table("Region", TableSchema.fromBean(RegionEntity.class));
        this.mapper = mapper;
    }

    /**
     * Saves a Region entity to the data store.
     *
     * @param region The Region entity to be saved.
     */
    @Override
    public void save(Region region) {
        dynamoDbTable.putItem(mapper.toEntity(region));
    }

    /**
     * Retrieves all Region entities from the data store.
     *
     * @return A list of all Region entities.
     */
    @Override
    public List<Region> listAll() {
        return dynamoDbTable.scan()
                .items()
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    /**
     * Deletes a Region entity by its ID.
     *
     * @param id The ID of the Region entity to be deleted.
     */
    @Override
    public void deleteById(String id) {
        dynamoDbTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
