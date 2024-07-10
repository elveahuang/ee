package cc.elvea.platform.commons.http.executor;

import cc.elvea.platform.commons.http.HttpConfig;
import cc.elvea.platform.commons.http.ResponseHandler;
import cc.elvea.platform.commons.http.apache.ApacheGetRequestExecutor;
import cc.elvea.platform.commons.http.okhttp.OkHttpGetRequestExecutor;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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
    public void execute(String uri, Map<String, String> data, ResponseHandler<String> handler) throws IOException {
        handler.handle(this.execute(uri, data));
    }

    public static HttpExecutor<String, Map<String, String>> create(HttpConfig config) {
        return switch (config.getType()) {
            case APACHE -> new ApacheGetRequestExecutor(config);
            case OKHTTP -> new OkHttpGetRequestExecutor(config);
        };
    }

    public String handleQueryParam(String uri, Map<String, String> paramMap) {
        if (MapUtils.isNotEmpty(paramMap) && StringUtils.isNotEmpty(uri)) {
            if (uri.indexOf('?') == -1) {
                uri += '?';
            }
            StringBuilder sb = new StringBuilder(uri);
            for (String key : paramMap.keySet()) {
                sb.append(sb.toString().endsWith("?") ? key : '&' + ObjectUtils.nvl(paramMap.get(key), ""));
            }
            uri = sb.toString();
        }
        return uri;
    }

    @NotNull
    public String handleResponse(String response) {
        return response;
    }

}
