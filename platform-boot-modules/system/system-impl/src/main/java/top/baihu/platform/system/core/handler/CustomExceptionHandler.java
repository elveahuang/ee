package top.baihu.platform.system.core.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.baihu.platform.commons.web.handler.GlobalExceptionHandler;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler extends GlobalExceptionHandler {
}
