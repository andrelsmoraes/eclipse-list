package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import andrelsmoraes.eclipselist.infrastructure.repository.EclipseElasticsearchRepository;

import java.util.List;

public record ListEclipsesByTypeUseCaseImpl(
        EclipseElasticsearchRepository elasticsearchRepository,
        EclipseDataMapper eclipseDataMapper,
        TypeMapper typeMapper
) implements ListEclipsesByTypeUseCase {

    @Override
    public List<Eclipse> execute(Type type) {
        return elasticsearchRepository.findByType(typeMapper.toString(type))
                .stream()
                .map(eclipseDataMapper::toModel)
                .toList();
    }
}
