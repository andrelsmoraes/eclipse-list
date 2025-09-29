package andrelsmoraes.eclipselist.domain.repository;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;

import java.util.List;
import java.util.UUID;

public interface EclipseRepository {
    void save(Eclipse eclipse);

    List<Eclipse> listAll();

    void deleteById(String id);

    List<Eclipse> findByRegionIds(UUID regionId);

    List<Eclipse> findByType(Type type);
}
