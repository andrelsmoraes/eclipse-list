package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;

/**
 * Use case interface for creating a new Eclipse entry.
 */
public interface CreateEclipseUseCase {

    Eclipse execute(Eclipse eclipse);
}
