package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;

import java.util.List;
import java.util.UUID;

public record ListEclipsesByRegionUseCaseImpl(EclipseRepository eclipseRepository) implements ListEclipsesByRegionUseCase {

    @Override
    public List<Eclipse> execute(UUID regionId) {
        return eclipseRepository.findByRegionIds(regionId.toString());
    }
}
