package cc.wdev.platform.commons.extensions.sensitive;

import cc.wdev.platform.commons.annotations.Sensitive;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.util.Objects;

/**
 * @author elvea
 */
@Slf4j
public class SensitiveSerializer extends ValueSerializer<String> {

    private SensitiveStrategy strategy;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializationContext serializers) {
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
    public ValueSerializer<?> createContextual(SerializationContext prov, BeanProperty property) {
        Sensitive annotation = property.getAnnotation(Sensitive.class);
        if (Objects.nonNull(annotation) && Objects.equals(String.class, property.getType().getRawClass())) {
            this.strategy = annotation.strategy();
            return this;
        }
        return prov.findValueSerializer(property.getType());
    }

}
