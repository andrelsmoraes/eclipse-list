package andrelsmoraes.eclipselist.application.service;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.domain.repository.EclipseRepository;

import java.util.Arrays;
import java.util.List;

public record ListTypeUseCaseImpl() implements ListTypeUseCase {

    @Override
    public List<Type> execute() {
        return Arrays.asList(Type.values());
    }
}
