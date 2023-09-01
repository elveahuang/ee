package cn.elvea.platform.commons.core.extensions.json.jackson;

import cn.elvea.platform.commons.core.annotations.JsonFormat;
import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import cn.elvea.platform.commons.core.extensions.time.TimeZoneResolver;
import cn.elvea.platform.commons.core.utils.DateUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * @author elvea
 * @since 0.0.1
 */
public class DateDeserializer extends StdDeserializer<Date> implements ContextualDeserializer {

    private final TimeZoneResolver resolver;

    private String pattern;

    private boolean timeZoneConvert = false;

    public DateDeserializer(TimeZoneResolver resolver) {
        super(Date.class);
        this.resolver = resolver;
    }

    public DateDeserializer(TimeZoneResolver resolver, String pattern, boolean timeZoneConvert) {
        this(resolver);
        this.pattern = pattern;
        this.timeZoneConvert = timeZoneConvert;
    }

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN;
        if (StringUtils.isNotEmpty(this.pattern)) {
            pattern = this.pattern;
        }
        if (timeZoneConvert) {
            return DateUtils.parse(parser.getValueAsString(), pattern, resolver.resolveUserTimeZone());
        } else {
            return DateUtils.parse(parser.getValueAsString(), pattern);
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) {
        com.fasterxml.jackson.annotation.JsonFormat.Value format = findFormatOverrides(context, property, handledType());
        JsonFormat annotation = property.getAnnotation(JsonFormat.class);
        if (annotation != null) {
            return new DateDeserializer(resolver, annotation.pattern(), annotation.timeZoneConvert());
        }
        return new DateDeserializer(resolver);
    }

}
