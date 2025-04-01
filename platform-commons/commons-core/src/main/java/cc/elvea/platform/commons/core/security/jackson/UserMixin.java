package cc.elvea.platform.commons.core.security.jackson;

import cc.elvea.platform.commons.core.security.user.User;
import cc.elvea.platform.commons.utils.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Set;

/**
 * @author elvea
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonDeserialize(using = UserDeserializer.class)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class UserMixin {
}

class UserDeserializer extends JsonDeserializer<User> {

    private static final TypeReference<Set<GrantedAuthority>> GRANTED_AUTHORITY_SET = new TypeReference<>() {
        //
    };

    @Override
    public User deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        JsonNode root = mapper.readTree(parser);
        return deserialize(parser, mapper, root);
    }

    private User deserialize(JsonParser parser, ObjectMapper mapper, JsonNode root) {
        Long id = JacksonUtils.findLongValue(root, "id");
        String username = JacksonUtils.findStringValue(root, "username");
        String password = JacksonUtils.findStringValue(root, "password");
        boolean enabled = JacksonUtils.findBooleanValue(root, "enabled");
        boolean accountNonExpired = JacksonUtils.findBooleanValue(root, "accountNonExpired");
        boolean credentialsNonExpired = JacksonUtils.findBooleanValue(root, "credentialsNonExpired");
        boolean accountNonLocked = JacksonUtils.findBooleanValue(root, "accountNonLocked");
        Set<GrantedAuthority> authorities = JacksonUtils.findValue(root, "authorities", GRANTED_AUTHORITY_SET, mapper);
        return new User(id, username, password, authorities, enabled, accountNonExpired, accountNonLocked, credentialsNonExpired);
    }

}
