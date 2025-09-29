package andrelsmoraes.eclipselist.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EclipseDto(UUID id, LocalDate date) {}
