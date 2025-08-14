package top.baihu.platform.commons.core.http.executor;

import org.jetbrains.annotations.NotNull;
import top.baihu.platform.commons.core.http.HttpConfig;
import top.baihu.platform.commons.core.http.enums.HttpRequestTypeEnum;
import top.baihu.platform.commons.core.http.executor.apache.ApacheGetRequestExecutor;
import top.baihu.platform.commons.core.http.executor.okhttp.OkHttpGetRequestExecutor;

import java.util.Map;

/**
 * @author elvea
 */
public abstract class HttpGetRequestExecutor implements HttpExecutor<String, Map<String, String>> {

    protected HttpConfig config;

    public HttpGetRequestExecutor(HttpConfig config) {
        this.config = config;
    }

    @Override
    public String execute(String uri, Map<String, String> paramMap, Map<String, String> headerMap) throws Exception {
        return this.execute(HttpRequestTypeEnum.GET, uri, paramMap, headerMap);
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
