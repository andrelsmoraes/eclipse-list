package andrelsmoraes.eclipselist.domain.repository;

import andrelsmoraes.eclipselist.domain.model.Type;

import java.util.List;

/**
 * Repository interface for managing Type entities.
 */
public interface TypeRepository {

    /**
     * Lists all Type entities.
     *
     * @return a list of all Type entities
     */
    List<Type> listAll();
}
