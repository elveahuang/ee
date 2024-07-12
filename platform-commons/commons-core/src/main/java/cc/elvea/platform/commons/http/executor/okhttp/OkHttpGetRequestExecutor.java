package cc.elvea.platform.commons.http.executor.okhttp;

import cc.elvea.platform.commons.http.Http;
import cc.elvea.platform.commons.http.HttpConfig;
import cc.elvea.platform.commons.http.HttpRequestType;
import cc.elvea.platform.commons.http.executor.HttpGetRequestExecutor;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class OkHttpGetRequestExecutor extends HttpGetRequestExecutor {

    public OkHttpGetRequestExecutor(HttpConfig config) {
        super(config);
    }

    @Override
    public String execute(HttpRequestType type, String uri, Map<String, String> paramMap, Map<String, String> headerMap) throws IOException {
        final String url = this.handleQueryParam(uri, paramMap);

        log.info("OkHttpGetRequestExecutor.execute() - [{}] - start.", url);
        OkHttpClient client = getHttpClient();
        Headers headers = MapUtils.isNotEmpty(headerMap) ? Headers.of(headerMap) : Headers.of(Maps.newHashMap());
        Request request = new Request.Builder().url(url).headers(headers).build();
        try (Response response = client.newCall(request).execute()) {
            String content = response.body().string();
            log.info("OkHttpGetRequestExecutor.execute() - [{}] - response - [{}].", url, content);
            return this.handleResponse(content);
        } finally {
            log.info("OkHttpGetRequestExecutor.execute() - [{}] - finish.", url);
        }
    }

    protected OkHttpClient getHttpClient() {
        return Http.getOkHttpClient(this.config);
    }

}
