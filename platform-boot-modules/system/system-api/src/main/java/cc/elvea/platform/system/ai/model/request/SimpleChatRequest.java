package cc.elvea.platform.system.ai.model.request;

import cc.elvea.platform.commons.web.request.Request;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.google.common.collect.Lists;
import lombok.*;
import org.springframework.ai.chat.messages.*;

import java.io.IOException;
import java.util.List;

import static cc.elvea.platform.commons.utils.JacksonUtils.findStringValue;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SimpleChatRequest extends Request {
    private String id;
    private String message;
    @JsonDeserialize(using = CustomJsonDeserializer.class)
    private List<Message> messages;

    static class CustomJsonDeserializer extends JsonDeserializer<List<Message>> implements ContextualDeserializer {

        @Override
        public List<Message> deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
            ObjectMapper mapper = (ObjectMapper) parser.getCodec();
            JsonNode root = mapper.readTree(parser);
            return deserialize(parser, mapper, root);
        }

        private List<Message> deserialize(JsonParser parser, ObjectMapper mapper, JsonNode root) {
            List<Message> messages = Lists.newArrayList();
            if (root.isArray() && !root.isEmpty()) {
                root.forEach(item -> {
                    String role = findStringValue(item, "role");
                    String text = findStringValue(item.get("content"), "text");
                    switch (MessageType.fromValue(role)) {
                        case MessageType.USER -> messages.add(new UserMessage(text));
                        case MessageType.ASSISTANT -> messages.add(new AssistantMessage(text));
                        case MessageType.SYSTEM -> messages.add(new SystemMessage(text));
                        case MessageType.TOOL -> messages.add(new ToolResponseMessage(List.of()));
                    }
                });
            }
            return messages;
        }

        @Override
        public JsonDeserializer<?> createContextual(DeserializationContext ctx, BeanProperty property) {
            return this;
        }

    }

}
