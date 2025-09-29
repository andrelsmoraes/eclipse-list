package andrelsmoraes.eclipselist.api.controller;

import andrelsmoraes.eclipselist.api.dto.RegionDto;
import andrelsmoraes.eclipselist.api.mapper.RegionPresentationMapper;
import andrelsmoraes.eclipselist.application.usecase.region.CreateRegionUseCase;
import andrelsmoraes.eclipselist.application.usecase.region.DeleteRegionByIdUseCase;
import andrelsmoraes.eclipselist.application.usecase.region.ListRegionUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private final CreateRegionUseCase createRegionUseCase;
    private final ListRegionUseCase listRegionUseCase;
    private final DeleteRegionByIdUseCase deleteRegionByIdUseCase;
    private final RegionPresentationMapper mapper;

    public RegionController(
            CreateRegionUseCase createRegionUseCase,
            ListRegionUseCase listRegionUseCase,
            DeleteRegionByIdUseCase deleteRegionByIdUseCase,
            RegionPresentationMapper mapper
    ) {
        this.createRegionUseCase = createRegionUseCase;
        this.listRegionUseCase = listRegionUseCase;
        this.deleteRegionByIdUseCase = deleteRegionByIdUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public void create(@RequestBody RegionDto dto) {
        createRegionUseCase.execute(mapper.toModel(dto));
    }

    @GetMapping
    public List<RegionDto> listAll() {
        return listRegionUseCase.execute()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        deleteRegionByIdUseCase.execute(id);
    }
}
