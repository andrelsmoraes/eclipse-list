package andrelsmoraes.eclipselist.application.usecase.region;

import andrelsmoraes.eclipselist.domain.model.Region;

/**
 * Use case interface for creating a new Region entry.
 */
public interface CreateRegionUseCase {

    Region execute(Region region);
}
