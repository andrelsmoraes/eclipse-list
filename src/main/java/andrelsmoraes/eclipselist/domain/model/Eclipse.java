package andrelsmoraes.eclipselist.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public record Eclipse(UUID id, LocalDate date, Type type) {

    public Eclipse copy(UUID id, LocalDate date, Type type) {
        return new Eclipse(
                id != null ? id : this.id(),
                date != null ? date : this.date(),
                type != null  ? type : this.type()
        );
    }
}
