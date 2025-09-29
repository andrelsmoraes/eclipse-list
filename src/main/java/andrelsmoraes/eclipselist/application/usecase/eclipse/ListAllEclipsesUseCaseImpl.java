package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;

import java.util.List;

public record ListAllEclipsesUseCaseImpl(EclipseRepository eclipseRepository) implements ListAllEclipsesUseCase {

    @Override
    public List<Eclipse> execute() {
        return eclipseRepository.listAll();
    }
}
