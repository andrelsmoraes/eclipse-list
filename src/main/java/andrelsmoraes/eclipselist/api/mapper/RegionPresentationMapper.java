package andrelsmoraes.eclipselist.api.mapper;

import andrelsmoraes.eclipselist.api.dto.RegionDto;
import andrelsmoraes.eclipselist.domain.model.Region;
import org.springframework.stereotype.Component;

@Component
public class RegionPresentationMapper {

    public Region toModel(RegionDto dto) {
        return new Region(dto.id(), dto.name());
    }

    public RegionDto toDto(Region region) {
        return new RegionDto(region.id(), region.name());
    }
}
