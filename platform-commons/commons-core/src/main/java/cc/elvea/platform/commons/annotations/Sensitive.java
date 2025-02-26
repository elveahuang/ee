package cc.elvea.platform.commons.annotations;

import cc.elvea.platform.commons.extensions.sensitive.SensitiveSerializer;
import cc.elvea.platform.commons.extensions.sensitive.SensitiveStrategy;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author elvea
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerializer.class)
public @interface Sensitive {

    SensitiveStrategy strategy();

}
