package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import andrelsmoraes.eclipselist.infrastructure.repository.EclipseElasticsearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the use case for listing Eclipse entries by Region.
 *
 * @param elasticsearchRepository the repository for managing Eclipse entries in Elasticsearch
 * @param eclipseDataMapper the mapper for converting data entities to domain models
 * @param typeMapper the mapper for handling type conversions
 */
public record ListEclipsesByRegionUseCaseImpl(
        EclipseElasticsearchRepository elasticsearchRepository,
        EclipseDataMapper eclipseDataMapper,
        TypeMapper typeMapper
) implements ListEclipsesByRegionUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ListEclipsesByRegionUseCaseImpl.class);

    @Override
    public List<Eclipse> execute(UUID regionId) {
        logger.info("Listing eclipse entries for Region ID: {}", regionId);
        return elasticsearchRepository.findByRegionIds(regionId.toString())
                .stream()
                .map(eclipseDataMapper::toModel)
                .toList();
    }
}
