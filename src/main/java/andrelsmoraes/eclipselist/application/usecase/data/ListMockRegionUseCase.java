package andrelsmoraes.eclipselist.application.usecase.data;

import andrelsmoraes.eclipselist.domain.model.Region;

import java.util.List;

/**
 * Use case interface for listing mock Region data.
 */
public interface ListMockRegionUseCase {

    List<Region> execute();
}
