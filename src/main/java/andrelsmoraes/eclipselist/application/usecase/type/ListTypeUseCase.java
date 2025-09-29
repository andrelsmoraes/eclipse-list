package andrelsmoraes.eclipselist.application.usecase.type;

import andrelsmoraes.eclipselist.domain.model.Type;

import java.util.List;

/**
 * Use case interface for listing all Type entries.
 */
public interface ListTypeUseCase {

    List<Type> execute();
}
