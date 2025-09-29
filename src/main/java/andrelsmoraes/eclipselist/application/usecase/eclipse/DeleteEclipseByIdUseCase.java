package andrelsmoraes.eclipselist.application.usecase.eclipse;

import java.util.UUID;

/**
 * Use case interface for deleting an Eclipse entry by its ID.
 */
public interface DeleteEclipseByIdUseCase {

    void execute(UUID id);
}
