package andrelsmoraes.eclipselist.application.service;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;

import java.util.List;
import java.util.UUID;

public record ListEclipseUseCaseImpl(EclipseRepository eclipseRepository) implements ListEclipseUseCase {

    @Override
    public List<Eclipse> execute() {
        return List.of();
    }
}
