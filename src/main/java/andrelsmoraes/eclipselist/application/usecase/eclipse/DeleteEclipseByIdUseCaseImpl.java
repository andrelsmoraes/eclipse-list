package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.application.service.EclipseEventProducer;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Implementation of the use case for deleting an Eclipse entry by its ID.
 *
 * @param eclipseRepository the repository for managing Eclipse entries
 * @param eventProducer the event producer for sending deletion events
 */
public record DeleteEclipseByIdUseCaseImpl(
        EclipseRepository eclipseRepository,
        EclipseEventProducer eventProducer
) implements DeleteEclipseByIdUseCase {

    private static final Logger logger = LoggerFactory.getLogger(DeleteEclipseByIdUseCaseImpl.class);

    @Override
    public void execute(UUID id) {
        logger.info("Deleting eclipse entry with ID: {}", id);
        eclipseRepository.deleteById(id.toString());
        eventProducer.sendEclipseDeletionEvent(id);
    }
}
