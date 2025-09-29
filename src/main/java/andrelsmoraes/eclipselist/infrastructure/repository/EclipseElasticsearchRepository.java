package andrelsmoraes.eclipselist.infrastructure.repository;

import andrelsmoraes.eclipselist.infrastructure.document.EclipseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EclipseElasticsearchRepository extends ElasticsearchRepository<EclipseDocument, String> {

    List<EclipseDocument> findByRegionIds(String regionId);

    List<EclipseDocument> findByType(String type);
}
