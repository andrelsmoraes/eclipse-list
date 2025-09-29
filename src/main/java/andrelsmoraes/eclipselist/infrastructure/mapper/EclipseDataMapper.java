package andrelsmoraes.eclipselist.infrastructure.mapper;

import andrelsmoraes.eclipselist.infrastructure.entity.EclipseEntity;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class EclipseDataMapper {

    public Eclipse toModel(EclipseEntity entity) {
        LocalDate date = LocalDate.parse(entity.getDate(), DateTimeFormatter.ISO_DATE);
        return new Eclipse(UUID.fromString(entity.getId()), date);
    }

    public EclipseEntity toEntity(Eclipse eclipse) {
        String formattedDate = eclipse.date().format(DateTimeFormatter.ISO_DATE);
        return new EclipseEntity(eclipse.id().toString(), formattedDate);
    }
}
