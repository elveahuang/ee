package cn.elvea.platform.commons.core.extensions.json.jackson;

import cn.elvea.platform.commons.core.annotations.JsonFormat;
import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import cn.elvea.platform.commons.core.extensions.time.TimeZoneResolver;
import cn.elvea.platform.commons.core.utils.DateUtils;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Date;

/**
 * @author elvea
 * @since 0.0.1
 */
public class DateSerializer extends StdSerializer<Date> implements ContextualSerializer {

    private final TimeZoneResolver resolver;

    private String pattern;

    private boolean timeZoneConvert = false;

    public DateSerializer(TimeZoneResolver resolver) {
        super(Date.class);
        this.resolver = resolver;
    }

    public DateSerializer(TimeZoneResolver resolver, String pattern, boolean timeZoneConvert) {
        this(resolver);
        this.pattern = pattern;
        this.timeZoneConvert = timeZoneConvert;
    }

    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException {
        String pattern = StringUtils.isNotEmpty(this.pattern) ? this.pattern : DateTimeConstants.DEFAULT_DATE_TIME_PATTERN;
        if (timeZoneConvert) {
            generator.writeString(DateUtils.format(date, pattern, resolver.resolveUserTimeZone()));
        } else {
            generator.writeString(DateUtils.format(date, pattern));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) {
        if (!ObjectUtils.isEmpty(property)) {
            com.fasterxml.jackson.annotation.JsonFormat.Value format = findFormatOverrides(provider, property, handledType());
            JsonFormat annotation = property.getAnnotation(JsonFormat.class);
            if (annotation != null) {
                return new DateSerializer(resolver, annotation.pattern(), annotation.timeZoneConvert());
            }
        }
        return new DateSerializer(resolver);
    }

}
