package andrelsmoraes.eclipselist.infrastructure.mapper;

import andrelsmoraes.eclipselist.domain.model.Region;
import andrelsmoraes.eclipselist.infrastructure.entity.RegionEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Mapper class for converting between Region domain model and entity.
 */
@Component
public class RegionDataMapper {

    /**
     * Converts a Region entity to a Region domain model.
     *
     * @param entity the RegionEntity to convert
     * @return the corresponding Region domain model
     */
    public Region toModel(RegionEntity entity) {
        return new Region(UUID.fromString(entity.getId()), entity.getName());
    }

    /**
     * Converts a Region domain model to a Region entity.
     *
     * @param region the Region domain model to convert
     * @return the corresponding RegionEntity
     */
    public RegionEntity toEntity(Region region) {
        return new RegionEntity(region.id().toString(), region.name());
    }
}
