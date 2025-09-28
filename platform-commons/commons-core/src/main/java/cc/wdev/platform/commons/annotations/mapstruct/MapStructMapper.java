package cc.wdev.platform.commons.annotations.mapstruct;

import org.mapstruct.Mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(builder = @org.mapstruct.Builder(disableBuilder = true), componentModel = SPRING)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface MapStructMapper {
}
