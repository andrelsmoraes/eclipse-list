package andrelsmoraes.eclipselist.application.usecase.type;

import andrelsmoraes.eclipselist.domain.model.Type;
import andrelsmoraes.eclipselist.domain.repository.TypeRepository;

import java.util.List;

public record ListTypeUseCaseImpl(TypeRepository typeRepository) implements ListTypeUseCase {

    @Override
    public List<Type> execute() {
        return typeRepository.listAll();
    }
}
