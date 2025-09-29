package andrelsmoraes.eclipselist.infrastructure.repository;

import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import andrelsmoraes.eclipselist.infrastructure.entity.EclipseEntity;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.UUID;

@Repository
public class EclipseRepositoryImpl implements EclipseRepository {

    private final DynamoDbTable<EclipseEntity> dynamoDbTable;
    private final EclipseElasticsearchRepository elasticsearchRepository;
    private final EclipseDataMapper eclipseMapper;
    private final TypeMapper typeMapper;

    public EclipseRepositoryImpl(
            DynamoDbEnhancedClient dynamoDbEnhancedClient,
            EclipseElasticsearchRepository elasticsearchRepository,
            EclipseDataMapper eclipseMapper,
            TypeMapper typeMapper
    ) {
        this.dynamoDbTable = dynamoDbEnhancedClient.table("Eclipse", TableSchema.fromBean(EclipseEntity.class));
        this.elasticsearchRepository = elasticsearchRepository;
        this.eclipseMapper = eclipseMapper;
        this.typeMapper = typeMapper;
    }

    @Override
    public void save(Eclipse eclipse) {
        dynamoDbTable.putItem(eclipseMapper.toEntity(eclipse));
        elasticsearchRepository.save(eclipseMapper.toElasticsearch(eclipse));
    }

    @Override
    public List<Eclipse> listAll() {
        return dynamoDbTable.scan()
                .items()
                .stream()
                .map(eclipseMapper::toModel)
                .toList();
    }

    @Override
    public void deleteById(String id) {
        dynamoDbTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
        elasticsearchRepository.deleteById(id);
    }

    @Override
    public List<Eclipse> findByRegionIds(UUID regionId) {
        return elasticsearchRepository.findByRegionIds(regionId.toString())
                .stream().map(eclipseMapper::toModel)
                .toList();
    }

    @Override
    public List<Eclipse> findByType(Type type) {
        return elasticsearchRepository.findByType(typeMapper.toString(type))
                .stream().map(eclipseMapper::toModel)
                .toList();
    }
}
