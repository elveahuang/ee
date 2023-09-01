package cn.elvea.platform.system.commons.handler;

import cn.elvea.platform.commons.core.web.handler.GlobalExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class CustomGlobalExceptionHandler extends GlobalExceptionHandler {
}
