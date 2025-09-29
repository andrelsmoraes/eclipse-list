package andrelsmoraes.eclipselist.application.usecase.region;

import andrelsmoraes.eclipselist.domain.model.Region;
import andrelsmoraes.eclipselist.domain.repository.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Implementation of the use case for listing all Region entries.
 *
 * @param regionRepository the repository for managing Region entries
 */
public record ListRegionUseCaseImpl(RegionRepository regionRepository) implements ListRegionUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ListRegionUseCaseImpl.class);

    @Override
    public List<Region> execute() {
        logger.info("Listing all region entries");
        return regionRepository.listAll();
    }
}
