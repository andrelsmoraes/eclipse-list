package andrelsmoraes.eclipselist.application.usecase.data;

import andrelsmoraes.eclipselist.domain.model.Eclipse;

import java.util.List;

/**
 * Use case interface for listing mock Eclipse data.
 */
public interface ListMockEclipseUseCase {

    List<Eclipse> execute();
}
