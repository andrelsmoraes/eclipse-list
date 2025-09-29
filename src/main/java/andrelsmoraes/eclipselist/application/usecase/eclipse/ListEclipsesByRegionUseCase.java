package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;

import java.util.List;
import java.util.UUID;

public interface ListEclipsesByRegionUseCase {

    List<Eclipse> execute(UUID regionId);
}
