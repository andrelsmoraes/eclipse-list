package andrelsmoraes.eclipselist.domain.repository;

import andrelsmoraes.eclipselist.domain.model.Region;

import java.util.List;

public interface RegionRepository {
    void save(Region region);

    List<Region> listAll();

    void deleteById(String id);
}
