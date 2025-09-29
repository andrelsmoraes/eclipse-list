package andrelsmoraes.eclipselist.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RegionDto(UUID id, String name) {}
