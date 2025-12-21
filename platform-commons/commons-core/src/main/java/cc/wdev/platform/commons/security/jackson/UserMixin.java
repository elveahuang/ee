package cc.wdev.platform.commons.security.jackson;

import cc.wdev.platform.commons.security.user.User;
import cc.wdev.platform.commons.utils.JacksonUtils;
import org.springframework.security.core.GrantedAuthority;
import tools.jackson.core.JsonParser;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.util.Collections;
import java.util.Set;

/**
 * @author elvea
 */
@JsonDeserialize(using = UserDeserializer.class)
public abstract class UserMixin {
}

class UserDeserializer extends ValueDeserializer<User> {

    private static final TypeReference<Set<GrantedAuthority>> GRANTED_AUTHORITY_SET = new TypeReference<>() {
    };

    @Override
    public User deserialize(JsonParser parser, DeserializationContext context) {
        JsonNode root = context.readTree(parser);
        return deserialize(parser, root);
    }

    private User deserialize(JsonParser parser, JsonNode root) {
        Long id = JacksonUtils.findLongValue(root, "id");
        String username = JacksonUtils.findStringValue(root, "username");
        String password = JacksonUtils.findStringValue(root, "password");
        boolean enabled = JacksonUtils.findBooleanValue(root, "enabled");
        String usertype = JacksonUtils.findStringValue(root, "userType");
        boolean accountNonExpired = JacksonUtils.findBooleanValue(root, "accountNonExpired");
        boolean credentialsNonExpired = JacksonUtils.findBooleanValue(root, "credentialsNonExpired");
        boolean accountNonLocked = JacksonUtils.findBooleanValue(root, "accountNonLocked");
        Set<GrantedAuthority> authorities = Collections.emptySet();
//        Set<GrantedAuthority> authorities = JacksonUtils.findValue(root, "authorities", GRANTED_AUTHORITY_SET);
        return new User(id, usertype, username, password, authorities, enabled, accountNonExpired, accountNonLocked, credentialsNonExpired);
    }

}
