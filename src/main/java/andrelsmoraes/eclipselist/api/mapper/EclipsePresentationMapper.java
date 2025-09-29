package andrelsmoraes.eclipselist.api.mapper;

import andrelsmoraes.eclipselist.api.dto.EclipseDto;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import org.springframework.stereotype.Component;

@Component
public class EclipsePresentationMapper {

    public Eclipse toModel(EclipseDto dto) {
        return new Eclipse(dto.id(), dto.date(), dto.type(), dto.regionIds());
    }

    public EclipseDto toDto(Eclipse eclipse) {
        return new EclipseDto(eclipse.id(), eclipse.date(), eclipse.type(), eclipse.regionIds());
    }
}
