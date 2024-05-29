package cc.elvea.platform.system.commons.handler;

import cc.elvea.platform.commons.web.handler.GlobalExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler extends GlobalExceptionHandler {
}
