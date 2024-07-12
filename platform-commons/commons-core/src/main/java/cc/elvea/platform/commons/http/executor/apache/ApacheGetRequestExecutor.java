package cc.elvea.platform.commons.http.executor.apache;

import cc.elvea.platform.commons.constants.GlobalConstants;
import cc.elvea.platform.commons.http.Http;
import cc.elvea.platform.commons.http.HttpConfig;
import cc.elvea.platform.commons.http.HttpRequestType;
import cc.elvea.platform.commons.http.executor.HttpGetRequestExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
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
    public String execute(HttpRequestType type, final String uri, Map<String, String> paramMap, Map<String, String> headerMap) throws IOException {
        final String url = this.handleQueryParam(uri, paramMap);

        log.info("ApacheGetRequestExecutor.execute() - [{}] - start.", url);
        HttpGet httpGet = new HttpGet(url);
        if (MapUtils.isNotEmpty(headerMap)) {
            headerMap.forEach(httpGet::addHeader);
        }
        return getHttpClient().execute(httpGet, response -> {
            String content = "";
            if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getEntity())) {
                content = EntityUtils.toString(response.getEntity(), GlobalConstants.UTF8);
                log.info("ApacheGetRequestExecutor.execute() - [{}] - response - [{}].", url, content);
            }
            log.info("ApacheGetRequestExecutor.execute() - [{}] - start.", url);
            return content;
        });
    }

    protected CloseableHttpClient getHttpClient() {
        return Http.getApacheHttpClient(this.config);
    }

}
