package andrelsmoraes.eclipselist.infrastructure.repository;

import andrelsmoraes.eclipselist.infrastructure.entity.EclipseElasticsearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EclipseElasticsearchRepository extends ElasticsearchRepository<EclipseElasticsearch, String> {

    List<EclipseElasticsearch> findByRegionIds(String regionId);
}
