package andrelsmoraes.eclipselist.application.config;

import andrelsmoraes.eclipselist.application.usecase.eclipse.*;
import andrelsmoraes.eclipselist.application.usecase.region.*;
import andrelsmoraes.eclipselist.application.usecase.type.ListTypeUseCase;
import andrelsmoraes.eclipselist.application.usecase.type.ListTypeUseCaseImpl;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import andrelsmoraes.eclipselist.domain.repository.RegionRepository;
import andrelsmoraes.eclipselist.domain.repository.TypeRepository;
import andrelsmoraes.eclipselist.infrastructure.repository.EclipseElasticsearchRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    private final EclipseRepository eclipseRepository;
    private final EclipseElasticsearchRepository eclipseElasticsearchRepository;
    private final RegionRepository regionRepository;
    private final TypeRepository typeRepository;

    public UseCaseConfig(
            EclipseRepository eclipseRepository,
            EclipseElasticsearchRepository eclipseElasticsearchRepository,
            RegionRepository regionRepository,
            TypeRepository typeRepository
    ) {
        this.eclipseRepository = eclipseRepository;
        this.eclipseElasticsearchRepository = eclipseElasticsearchRepository;
        this.regionRepository = regionRepository;
        this.typeRepository = typeRepository;
    }

    @Bean
    public CreateEclipseUseCase getCreateEclipseUseCase() {
        return new CreateEclipseUseCaseImpl(eclipseRepository);
    }

    @Bean
    public ListAllEclipsesUseCase getListEclipsesUseCase() {
        return new ListAllEclipsesUseCaseImpl(eclipseRepository);
    }

    @Bean
    public ListEclipsesByRegionUseCase getListEclipsesByRegionUseCase() {
        return new ListEclipsesByRegionUseCaseImpl(eclipseRepository);
    }

    @Bean
    public DeleteEclipseByIdUseCase getDeleteEclipseByIdUseCase() {
        return new DeleteEclipseByIdUseCaseImpl(eclipseRepository);
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
}
