package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import andrelsmoraes.eclipselist.infrastructure.repository.EclipseElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public record ListEclipsesByRegionUseCaseImpl(
        EclipseElasticsearchRepository elasticsearchRepository,
        EclipseDataMapper eclipseDataMapper,
        TypeMapper typeMapper
) implements ListEclipsesByRegionUseCase {

    @Override
    public List<Eclipse> execute(UUID regionId) {
        return elasticsearchRepository.findByRegionIds(regionId.toString())
                .stream()
                .map(eclipseDataMapper::toModel)
                .toList();
    }
}
