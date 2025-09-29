package andrelsmoraes.eclipselist.infrastructure.mapper;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.infrastructure.entity.EclipseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class EclipseDataMapper {

    public Eclipse toModel(EclipseEntity entity) {
        LocalDate date = LocalDate.parse(entity.getDate(), DateTimeFormatter.ISO_DATE);
        return new Eclipse(
                UUID.fromString(entity.getId()),
                date,
                Type.valueOf(entity.getType()),
                entity.getRegionIds().stream().map(UUID::fromString).toList()
        );
    }

    public EclipseEntity toEntity(Eclipse eclipse) {
        String formattedDate = eclipse.date().format(DateTimeFormatter.ISO_DATE);
        return new EclipseEntity(
                eclipse.id().toString(),
                formattedDate,
                eclipse.type().name(),
                eclipse.regionIds().stream().map(UUID::toString).toList()
        );
    }
}
