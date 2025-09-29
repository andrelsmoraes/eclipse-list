package andrelsmoraes.eclipselist.api.controller;

import andrelsmoraes.eclipselist.api.dto.EclipseDto;
import andrelsmoraes.eclipselist.api.mapper.EclipsePresentationMapper;
import andrelsmoraes.eclipselist.application.usecase.eclipse.CreateEclipseUseCase;
import andrelsmoraes.eclipselist.application.usecase.eclipse.DeleteEclipseByIdUseCase;
import andrelsmoraes.eclipselist.application.usecase.eclipse.ListAllEclipsesUseCase;
import andrelsmoraes.eclipselist.application.usecase.eclipse.ListEclipsesByRegionUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eclipses")
public class EclipseController {

    private final CreateEclipseUseCase createEclipseUseCase;
    private final ListAllEclipsesUseCase listAllEclipsesUseCase;
    private final ListEclipsesByRegionUseCase listEclipsesByRegionUseCase;
    private final DeleteEclipseByIdUseCase deleteEclipseByIdUseCase;
    private final EclipsePresentationMapper mapper;

    public EclipseController(
            CreateEclipseUseCase createEclipseUseCase,
            ListAllEclipsesUseCase listAllEclipsesUseCase,
            ListEclipsesByRegionUseCase listEclipsesByRegionUseCase,
            DeleteEclipseByIdUseCase deleteEclipseByIdUseCase,
            EclipsePresentationMapper mapper
    ) {
        this.createEclipseUseCase = createEclipseUseCase;
        this.listAllEclipsesUseCase = listAllEclipsesUseCase;
        this.listEclipsesByRegionUseCase = listEclipsesByRegionUseCase;
        this.deleteEclipseByIdUseCase = deleteEclipseByIdUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public void create(@RequestBody EclipseDto dto) {
        createEclipseUseCase.execute(mapper.toModel(dto));
    }

    @GetMapping
    public List<EclipseDto> listAll() {
        return listAllEclipsesUseCase.execute()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/by-region")
    public List<EclipseDto> listByRegion(@RequestParam UUID regionId) {
        return listEclipsesByRegionUseCase.execute(regionId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        deleteEclipseByIdUseCase.execute(id);
    }
}
