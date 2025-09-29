package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.application.service.EclipseEventProducer;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Implementation of the use case for creating a new Eclipse entry.
 * It saves the Eclipse to the repository and produces an event for the creation.
 *
 * @param eclipseRepository the repository for managing Eclipse entries
 * @param eventProducer the event producer for sending creation events
 */
public record CreateEclipseUseCaseImpl(
        EclipseRepository eclipseRepository,
        EclipseEventProducer eventProducer
) implements CreateEclipseUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateEclipseUseCaseImpl.class);

    /**
     * Creates a new Eclipse entry. If the Eclipse does not have an ID, a new UUID is generated.
     * The Eclipse is then saved to the repository and an event is produced.
     *
     * @param eclipse the Eclipse to be created
     * @return the created Eclipse with an assigned ID, if it was not provided
     */
    @Override
    public Eclipse execute(Eclipse eclipse) {
        logger.info("Creating new eclipse entry: {}", eclipse);
        Eclipse result = eclipse.copy(
                eclipse.id() == null ? UUID.randomUUID() : eclipse.id(),
                eclipse.date(),
                eclipse.type(),
                eclipse.regionIds()
        );
        eclipseRepository.save(result);
        eventProducer.sendEclipseCreationEvent(result);
        return result;
    }
}
