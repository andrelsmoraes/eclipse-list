package andrelsmoraes.eclipselist.domain.repository;

import andrelsmoraes.eclipselist.domain.model.Eclipse;

import java.util.List;

/**
 * Repository interface for managing Eclipse entities.
 */
public interface EclipseRepository {

    /**
     * Saves an Eclipse entity.
     *
     * @param eclipse the Eclipse entity to save
     */
    void save(Eclipse eclipse);

    /**
     * Lists all Eclipse entities.
     *
     * @return a list of all Eclipse entities
     */
    List<Eclipse> listAll();

    /**
     * Deletes an Eclipse entity by its ID.
     *
     * @param id the ID of the Eclipse entity to delete
     */
    void deleteById(String id);
}
