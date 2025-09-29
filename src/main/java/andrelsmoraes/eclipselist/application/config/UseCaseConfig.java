package andrelsmoraes.eclipselist.application.config;

import andrelsmoraes.eclipselist.application.service.*;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    private final EclipseRepository eclipseRepository;

    public UseCaseConfig(EclipseRepository eclipseRepository) {
        this.eclipseRepository = eclipseRepository;
    }

    @Bean
    public CreateEclipseUseCase getCreateEclipseUseCase() {
        return new CreateEclipseUseCaseImpl(eclipseRepository);
    }

    @Bean
    public ListEclipseUseCase getListEclipseUseCase() {
        return new ListEclipseUseCaseImpl(eclipseRepository);
    }

    @Bean
    public DeleteEclipseByIdUseCase getDeleteEclipseByIdUseCase() {
        return new DeleteEclipseByIdUseCaseImpl(eclipseRepository);
    }

    @Bean
    public ListTypeUseCase getTypeUseCase() {
        return new ListTypeUseCaseImpl();
    }
}
