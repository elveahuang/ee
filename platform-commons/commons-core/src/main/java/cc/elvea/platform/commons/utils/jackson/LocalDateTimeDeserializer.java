package cc.elvea.platform.commons.utils.jackson;

import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.utils.DateTimeUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.commons.utils.time.TimeZoneManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author elvea
 * @since 24.1.0
 */
public final class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> implements ContextualDeserializer {

    private String pattern;

    private boolean timeZoneConvert = false;

    public LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    public LocalDateTimeDeserializer(String pattern, boolean timeZoneConvert) {
        super(LocalDateTime.class);
        this.pattern = pattern;
        this.timeZoneConvert = timeZoneConvert;
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        LocalDateTime localDateTime = DateTimeUtils.parse(parser.getText(), getFormatter(), LocalDateTime.class);
        if (timeZoneConvert) {
            return DateTimeUtils.transfer(localDateTime, TimeZoneManager.getResolver().resolveUserZoneId(), TimeZoneManager.getResolver().resolveSystemZoneId());
        } else {
            return localDateTime;
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) {
        com.fasterxml.jackson.annotation.JsonFormat.Value format = findFormatOverrides(context, property, handledType());
        JsonFormat annotation = property.getAnnotation(JsonFormat.class);
        if (annotation != null) {
            return new LocalDateTimeDeserializer(annotation.pattern(), annotation.timeZoneConvert());
        }
        return new LocalDateTimeDeserializer();
    }

    private DateTimeFormatter getFormatter() {
        DateTimeFormatter formatter = null;
        if (StringUtils.isNotEmpty(this.pattern)) {
            formatter = DateTimeFormatter.ofPattern(this.pattern);
        }
        return formatter;
    }

}
