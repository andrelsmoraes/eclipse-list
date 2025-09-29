package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import andrelsmoraes.eclipselist.infrastructure.repository.EclipseElasticsearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Implementation of the use case for listing Eclipse entries by Type.
 *
 * @param elasticsearchRepository the repository for managing Eclipse entries in Elasticsearch
 * @param eclipseDataMapper the mapper for converting data entities to domain models
 * @param typeMapper the mapper for handling type conversions
 */
public record ListEclipsesByTypeUseCaseImpl(
        EclipseElasticsearchRepository elasticsearchRepository,
        EclipseDataMapper eclipseDataMapper,
        TypeMapper typeMapper
) implements ListEclipsesByTypeUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ListEclipsesByTypeUseCaseImpl.class);

    @Override
    public List<Eclipse> execute(Type type) {
        logger.info("Listing eclipse entries for Type: {}", type);
        return elasticsearchRepository.findByType(typeMapper.toString(type))
                .stream()
                .map(eclipseDataMapper::toModel)
                .toList();
    }
}
