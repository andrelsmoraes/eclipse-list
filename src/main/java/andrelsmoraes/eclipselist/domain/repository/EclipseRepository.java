package andrelsmoraes.eclipselist.domain.repository;

import andrelsmoraes.eclipselist.domain.model.Eclipse;

import java.util.List;

public interface EclipseRepository {
    void save(Eclipse eclipse);

    List<Eclipse> listAll();

    void deleteById(String id);
}
