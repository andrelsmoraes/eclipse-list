package andrelsmoraes.eclipselist.api.mapper;

import andrelsmoraes.eclipselist.domain.model.Type;
import org.springframework.stereotype.Component;

@Component
public class TypePresentationMapper {

    public Type toModel(String value) {
        return Type.valueOf(value);
    }

    public String toString(Type type) {
        return type.name();
    }
}
