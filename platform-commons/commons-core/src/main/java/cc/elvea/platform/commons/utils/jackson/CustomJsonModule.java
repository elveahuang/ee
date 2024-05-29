package cc.elvea.platform.commons.utils.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author elvea
 * @since 24.1.0
 */
public class CustomJsonModule extends SimpleModule {

    public CustomJsonModule() {
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.addDeserializer(Date.class, new DateDeserializer());
        this.addSerializer(Date.class, new DateSerializer());
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
    }

}
