package cn.elvea.platform.commons.core.extensions.json.jackson;

import cn.elvea.platform.commons.core.annotations.JsonFormat;
import cn.elvea.platform.commons.core.extensions.time.TimeZoneResolver;
import cn.elvea.platform.commons.core.utils.DateTimeUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
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
 * LocalDateTimeDeserializer
 *
 * @author elvea
 * @since 0.0.1
 */
public final class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> implements ContextualDeserializer {

    private final TimeZoneResolver resolver;

    private String pattern;

    private boolean timeZoneConvert = false;

    public LocalDateTimeDeserializer(TimeZoneResolver resolver) {
        super(LocalDateTime.class);
        this.resolver = resolver;
    }

    public LocalDateTimeDeserializer(TimeZoneResolver resolver, String pattern, boolean timeZoneConvert) {
        this(resolver);
        this.pattern = pattern;
        this.timeZoneConvert = timeZoneConvert;
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        LocalDateTime localDateTime = DateTimeUtils.parse(parser.getText(), getFormatter(), LocalDateTime.class);
        if (timeZoneConvert) {
            return DateTimeUtils.transfer(localDateTime, resolver.resolveUserZoneId(), resolver.resolveSystemZoneId());
        } else {
            return localDateTime;
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) {
        com.fasterxml.jackson.annotation.JsonFormat.Value format = findFormatOverrides(context, property, handledType());
        JsonFormat annotation = property.getAnnotation(JsonFormat.class);
        if (annotation != null) {
            return new LocalDateTimeDeserializer(resolver, annotation.pattern(), annotation.timeZoneConvert());
        }
        return new LocalDateTimeDeserializer(resolver);
    }

    private DateTimeFormatter getFormatter() {
        DateTimeFormatter formatter = null;
        if (StringUtils.isNotEmpty(this.pattern)) {
            formatter = DateTimeFormatter.ofPattern(this.pattern);
        }
        return formatter;
    }

}
