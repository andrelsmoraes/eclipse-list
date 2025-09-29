package andrelsmoraes.eclipselist.application.usecase.region;

import andrelsmoraes.eclipselist.domain.model.Region;
import andrelsmoraes.eclipselist.domain.repository.RegionRepository;

import java.util.UUID;

public record CreateRegionUseCaseImpl(RegionRepository regionRepository) implements CreateRegionUseCase {

    @Override
    public void execute(Region region) {
        regionRepository.save(
                region.copy(
                        UUID.randomUUID(),
                        region.name()
                )
        );
    }
}
