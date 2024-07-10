package cc.elvea.platform.commons.http.apache;

import cc.elvea.platform.commons.constants.GlobalConstants;
import cc.elvea.platform.commons.http.HttpConfig;
import cc.elvea.platform.commons.http.HttpUtils;
import cc.elvea.platform.commons.http.executor.HttpGetRequestExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class ApacheGetRequestExecutor extends HttpGetRequestExecutor {

    public ApacheGetRequestExecutor(HttpConfig config) {
        super(config);
    }

    @Override
    public String execute(String uri, Map<String, String> paramMap) throws IOException {
        final String url = this.handleQueryParam(uri, paramMap);

        log.info("ApacheGetRequestExecutor.execute() - [{}] - start.", url);
        HttpGet httpGet = new HttpGet(uri);
        return getHttpClient().execute(httpGet, response -> {
            String content = "";
            if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getEntity())) {
                content = EntityUtils.toString(response.getEntity(), GlobalConstants.UTF8);
                log.info("ApacheGetRequestExecutor.execute() - [{}] - response - [{}].", uri, content);
            }
            log.info("ApacheGetRequestExecutor.execute() - [{}] - start.", url);
            return content;
        });
    }

    protected CloseableHttpClient getHttpClient() {
        return HttpUtils.getApacheHttpClient(this.config);
    }

}
