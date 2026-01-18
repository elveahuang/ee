package cc.wdev.platform.commons.security.jackson;

import cc.wdev.platform.commons.utils.JacksonUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

/**
 * @author elvea
 */
@JsonDeserialize(using = LongDeserializer.class)
public abstract class LongMixin {
}

class LongDeserializer extends ValueDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser parser, DeserializationContext context) {
        JsonNode root = context.readTree(parser);
        return deserialize(parser, root);
    }

    private Long deserialize(JsonParser parser, JsonNode root) {
        String value = JacksonUtils.findStringValue(root, "value");
        if (StringUtils.isNotEmpty(value)) {
            return Long.valueOf(value);
        }
        return null;
    }

}
