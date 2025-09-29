package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;

import java.util.List;

public record ListEclipseUseCaseImpl(EclipseRepository eclipseRepository) implements ListEclipseUseCase {

    @Override
    public List<Eclipse> execute() {
        return eclipseRepository.listAll();
    }
}
