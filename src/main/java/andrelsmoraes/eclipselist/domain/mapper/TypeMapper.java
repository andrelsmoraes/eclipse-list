package andrelsmoraes.eclipselist.domain.mapper;

import andrelsmoraes.eclipselist.domain.model.Type;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting the Type enum to and from its String representation.
 */
@Component
public class TypeMapper {

    /**
     * Converts a String representation of a Type to its corresponding Type enum.
     *
     * @param type the String representation of the Type
     * @return the corresponding Type enum
     * @throws RuntimeException if the provided String does not match any Type enum
     */
    public Type toModel(String type) {
        try {
            return Type.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Eclipse Type: " + type, e);
        }
    }

    /**
     * Converts a Type enum to its String representation.
     *
     * @param type the Type enum
     * @return the String representation of the Type
     */
    public String toString(Type type) {
        return type.name();
    }
}
