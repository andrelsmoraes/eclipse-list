package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Implementation of the use case for listing all Eclipse entries.
 *
 * @param eclipseRepository the repository for managing Eclipse entries
 */
public record ListAllEclipsesUseCaseImpl(EclipseRepository eclipseRepository) implements ListAllEclipsesUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ListAllEclipsesUseCaseImpl.class);

    @Override
    public List<Eclipse> execute() {
        logger.info("Listing all eclipse entries");
        return eclipseRepository.listAll();
    }
}
