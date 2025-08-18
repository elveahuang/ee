package cc.wdev.platform.system;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author elvea
 */
@Configuration
@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = {SystemServerApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class BaseTests {

    public final static Long DEFAULT_USER_ID = 1L;

    public final static Long DEFAULT_ORGANIZATION_ID = 1L;

    public final static Long DEFAULT_POSITION_ID = 1L;

}
