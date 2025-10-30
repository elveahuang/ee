package cc.wdev.platform.commons.annotations.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author elvea
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface MapStructMapper {

    @AliasFor(annotation = org.mapstruct.Mapper.class, attribute = "componentModel")
    String model() default MappingConstants.ComponentModel.DEFAULT;

}
