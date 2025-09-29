package andrelsmoraes.eclipselist.application.usecase.region;

import andrelsmoraes.eclipselist.domain.repository.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Implementation of the use case for deleting a Region entry by its ID.
 *
 * @param regionRepository the repository for managing Region entries
 */
public record DeleteRegionByIdUseCaseImpl(RegionRepository regionRepository) implements DeleteRegionByIdUseCase {

    private static final Logger logger = LoggerFactory.getLogger(DeleteRegionByIdUseCaseImpl.class);

    @Override
    public void execute(UUID id) {
        logger.info("Deleting region entry with ID: {}", id);
        regionRepository.deleteById(id.toString());
    }
}
