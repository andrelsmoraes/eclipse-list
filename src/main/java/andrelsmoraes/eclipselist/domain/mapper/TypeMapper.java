package andrelsmoraes.eclipselist.domain.mapper;

import andrelsmoraes.eclipselist.domain.model.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper {

    public Type toModel(String type) {
        return Type.valueOf(type);
    }

    public String toString(Type type) {
        return type.name();
    }
}
