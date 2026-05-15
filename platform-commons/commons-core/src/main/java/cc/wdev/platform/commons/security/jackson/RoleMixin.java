package cc.wdev.platform.commons.security.jackson;

import cc.wdev.platform.commons.security.domain.Role;
import cc.wdev.platform.commons.utils.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import tools.jackson.core.JsonParser;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;

/**
 * @author elvea
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@JsonDeserialize(using = RoleDeserializer.class)
public abstract class RoleMixin {
}

class RoleDeserializer extends ValueDeserializer<Role> {

    private static final TypeReference<Set<String>> SCOPES_SET = new TypeReference<>() {
    };

    @Override
    public Role deserialize(JsonParser parser, DeserializationContext context) {
        JsonNode root = context.readTree(parser);
        return deserialize(parser, context, root);
    }

    private Role deserialize(JsonParser parser, DeserializationContext context, JsonNode root) {
        Long id = JacksonUtils.findLongValue(root, "id");
        String code = JacksonUtils.findStringValue(root, "code");
        String name = JacksonUtils.findStringValue(root, "name");
        Set<String> scopes = JacksonUtils.findValue(root, "scopes", SCOPES_SET, context);
        return Role.builder().id(id).code(code).name(name).scopes(scopes).build();
    }

}
