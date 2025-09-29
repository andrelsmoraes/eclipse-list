package andrelsmoraes.eclipselist.application.usecase.region;

import andrelsmoraes.eclipselist.domain.model.Region;

import java.util.List;

/**
 * Use case interface for listing all Region entries.
 */
public interface ListRegionUseCase {

    List<Region> execute();
}
