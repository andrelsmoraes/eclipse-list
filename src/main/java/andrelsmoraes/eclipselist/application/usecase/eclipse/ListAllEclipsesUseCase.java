package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;

import java.util.List;

/**
 * Use case interface for listing all Eclipse entries.
 */
public interface ListAllEclipsesUseCase {

    List<Eclipse> execute();
}
