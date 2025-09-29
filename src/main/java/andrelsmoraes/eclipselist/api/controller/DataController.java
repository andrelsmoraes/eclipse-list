package andrelsmoraes.eclipselist.api.controller;

import andrelsmoraes.eclipselist.api.dto.EclipseDto;
import andrelsmoraes.eclipselist.api.dto.RegionDto;
import andrelsmoraes.eclipselist.api.mapper.EclipsePresentationMapper;
import andrelsmoraes.eclipselist.api.mapper.RegionPresentationMapper;
import andrelsmoraes.eclipselist.application.usecase.eclipse.CreateEclipseUseCase;
import andrelsmoraes.eclipselist.application.usecase.region.CreateRegionUseCase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    private final CreateEclipseUseCase createEclipseUseCase;
    private final CreateRegionUseCase createRegionUseCase;
    private final ObjectMapper objectMapper;
    private final RegionPresentationMapper regionMapper;
    private final EclipsePresentationMapper eclipseMapper;

    @Value("classpath:data/regions.json")
    private Resource regionsFile;

    @Value("classpath:data/eclipses.json")
    private Resource eclipsesFile;

    public DataController(
            CreateEclipseUseCase createEclipseUseCase,
            CreateRegionUseCase createRegionUseCase,
            ObjectMapper objectMapper,
            RegionPresentationMapper regionMapper,
            EclipsePresentationMapper eclipseMapper
    ) {
        this.createEclipseUseCase = createEclipseUseCase;
        this.createRegionUseCase = createRegionUseCase;
        this.objectMapper = objectMapper;
        this.regionMapper = regionMapper;
        this.eclipseMapper = eclipseMapper;
    }

    @PostMapping("/populate")
    public ResponseEntity<String> populateData() {

        try {
            loadRegionsFromFile()
                    .stream()
                    .map(regionMapper::toModel)
                    .forEach(createRegionUseCase::execute);

            loadEclipsesFromFile()
                    .stream()
                    .map(eclipseMapper::toModel)
                    .forEach(createEclipseUseCase::execute);

            return ResponseEntity.ok("Data successfully populated!");
        } catch (IOException e) {
            e.printStackTrace(); // TODO proper logging
            return ResponseEntity.status(500).body("Error populating data: " + e.getMessage());
        }
    }

    // TODO load from storage? use case?
    private List<RegionDto> loadRegionsFromFile() throws IOException {
        try (InputStream is = regionsFile.getInputStream()) {
            return objectMapper.readValue(is, new TypeReference<>() {
            });
        }
    }

    // TODO load from storage? use case?
    private List<EclipseDto> loadEclipsesFromFile() throws IOException {
        try (InputStream is = eclipsesFile.getInputStream()) {
            return objectMapper.readValue(is, new TypeReference<>() {
            });
        }
    }
}
