package andrelsmoraes.eclipselist.infrastructure.mapper;

import andrelsmoraes.eclipselist.domain.model.Region;
import andrelsmoraes.eclipselist.infrastructure.entity.RegionEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegionDataMapper {

    public Region toModel(RegionEntity entity) {
        return new Region(UUID.fromString(entity.getId()), entity.getName());
    }

    public RegionEntity toEntity(Region region) {
        return new RegionEntity(region.id().toString(), region.name());
    }
}
