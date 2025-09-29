package andrelsmoraes.eclipselist.domain.model;

import java.util.UUID;

public record Region(UUID id, String name) {

    public Region copy(UUID id, String name) {
        return new Region(
                id != null ? id : this.id(),
                name != null  ? name : this.name()
        );
    }
}
