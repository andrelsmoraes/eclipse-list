package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.application.service.EclipseEventProducer;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;

import java.util.UUID;

public record CreateEclipseUseCaseImpl(
        EclipseRepository eclipseRepository,
        EclipseEventProducer eventProducer
) implements CreateEclipseUseCase {

    @Override
    public void execute(Eclipse eclipse) {
        eclipseRepository.save(
                eclipse.copy(
                        UUID.randomUUID(),
                        eclipse.date(),
                        eclipse.type(),
                        eclipse.regionIds()
                )
        );
        eventProducer.sendEclipseCreationEvent(eclipse);
    }
}
