package andrelsmoraes.eclipselist.application.usecase.type;

import andrelsmoraes.eclipselist.application.usecase.region.ListRegionUseCaseImpl;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.domain.repository.TypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Implementation of the use case for listing all Type entries.
 *
 * @param typeRepository the repository for managing Type entries
 */
public record ListTypeUseCaseImpl(TypeRepository typeRepository) implements ListTypeUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ListTypeUseCaseImpl.class);

    @Override
    public List<Type> execute() {
        logger.info("Listing all type entries");
        return typeRepository.listAll();
    }
}
