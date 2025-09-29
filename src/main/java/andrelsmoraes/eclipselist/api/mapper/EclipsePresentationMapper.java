package andrelsmoraes.eclipselist.api.mapper;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.api.dto.EclipseDto;
import org.springframework.stereotype.Component;

@Component
public class EclipsePresentationMapper {

    public Eclipse toModel(EclipseDto dto) {
        return new Eclipse(dto.id(), dto.date());
    }

    public EclipseDto toDto(Eclipse eclipse) {
        return new EclipseDto(eclipse.id(), eclipse.date());
    }
}
