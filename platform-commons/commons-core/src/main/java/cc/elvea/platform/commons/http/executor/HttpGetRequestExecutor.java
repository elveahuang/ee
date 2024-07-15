package cc.elvea.platform.commons.http.executor;

import cc.elvea.platform.commons.http.HttpConfig;
import cc.elvea.platform.commons.http.HttpRequestType;
import cc.elvea.platform.commons.http.executor.apache.ApacheGetRequestExecutor;
import cc.elvea.platform.commons.http.executor.okhttp.OkHttpGetRequestExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author elvea
 * @since 24.1.0
 */
public abstract class HttpGetRequestExecutor implements HttpExecutor<String, Map<String, String>> {

    protected HttpConfig config;

    public HttpGetRequestExecutor(HttpConfig config) {
        this.config = config;
    }

    @Override
    public String execute(String uri, Map<String, String> paramMap, Map<String, String> headerMap) throws Exception {
        return this.execute(HttpRequestType.GET, uri, paramMap, headerMap);
    }

    public static HttpExecutor<String, Map<String, String>> create(HttpConfig config) {
        return switch (config.getType()) {
            case APACHE -> new ApacheGetRequestExecutor(config);
            case OKHTTP -> new OkHttpGetRequestExecutor(config);
        };
    }

    @NotNull
    public String handleResponse(String response) {
        return response;
    }

}
