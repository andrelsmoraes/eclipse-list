package andrelsmoraes.eclipselist.api.controller;

import andrelsmoraes.eclipselist.api.dto.EclipseDto;
import andrelsmoraes.eclipselist.api.mapper.EclipsePresentationMapper;
import andrelsmoraes.eclipselist.application.usecase.eclipse.*;
import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing Eclipse entities.
 */
@RestController
@RequestMapping("/eclipses")
@RequiredArgsConstructor
public class EclipseController {

    private final CreateEclipseUseCase createEclipseUseCase;
    private final ListAllEclipsesUseCase listAllEclipsesUseCase;
    private final ListEclipsesByRegionUseCase listEclipsesByRegionUseCase;
    private final ListEclipsesByTypeUseCase listEclipsesByTypeUseCase;
    private final DeleteEclipseByIdUseCase deleteEclipseByIdUseCase;
    private final EclipsePresentationMapper eclipseMapper;
    private final TypeMapper typeMapper;

    @PostMapping
    public EclipseDto create(@RequestBody EclipseDto dto) {
        Eclipse eclipse = createEclipseUseCase.execute(eclipseMapper.toModel(dto));
        return eclipseMapper.toDto(eclipse);
    }

    @GetMapping
    public List<EclipseDto> listAll() {
        return listAllEclipsesUseCase.execute()
                .stream()
                .map(eclipseMapper::toDto)
                .toList();
    }

    @GetMapping("/by-region")
    public List<EclipseDto> listByRegion(@RequestParam UUID regionId) {
        return listEclipsesByRegionUseCase.execute(regionId)
                .stream()
                .map(eclipseMapper::toDto)
                .toList();
    }

    @GetMapping("/by-type")
    public List<EclipseDto> listByType(@RequestParam String type) {
        return listEclipsesByTypeUseCase.execute(typeMapper.toModel(type))
                .stream()
                .map(eclipseMapper::toDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        deleteEclipseByIdUseCase.execute(id);
    }
}
