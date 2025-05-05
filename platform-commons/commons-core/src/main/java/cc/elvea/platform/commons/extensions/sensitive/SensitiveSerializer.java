package cc.elvea.platform.commons.extensions.sensitive;

import cc.elvea.platform.commons.annotations.Sensitive;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;

import java.io.IOException;
import java.util.Objects;

/**
 * @author elvea
 */
@Slf4j
public class SensitiveSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveStrategy strategy;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            SensitiveService sensitiveService = SensitiveManager.getService();
            if (ObjectUtil.isNotNull(sensitiveService)) {
                gen.writeString(strategy.handler().apply(value));
            } else {
                gen.writeString(value);
            }
        } catch (BeansException e) {
            gen.writeString(value);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        Sensitive annotation = property.getAnnotation(Sensitive.class);
        if (Objects.nonNull(annotation) && Objects.equals(String.class, property.getType().getRawClass())) {
            this.strategy = annotation.strategy();
            return this;
        }
        return prov.findValueSerializer(property.getType(), property);
    }

}
