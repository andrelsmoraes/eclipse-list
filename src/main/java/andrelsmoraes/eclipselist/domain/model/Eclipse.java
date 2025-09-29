package andrelsmoraes.eclipselist.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Represents an Eclipse domain model.
 *
 * @param id        the unique identifier of the eclipse
 * @param date      the date of the eclipse
 * @param type      the type of the eclipse
 * @param regionIds the list of region IDs where the eclipse is visible
 */
public record Eclipse(UUID id, LocalDate date, Type type, List<UUID> regionIds) {

    public Eclipse copy(UUID id, LocalDate date, Type type, List<UUID> regionIds) {
        return new Eclipse(
                id != null ? id : this.id(),
                date != null ? date : this.date(),
                type != null  ? type : this.type(),
                regionIds != null  ? regionIds : this.regionIds()
        );
    }
}
