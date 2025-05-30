package cc.elvea.platform.commons.utils.jackson;

import cc.elvea.platform.commons.annotations.JsonFormat;
import cc.elvea.platform.commons.constants.DateTimeConstants;
import cc.elvea.platform.commons.utils.DateUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.commons.utils.time.TimeZoneManager;
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
 */
public class DateDeserializer extends StdDeserializer<Date> implements ContextualDeserializer {

    private String pattern;

    private boolean timeZoneConvert = false;

    public DateDeserializer() {
        super(Date.class);
    }

    public DateDeserializer(String pattern, boolean timeZoneConvert) {
        super(Date.class);
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
            return DateUtils.parse(parser.getValueAsString(), pattern, TimeZoneManager.getResolver().resolveUserTimeZone());
        } else {
            return DateUtils.parse(parser.getValueAsString(), pattern);
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) {
        com.fasterxml.jackson.annotation.JsonFormat.Value format = findFormatOverrides(context, property, handledType());
        JsonFormat annotation = property.getAnnotation(JsonFormat.class);
        if (annotation != null) {
            return new DateDeserializer(annotation.pattern(), annotation.timeZoneConvert());
        }
        return new DateDeserializer();
    }

}
