package andrelsmoraes.eclipselist.application.usecase.region;

import andrelsmoraes.eclipselist.domain.repository.RegionRepository;

import java.util.UUID;

public record DeleteRegionByIdUseCaseImpl(RegionRepository regionRepository) implements DeleteRegionByIdUseCase {

    @Override
    public void execute(UUID id) {
        regionRepository.deleteById(id.toString());
    }
}
