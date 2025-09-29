package andrelsmoraes.eclipselist.infrastructure.datasource;

import andrelsmoraes.eclipselist.domain.model.Eclipse;

import java.util.List;

public interface EclipseFileDatasource {

    List<Eclipse> listAll();
}
