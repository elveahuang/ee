package cn.elvea.platform;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AppServerApplication.class)
@Transactional
@Rollback(false)
public abstract class BaseTests {
}
