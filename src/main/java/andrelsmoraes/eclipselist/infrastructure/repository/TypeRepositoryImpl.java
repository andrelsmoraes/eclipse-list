package andrelsmoraes.eclipselist.infrastructure.repository;

import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.domain.repository.TypeRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class TypeRepositoryImpl implements TypeRepository {

    @Override
    public List<Type> listAll() {
        return Arrays.stream(Type.values()).toList();
    }
}
