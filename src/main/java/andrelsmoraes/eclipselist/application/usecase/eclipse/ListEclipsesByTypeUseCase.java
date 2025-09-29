package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;

import java.util.List;

/**
 * Use case interface for listing Eclipse entries by Type.
 */
public interface ListEclipsesByTypeUseCase {

    List<Eclipse> execute(Type type);
}
