package andrelsmoraes.eclipselist.infrastructure.datasource;

import andrelsmoraes.eclipselist.domain.model.Region;

import java.util.List;

public interface RegionFileDatasource {

    List<Region> listAll();
}
