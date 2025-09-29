package andrelsmoraes.eclipselist.application.usecase.region;

import andrelsmoraes.eclipselist.application.usecase.eclipse.ListEclipsesByTypeUseCaseImpl;
import andrelsmoraes.eclipselist.domain.model.Region;
import andrelsmoraes.eclipselist.domain.repository.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Implementation of the use case for creating a new Region entry.
 *
 * @param regionRepository the repository for managing Region entries
 */
public record CreateRegionUseCaseImpl(RegionRepository regionRepository) implements CreateRegionUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateRegionUseCaseImpl.class);

    /**
     * Creates a new Region entry. If the provided Region does not have an ID, a new UUID is generated.
     * The Region is then saved to the repository.
     *
     * @param region the Region to be created
     * @return the created Region with an assigned ID, if it was not provided
     */
    @Override
    public Region execute(Region region) {
        logger.info("Creating new region entry: {}", region);
        Region result = region.copy(
                region.id() == null ? UUID.randomUUID() : region.id(),
                region.name()
        ) ;
        regionRepository.save(result);
        return result;
    }
}
