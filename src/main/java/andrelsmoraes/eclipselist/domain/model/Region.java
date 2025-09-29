package andrelsmoraes.eclipselist.domain.model;

import java.util.UUID;

/**
 * Represents a geographical Region domain model.
 *
 * @param id   Unique identifier for the region.
 * @param name Name of the region.
 */
public record Region(UUID id, String name) {

    public Region copy(UUID id, String name) {
        return new Region(
                id != null ? id : this.id(),
                name != null  ? name : this.name()
        );
    }
}
