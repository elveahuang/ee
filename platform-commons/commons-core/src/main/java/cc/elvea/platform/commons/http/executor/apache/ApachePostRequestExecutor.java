package cc.elvea.platform.commons.http.executor.apache;

import cc.elvea.platform.commons.constants.GlobalConstants;
import cc.elvea.platform.commons.http.Http;
import cc.elvea.platform.commons.http.HttpConfig;
import cc.elvea.platform.commons.http.HttpRequestType;
import cc.elvea.platform.commons.http.executor.HttpGetRequestExecutor;
import cc.elvea.platform.commons.http.utils.ApacheHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.util.Map;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class ApachePostRequestExecutor extends HttpGetRequestExecutor {

    public ApachePostRequestExecutor(HttpConfig config) {
        super(config);
    }

    @Override
    public String execute(final HttpRequestType type, final String uri, Map<String, String> paramMap, Map<String, String> headerMap) throws Exception {
        log.info("Apache post execute - [{}] - [{}] - start.", type, uri);
        HttpPost httpPost = new HttpPost(uri);
        ApacheHttpUtils.setRequestParams(httpPost, headerMap);
        switch (type) {
            case POST -> ApacheHttpUtils.setRequestParams(httpPost, paramMap);
            case JSON -> ApacheHttpUtils.setRequestJson(httpPost, paramMap);
        }
        return getHttpClient().execute(httpPost, response -> {
            String content = "";
            if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getEntity())) {
                content = EntityUtils.toString(response.getEntity(), GlobalConstants.UTF8);
                if (this.config.getDebug().isEnabled()) {
                    log.info("Apache post execute - [{}] - [{}] - response - [{}].", type, uri, content);
                }
            }
            log.info("Apache post execute - [{}] - [{}] - finish.", type, uri);
            return content;
        });
    }

    protected CloseableHttpClient getHttpClient() {
        return Http.getApacheHttpClient(this.config);
    }

}
