package andrelsmoraes.eclipselist.api.mapper;

import andrelsmoraes.eclipselist.api.dto.EclipseDto;
import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import org.springframework.stereotype.Component;

@Component
public class EclipsePresentationMapper {

    private final TypeMapper typeMapper;

    public EclipsePresentationMapper(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    public Eclipse toModel(EclipseDto dto) {
        return new Eclipse(dto.id(), dto.date(), typeMapper.toModel(dto.type()), dto.regionIds());
    }

    public EclipseDto toDto(Eclipse eclipse) {
        return new EclipseDto(eclipse.id(), eclipse.date(), typeMapper.toString(eclipse.type()), eclipse.regionIds());
    }
}
