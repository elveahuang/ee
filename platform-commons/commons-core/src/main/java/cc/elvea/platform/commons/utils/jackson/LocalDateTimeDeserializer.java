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
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author elvea
 */
@Slf4j
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
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        log.info("deserialize [{}] pattern [{}].", parser.getText(), this.pattern);
        LocalDateTime localDateTime = DateTimeUtils.parse(parser.getText(), getFormatter(), LocalDateTime.class);
        if (timeZoneConvert) {
            return DateTimeUtils.transfer(localDateTime, TimeZoneManager.getResolver().resolveUserZoneId(), TimeZoneManager.getResolver().resolveSystemZoneId());
        } else {
            return localDateTime;
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) {
        log.info("createContextual [{}].", property.getName());
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
