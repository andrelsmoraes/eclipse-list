package andrelsmoraes.eclipselist.application.usecase.region;

import java.util.UUID;

/**
 * Use case interface for deleting a Region entry by its ID.
 */
public interface DeleteRegionByIdUseCase {

    void execute(UUID id);
}
