package andrelsmoraes.eclipselist.api.controller;

import andrelsmoraes.eclipselist.api.dto.EclipseDto;
import andrelsmoraes.eclipselist.api.mapper.EclipsePresentationMapper;
import andrelsmoraes.eclipselist.application.usecase.eclipse.*;
import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EclipseController.class)
class EclipseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateEclipseUseCase createEclipseUseCase;

    @MockitoBean
    private ListAllEclipsesUseCase listAllEclipsesUseCase;

    @MockitoBean
    private ListEclipsesByRegionUseCase listEclipsesByRegionUseCase;

    @MockitoBean
    private ListEclipsesByTypeUseCase listEclipsesByTypeUseCase;

    @MockitoBean
    private DeleteEclipseByIdUseCase deleteEclipseByIdUseCase;

    @MockitoBean
    private EclipsePresentationMapper eclipseMapper;

    @MockitoBean
    private TypeMapper typeMapper;

    @Test
    void shouldCreateEclipse() throws Exception {
        // Given
        
        UUID eclipseId = UUID.randomUUID();
        UUID regionId1 = UUID.randomUUID();
        UUID regionId2 = UUID.randomUUID();
        LocalDate eclipseDate = LocalDate.of(2025, 4, 8);
        List<UUID> regionIds = Arrays.asList(regionId1, regionId2);

        EclipseDto inputDto = new EclipseDto(null, eclipseDate, Type.SOLAR_TOTAL, regionIds);
        Eclipse eclipseModel = new Eclipse(eclipseId, eclipseDate, Type.SOLAR_TOTAL, regionIds);
        EclipseDto responseDto = new EclipseDto(eclipseId, eclipseDate, Type.SOLAR_TOTAL, regionIds);

        given(eclipseMapper.toModel(inputDto)).willReturn(eclipseModel);
        given(createEclipseUseCase.execute(eclipseModel)).willReturn(eclipseModel);
        given(eclipseMapper.toDto(eclipseModel)).willReturn(responseDto);

        mockMvc.perform(
                    post("/eclipses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(eclipseId.toString()))
                .andExpect(jsonPath("$.date").value("2025-04-08"))
                .andExpect(jsonPath("$.type").value("SOLAR_TOTAL"))
                .andExpect(jsonPath("$.regionIds").isArray())
                .andExpect(jsonPath("$.regionIds.length()").value(2))
                .andExpect(jsonPath("$.regionIds[0]").value(regionId1.toString()))
                .andExpect(jsonPath("$.regionIds[1]").value(regionId2.toString()));

        verify(createEclipseUseCase).execute(eclipseModel);
    }

    @Test
    void shouldListAllEclipses() throws Exception {
        // Given
        UUID eclipse1Id = UUID.randomUUID();
        UUID eclipse2Id = UUID.randomUUID();
        UUID regionId = UUID.randomUUID();

        Eclipse eclipse1 = new Eclipse(eclipse1Id, LocalDate.of(2025, 4, 8), Type.SOLAR_TOTAL, List.of(regionId));
        Eclipse eclipse2 = new Eclipse(eclipse2Id, LocalDate.of(2025, 10, 14), Type.LUNAR_PARTIAL, List.of(regionId));

        EclipseDto dto1 = new EclipseDto(eclipse1Id, LocalDate.of(2025, 4, 8), Type.SOLAR_TOTAL, List.of(regionId));
        EclipseDto dto2 = new EclipseDto(eclipse2Id, LocalDate.of(2025, 10, 14), Type.LUNAR_PARTIAL, List.of(regionId));

        List<Eclipse> eclipses = Arrays.asList(eclipse1, eclipse2);

        given(listAllEclipsesUseCase.execute()).willReturn(eclipses);
        given(eclipseMapper.toDto(eclipse1)).willReturn(dto1);
        given(eclipseMapper.toDto(eclipse2)).willReturn(dto2);

        // When & Then
        mockMvc.perform(
                    get("/eclipses")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(eclipse1Id.toString()))
                .andExpect(jsonPath("$[0].date").value("2025-04-08"))
                .andExpect(jsonPath("$[0].type").value("SOLAR_TOTAL"))
                .andExpect(jsonPath("$[0].regionIds[0]").value(regionId.toString()))
                .andExpect(jsonPath("$[1].id").value(eclipse2Id.toString()))
                .andExpect(jsonPath("$[1].date").value("2025-10-14"))
                .andExpect(jsonPath("$[1].type").value("LUNAR_PARTIAL"))
                .andExpect(jsonPath("$[1].regionIds[0]").value(regionId.toString()));

        verify(listAllEclipsesUseCase).execute();
    }

    @Test
    void shouldListEclipsesByRegion() throws Exception {
        // Given
        UUID regionId = UUID.randomUUID();
        UUID eclipseId = UUID.randomUUID();

        Eclipse eclipse = new Eclipse(eclipseId, LocalDate.of(2025, 4, 8), Type.SOLAR_TOTAL, List.of(regionId));
        EclipseDto dto = new EclipseDto(eclipseId, LocalDate.of(2025, 4, 8), Type.SOLAR_TOTAL, List.of(regionId));

        List<Eclipse> eclipses = List.of(eclipse);

        given(listEclipsesByRegionUseCase.execute(regionId)).willReturn(eclipses);
        given(eclipseMapper.toDto(eclipse)).willReturn(dto);

        // When & Then
        mockMvc.perform(
                    get("/eclipses/by-region")
                        .param("regionId", regionId.toString())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(eclipseId.toString()))
                .andExpect(jsonPath("$[0].date").value("2025-04-08"))
                .andExpect(jsonPath("$[0].type").value("SOLAR_TOTAL"))
                .andExpect(jsonPath("$[0].regionIds[0]").value(regionId.toString()));

        verify(listEclipsesByRegionUseCase).execute(regionId);
    }

    @Test
    void shouldListEclipsesByType() throws Exception {
        // Given
        String typeString = "SOLAR_TOTAL";
        UUID eclipseId = UUID.randomUUID();
        UUID regionId = UUID.randomUUID();

        Eclipse eclipse = new Eclipse(eclipseId, LocalDate.of(2025, 4, 8), Type.SOLAR_TOTAL, List.of(regionId));
        EclipseDto dto = new EclipseDto(eclipseId, LocalDate.of(2025, 4, 8), Type.SOLAR_TOTAL, List.of(regionId));

        List<Eclipse> eclipses = List.of(eclipse);

        given(typeMapper.toModel(typeString)).willReturn(Type.SOLAR_TOTAL);
        given(listEclipsesByTypeUseCase.execute(Type.SOLAR_TOTAL)).willReturn(eclipses);
        given(eclipseMapper.toDto(eclipse)).willReturn(dto);

        // When & Then
        mockMvc.perform(
                    get("/eclipses/by-type")
                        .param("type", typeString)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(eclipseId.toString()))
                .andExpect(jsonPath("$[0].type").value("SOLAR_TOTAL"))
                .andExpect(jsonPath("$[0].regionIds[0]").value(regionId.toString()));

        verify(typeMapper).toModel(typeString);
        verify(listEclipsesByTypeUseCase).execute(Type.SOLAR_TOTAL);
    }

    @Test
    void shouldDeleteEclipseById() throws Exception {
        // Given
        UUID eclipseId = UUID.randomUUID();

        // When & Then
        mockMvc.perform(
                    delete("/eclipses/{id}", eclipseId)
                )
                .andExpect(status().isOk());

        verify(deleteEclipseByIdUseCase).execute(eclipseId);
    }

    @Test
    void shouldReturnBadRequestWhenRegionIdIsMissing() throws Exception {
        mockMvc.perform(
                    get("/eclipses/by-region")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenTypeIsMissing() throws Exception {
        mockMvc.perform(
                    get("/eclipses/by-type")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenRegionIdIsInvalid() throws Exception {
        mockMvc.perform(
                    get("/eclipses/by-region")
                        .param("regionId", "invalid-uuid")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCreateEclipseWithNullRegionIds() throws Exception {
        // Given
        UUID eclipseId = UUID.randomUUID();
        LocalDate eclipseDate = LocalDate.of(2025, 4, 8);

        EclipseDto inputDto = new EclipseDto(null, eclipseDate, Type.SOLAR_TOTAL, null);
        Eclipse eclipseModel = new Eclipse(eclipseId, eclipseDate, Type.SOLAR_TOTAL, null);
        EclipseDto responseDto = new EclipseDto(eclipseId, eclipseDate, Type.SOLAR_TOTAL, null);

        given(eclipseMapper.toModel(inputDto)).willReturn(eclipseModel);
        given(createEclipseUseCase.execute(eclipseModel)).willReturn(eclipseModel);
        given(eclipseMapper.toDto(eclipseModel)).willReturn(responseDto);

        // When & Then
        mockMvc.perform(
                    post("/eclipses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(eclipseId.toString()))
                .andExpect(jsonPath("$.date").value("2025-04-08"))
                .andExpect(jsonPath("$.type").value("SOLAR_TOTAL"))
                .andExpect(jsonPath("$.regionIds").doesNotExist()); // Due to @JsonInclude(JsonInclude.Include.NON_NULL)

        verify(createEclipseUseCase).execute(eclipseModel);
    }

    @Test
    void shouldCreateEclipseWithEmptyRegionIds() throws Exception {
        // Given
        UUID eclipseId = UUID.randomUUID();
        LocalDate eclipseDate = LocalDate.of(2025, 4, 8);
        List<UUID> emptyRegionIds = List.of();

        EclipseDto inputDto = new EclipseDto(null, eclipseDate, Type.LUNAR_PENUMBRAL, emptyRegionIds);
        Eclipse eclipseModel = new Eclipse(eclipseId, eclipseDate, Type.LUNAR_PENUMBRAL, emptyRegionIds);
        EclipseDto responseDto = new EclipseDto(eclipseId, eclipseDate, Type.LUNAR_PENUMBRAL, emptyRegionIds);

        given(eclipseMapper.toModel(inputDto)).willReturn(eclipseModel);
        given(createEclipseUseCase.execute(eclipseModel)).willReturn(eclipseModel);
        given(eclipseMapper.toDto(eclipseModel)).willReturn(responseDto);

        // When & Then
        mockMvc.perform(
                    post("/eclipses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(eclipseId.toString()))
                .andExpect(jsonPath("$.date").value("2025-04-08"))
                .andExpect(jsonPath("$.type").value("LUNAR_PENUMBRAL"))
                .andExpect(jsonPath("$.regionIds").isArray())
                .andExpect(jsonPath("$.regionIds.length()").value(0));

        verify(createEclipseUseCase).execute(eclipseModel);
    }
}
