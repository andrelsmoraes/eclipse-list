package andrelsmoraes.eclipselist.application.usecase.region;

import andrelsmoraes.eclipselist.domain.model.Region;
import andrelsmoraes.eclipselist.domain.repository.RegionRepository;

import java.util.List;

public record ListRegionUseCaseImpl(RegionRepository regionRepository) implements ListRegionUseCase {

    @Override
    public List<Region> execute() {
        return regionRepository.listAll();
    }
}
