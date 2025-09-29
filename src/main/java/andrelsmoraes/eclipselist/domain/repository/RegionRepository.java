package andrelsmoraes.eclipselist.domain.repository;

import andrelsmoraes.eclipselist.domain.model.Region;

import java.util.List;

/**
 * Repository interface for managing Region entities.
 */
public interface RegionRepository {

    /**
     * Saves a Region entity.
     *
     * @param region the Region entity to save
     */
    void save(Region region);

    /**
     * Lists all Region entities.
     *
     * @return a list of all Region entities
     */
    List<Region> listAll();

    /**
     * Deletes a Region entity by its ID.
     *
     * @param id the ID of the Region entity to delete
     */
    void deleteById(String id);
}
