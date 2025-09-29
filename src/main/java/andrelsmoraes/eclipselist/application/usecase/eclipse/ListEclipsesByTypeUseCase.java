package andrelsmoraes.eclipselist.application.usecase.eclipse;

import andrelsmoraes.eclipselist.domain.model.Eclipse;
import andrelsmoraes.eclipselist.domain.model.Type;

import java.util.List;

public interface ListEclipsesByTypeUseCase {

    List<Eclipse> execute(Type type);
}
