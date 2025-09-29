package andrelsmoraes.eclipselist.api.dto;

import andrelsmoraes.eclipselist.domain.model.Type;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EclipseDto(UUID id, LocalDate date, Type type, List<UUID> regionIds) {}
