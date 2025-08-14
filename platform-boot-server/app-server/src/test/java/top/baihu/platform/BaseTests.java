package top.baihu.platform;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import top.baihu.platform.app.AppServerApplication;

/**
 * @author elvea
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AppServerApplication.class)
@Transactional
@Rollback(false)
public abstract class BaseTests {
}
