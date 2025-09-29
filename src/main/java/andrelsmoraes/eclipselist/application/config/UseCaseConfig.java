package andrelsmoraes.eclipselist.application.config;

import andrelsmoraes.eclipselist.application.service.EclipseEventProducer;
import andrelsmoraes.eclipselist.application.usecase.data.ListMockEclipseUseCase;
import andrelsmoraes.eclipselist.application.usecase.data.ListMockEclipseUseCaseImpl;
import andrelsmoraes.eclipselist.application.usecase.data.ListMockRegionUseCase;
import andrelsmoraes.eclipselist.application.usecase.data.ListMockRegionUseCaseImpl;
import andrelsmoraes.eclipselist.application.usecase.eclipse.*;
import andrelsmoraes.eclipselist.application.usecase.region.*;
import andrelsmoraes.eclipselist.application.usecase.type.ListTypeUseCase;
import andrelsmoraes.eclipselist.application.usecase.type.ListTypeUseCaseImpl;
import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import andrelsmoraes.eclipselist.domain.repository.RegionRepository;
import andrelsmoraes.eclipselist.domain.repository.TypeRepository;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import andrelsmoraes.eclipselist.infrastructure.repository.EclipseElasticsearchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for providing use case implementations as Spring beans.
 * Use cases are not annotated with @Service to maintain a clean architecture approach and facilitate testing.
 */
@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {

    private final EclipseRepository eclipseRepository;
    private final EclipseElasticsearchRepository eclipseElasticsearchRepository;
    private final RegionRepository regionRepository;
    private final TypeRepository typeRepository;
    private final EclipseEventProducer eclipseEventProducer;
    private final EclipseDataMapper eclipseDataMapper;
    private final TypeMapper typeMapper;
    private final ObjectMapper objectMapper;

    @Bean
    public CreateEclipseUseCase getCreateEclipseUseCase() {
        return new CreateEclipseUseCaseImpl(eclipseRepository, eclipseEventProducer);
    }

    @Bean
    public ListAllEclipsesUseCase getListEclipsesUseCase() {
        return new ListAllEclipsesUseCaseImpl(eclipseRepository);
    }

    @Bean
    public ListEclipsesByRegionUseCase getListEclipsesByRegionUseCase() {
        return new ListEclipsesByRegionUseCaseImpl(eclipseElasticsearchRepository, eclipseDataMapper, typeMapper);
    }

    @Bean
    public ListEclipsesByTypeUseCase getListEclipsesByTypeUseCase() {
        return new ListEclipsesByTypeUseCaseImpl(eclipseElasticsearchRepository, eclipseDataMapper, typeMapper);
    }

    @Bean
    public DeleteEclipseByIdUseCase getDeleteEclipseByIdUseCase() {
        return new DeleteEclipseByIdUseCaseImpl(eclipseRepository, eclipseEventProducer);
    }

    @Bean
    public CreateRegionUseCase getCreateRegionUseCase() {
        return new CreateRegionUseCaseImpl(regionRepository);
    }

    @Bean
    public ListRegionUseCase getListRegionUseCase() {
        return new ListRegionUseCaseImpl(regionRepository);
    }

    @Bean
    public DeleteRegionByIdUseCase getDeleteRegionByIdUseCase() {
        return new DeleteRegionByIdUseCaseImpl(regionRepository);
    }

    @Bean
    public ListTypeUseCase getTypeUseCase() {
        return new ListTypeUseCaseImpl(typeRepository);
    }

    @Bean
    public ListMockEclipseUseCase getListMockEclipseUseCase() {
        return new ListMockEclipseUseCaseImpl(objectMapper);
    }

    @Bean
    public ListMockRegionUseCase getListMockRegionUseCase() {
        return new ListMockRegionUseCaseImpl(objectMapper);
    }
}
