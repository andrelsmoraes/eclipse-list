package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.application.service.EclipseEventProducer;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;

import java.util.UUID;

public record DeleteEclipseByIdUseCaseImpl(
        EclipseRepository eclipseRepository,
        EclipseEventProducer eventProducer
) implements DeleteEclipseByIdUseCase {

    @Override
    public void execute(UUID id) {
        eclipseRepository.deleteById(id.toString());
        eventProducer.sendEclipseDeletionEvent(id);
    }
}
