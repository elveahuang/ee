package cc.elvea.platform.commons.http;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.http.HttpConfig;
import cc.elvea.platform.commons.http.HttpManger;
import cc.elvea.platform.commons.http.HttpType;
import cc.elvea.platform.commons.http.executor.HttpExecutor;
import cc.elvea.platform.commons.http.executor.HttpGetRequestExecutor;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class HttpTests extends BaseTests {

    @Test
    public void baseTest() throws Exception {
        HttpExecutor<String, Map<String, String>> executor = HttpGetRequestExecutor.create(HttpManger.getHttp().getConfig());
        executor.execute("https://www.coingecko.com/zh", Maps.newHashMap(), response -> {
            log.info("google response: {}", response);
        });
    }

    @Test
    public void baseOkHttpTest() throws Exception {
        HttpConfig config = HttpConfig.builder().type(HttpType.OKHTTP).build();
        HttpExecutor<String, Map<String, String>> executor = HttpGetRequestExecutor.create(config);
        executor.execute("https://baidu.com", Maps.newHashMap(), response -> {
            log.info("okhttp response: {}", response);
        });
    }

    @Test
    public void baseApacheHttpTest() throws Exception {
        HttpConfig config = HttpConfig.builder().type(HttpType.APACHE).build();
        HttpExecutor<String, Map<String, String>> executor = HttpGetRequestExecutor.create(config);
        executor.execute("https://baidu.com", Maps.newHashMap(), response -> {
            log.info("apache response: {}", response);
        });
    }

}
