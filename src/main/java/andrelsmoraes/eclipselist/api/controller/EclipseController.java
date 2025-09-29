package andrelsmoraes.eclipselist.api.controller;

import andrelsmoraes.eclipselist.api.dto.EclipseDto;
import andrelsmoraes.eclipselist.api.mapper.EclipsePresentationMapper;
import andrelsmoraes.eclipselist.application.service.CreateEclipseUseCase;
import andrelsmoraes.eclipselist.application.service.DeleteEclipseByIdUseCase;
import andrelsmoraes.eclipselist.application.service.ListEclipseUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eclipses")
public class EclipseController {

    private final CreateEclipseUseCase createEclipseUseCase;
    private final ListEclipseUseCase listEclipseUseCase;
    private final DeleteEclipseByIdUseCase deleteEclipseByIdUseCase;
    private final EclipsePresentationMapper mapper;

    public EclipseController(
            CreateEclipseUseCase createEclipseUseCase,
            ListEclipseUseCase listEclipseUseCase,
            DeleteEclipseByIdUseCase deleteEclipseByIdUseCase,
            EclipsePresentationMapper mapper
    ) {
        this.createEclipseUseCase = createEclipseUseCase;
        this.listEclipseUseCase = listEclipseUseCase;
        this.deleteEclipseByIdUseCase = deleteEclipseByIdUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public void create(@RequestBody EclipseDto dto) {
        createEclipseUseCase.execute(mapper.toModel(dto));
    }

    @GetMapping
    public List<EclipseDto> listAll() {
        return listEclipseUseCase.execute()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        deleteEclipseByIdUseCase.execute(id);
    }
}
