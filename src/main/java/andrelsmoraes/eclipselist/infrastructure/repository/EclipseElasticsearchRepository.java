package andrelsmoraes.eclipselist.infrastructure.repository;

import andrelsmoraes.eclipselist.infrastructure.document.EclipseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing EclipseDocument entities in Elasticsearch. It provides methods to perform
 * CRUD operations and custom queries on EclipseDocument objects.
 */
@Repository
public interface EclipseElasticsearchRepository extends ElasticsearchRepository<EclipseDocument, String> {

    /**
     * Finds a list of EclipseDocument entities by their associated region ID.
     *
     * @param regionId The ID of the region to filter EclipseDocument entities.
     * @return A list of EclipseDocument entities associated with the specified region ID.
     */
    List<EclipseDocument> findByRegionIds(String regionId);

    /**
     * Finds a list of EclipseDocument entities by their type.
     *
     * @param type The type of the eclipse to filter EclipseDocument entities.
     * @return A list of EclipseDocument entities of the specified type.
     */
    List<EclipseDocument> findByType(String type);
}
