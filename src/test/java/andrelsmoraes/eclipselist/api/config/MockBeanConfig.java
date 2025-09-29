package andrelsmoraes.eclipselist.api.config;

import andrelsmoraes.eclipselist.api.mapper.EclipsePresentationMapper;
import andrelsmoraes.eclipselist.application.config.UseCaseConfig;
import andrelsmoraes.eclipselist.application.service.EclipseEventProducer;
import andrelsmoraes.eclipselist.domain.mapper.TypeMapper;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import andrelsmoraes.eclipselist.domain.repository.RegionRepository;
import andrelsmoraes.eclipselist.domain.repository.TypeRepository;
import andrelsmoraes.eclipselist.infrastructure.mapper.EclipseDataMapper;
import andrelsmoraes.eclipselist.infrastructure.repository.EclipseElasticsearchRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(UseCaseConfig.class)
public class MockBeanConfig {

    @Bean
    public EclipseRepository getEclipseRepository() {
        return Mockito.mock(EclipseRepository.class);
    }

    @Bean
    public EclipseElasticsearchRepository getEclipseElasticsearchRepository() {
        return Mockito.mock(EclipseElasticsearchRepository.class);
    }

    @Bean
    public RegionRepository getRegionRepository() {
        return Mockito.mock(RegionRepository.class);
    }

    @Bean
    public TypeRepository getTypeRepository() {
        return Mockito.mock(TypeRepository.class);
    }

    @Bean
    EclipseEventProducer getEclipseEventProducer() {
        return Mockito.mock(EclipseEventProducer.class);
    }

    @Bean
    public EclipseDataMapper getEclipseDataMapper() {
        return Mockito.mock(EclipseDataMapper.class);
    }

    @Bean
    public TypeMapper getTypeMapper() {
        return new TypeMapper();
    }

    @Bean
    public EclipsePresentationMapper getEclipsePresentationMapper() {
        return new EclipsePresentationMapper();
    }
}
