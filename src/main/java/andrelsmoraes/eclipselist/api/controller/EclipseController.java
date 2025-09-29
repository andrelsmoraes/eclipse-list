package andrelsmoraes.eclipselist.api.controller;

import andrelsmoraes.eclipselist.api.dto.EclipseDto;
import andrelsmoraes.eclipselist.api.mapper.EclipsePresentationMapper;
import andrelsmoraes.eclipselist.application.usecase.eclipse.*;
import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eclipses")
public class EclipseController {

    private final CreateEclipseUseCase createEclipseUseCase;
    private final ListAllEclipsesUseCase listAllEclipsesUseCase;
    private final ListEclipsesByRegionUseCase listEclipsesByRegionUseCase;
    private final ListEclipsesByTypeUseCase listEclipsesByTypeUseCase;
    private final DeleteEclipseByIdUseCase deleteEclipseByIdUseCase;
    private final EclipsePresentationMapper eclipsePresentationMapper;
    private final TypeMapper typeMapper;

    public EclipseController(
            CreateEclipseUseCase createEclipseUseCase,
            ListAllEclipsesUseCase listAllEclipsesUseCase,
            ListEclipsesByRegionUseCase listEclipsesByRegionUseCase,
            ListEclipsesByTypeUseCase listEclipsesByTypeUseCase,
            DeleteEclipseByIdUseCase deleteEclipseByIdUseCase,
            EclipsePresentationMapper eclipsePresentationMapper,
            TypeMapper typeMapper
    ) {
        this.createEclipseUseCase = createEclipseUseCase;
        this.listAllEclipsesUseCase = listAllEclipsesUseCase;
        this.listEclipsesByRegionUseCase = listEclipsesByRegionUseCase;
        this.listEclipsesByTypeUseCase = listEclipsesByTypeUseCase;
        this.deleteEclipseByIdUseCase = deleteEclipseByIdUseCase;
        this.eclipsePresentationMapper = eclipsePresentationMapper;
        this.typeMapper = typeMapper;
    }

    @PostMapping
    public void create(@RequestBody EclipseDto dto) {
        createEclipseUseCase.execute(eclipsePresentationMapper.toModel(dto));
    }

    @GetMapping
    public List<EclipseDto> listAll() {
        return listAllEclipsesUseCase.execute()
                .stream()
                .map(eclipsePresentationMapper::toDto)
                .toList();
    }

    @GetMapping("/by-region")
    public List<EclipseDto> listByRegion(@RequestParam UUID regionId) {
        return listEclipsesByRegionUseCase.execute(regionId)
                .stream()
                .map(eclipsePresentationMapper::toDto)
                .toList();
    }

    @GetMapping("/by-type")
    public List<EclipseDto> listByType(@RequestParam String type) {
        return listEclipsesByTypeUseCase.execute(typeMapper.toModel(type))
                .stream()
                .map(eclipsePresentationMapper::toDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        deleteEclipseByIdUseCase.execute(id);
    }
}
