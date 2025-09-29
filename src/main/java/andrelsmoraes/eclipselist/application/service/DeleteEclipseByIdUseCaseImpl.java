package andrelsmoraes.eclipselist.application.service;

import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;

import java.util.UUID;

public record DeleteEclipseByIdUseCaseImpl(EclipseRepository eclipseRepository) implements DeleteEclipseByIdUseCase {

    @Override
    public void execute(UUID id) {
        eclipseRepository.deleteById(id.toString());
    }
}
