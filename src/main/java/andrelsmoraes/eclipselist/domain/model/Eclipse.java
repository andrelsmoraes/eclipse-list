package andrelsmoraes.eclipselist.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
