package cn.elvea.platform.commons.core.extensions.json.jackson;

import cn.elvea.platform.commons.core.extensions.time.TimeZoneResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author elvea
 * @since 0.0.1
 */
public class CustomJsonModule extends SimpleModule {

    public CustomJsonModule(TimeZoneResolver resolver) {
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.addDeserializer(Date.class, new DateDeserializer(resolver));
        this.addSerializer(Date.class, new DateSerializer(resolver));
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(resolver));
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(resolver));
    }

}
