package andrelsmoraes.eclipselist.api.controller;

import andrelsmoraes.eclipselist.api.mapper.EclipsePresentationMapper;
import andrelsmoraes.eclipselist.api.mapper.RegionPresentationMapper;
import andrelsmoraes.eclipselist.application.usecase.data.ListMockEclipseUseCase;
import andrelsmoraes.eclipselist.application.usecase.data.ListMockRegionUseCase;
import andrelsmoraes.eclipselist.application.usecase.eclipse.CreateEclipseUseCase;
import andrelsmoraes.eclipselist.application.usecase.region.CreateRegionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for populating mock data into the system.
 * This controller is only active in the 'dev' profile.
 */
@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
@Profile("dev")
public class DataController {

    private final CreateEclipseUseCase createEclipseUseCase;
    private final CreateRegionUseCase createRegionUseCase;
    private final ListMockEclipseUseCase listMockEclipseUseCase;
    private final ListMockRegionUseCase listMockRegionUseCase;
    private final RegionPresentationMapper regionMapper;
    private final EclipsePresentationMapper eclipseMapper;

    @PostMapping("/populate")
    public ResponseEntity<String> populateData() {
        listMockRegionUseCase
                .execute()
                .forEach(createRegionUseCase::execute);
        listMockEclipseUseCase
                .execute()
                .forEach(createEclipseUseCase::execute);

        return ResponseEntity.ok("Data successfully populated");
    }
}
