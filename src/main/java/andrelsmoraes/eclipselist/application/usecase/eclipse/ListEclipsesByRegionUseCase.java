package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;

import java.util.List;
import java.util.UUID;

/**
 * Use case interface for listing Eclipse entries by Region.
 */
public interface ListEclipsesByRegionUseCase {

    List<Eclipse> execute(UUID regionId);
}
