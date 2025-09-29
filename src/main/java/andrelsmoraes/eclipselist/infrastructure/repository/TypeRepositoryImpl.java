package andrelsmoraes.eclipselist.infrastructure.repository;

import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.domain.repository.TypeRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the TypeRepository interface.
 * This class provides methods to interact with the Type data.
 */
@Repository
public class TypeRepositoryImpl implements TypeRepository {

    /**
     * Lists all available types.
     *
     * @return a list of all types
     */
    @Override
    public List<Type> listAll() {
        return Arrays.stream(Type.values()).toList();
    }
}
