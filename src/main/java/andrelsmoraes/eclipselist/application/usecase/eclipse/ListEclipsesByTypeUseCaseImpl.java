package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;

import java.util.List;

public record ListEclipsesByTypeUseCaseImpl(EclipseRepository eclipseRepository) implements ListEclipsesByTypeUseCase {

    @Override
    public List<Eclipse> execute(Type type) {
        return eclipseRepository.findByType(type);
    }
}
