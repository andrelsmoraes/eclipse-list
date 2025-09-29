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

@Repository
public class EclipseRepositoryImpl implements EclipseRepository {

    private final DynamoDbTable<EclipseEntity> dynamoDbTable;
    private final EclipseDataMapper mapper;

    public EclipseRepositoryImpl(DynamoDbEnhancedClient dynamoDbEnhancedClient, EclipseDataMapper mapper) {
        this.dynamoDbTable = dynamoDbEnhancedClient.table("Eclipse", TableSchema.fromBean(EclipseEntity.class));
        this.mapper = mapper;
    }

    @Override
    public void save(Eclipse eclipse) {
        dynamoDbTable.putItem(mapper.toEntity(eclipse));
    }

    @Override
    public List<Eclipse> listAll() {
        return dynamoDbTable.scan()
                .items()
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public void deleteById(String id) {
        dynamoDbTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
