package cc.elvea.platform.commons.http.executor;

import cc.elvea.platform.commons.http.HttpConfig;
import cc.elvea.platform.commons.http.HttpRequestType;
import cc.elvea.platform.commons.http.executor.apache.ApachePostRequestExecutor;
import cc.elvea.platform.commons.http.executor.okhttp.OkHttpPostRequestExecutor;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author elvea
 * @since 24.1.0
 */
public abstract class HttpPostRequestExecutor implements HttpExecutor<String, Map<String, String>> {

    protected HttpConfig config;

    public HttpPostRequestExecutor(HttpConfig config) {
        this.config = config;
    }

    @Override
    public String execute(String uri, Map<String, String> paramMap, Map<String, String> headerMap) throws Exception {
        return this.execute(HttpRequestType.POST, uri, paramMap, headerMap);
    }

    public static HttpExecutor<String, Map<String, String>> create(HttpConfig config) {
        return switch (config.getType()) {
            case APACHE -> new ApachePostRequestExecutor(config);
            case OKHTTP -> new OkHttpPostRequestExecutor(config);
        };
    }

    public String handleQueryParam(String uri, Map<String, String> paramMap) {
        if (MapUtils.isNotEmpty(paramMap) && StringUtils.isNotEmpty(uri)) {
            if (uri.indexOf('?') == -1) {
                uri += '?';
            }
            StringBuilder sb = new StringBuilder(uri);
            for (String key : paramMap.keySet()) {
                String param = key + '=' + ObjectUtils.nvl(paramMap.get(key), "");
                sb.append(sb.toString().endsWith("?") ? param : '&' + param);
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
