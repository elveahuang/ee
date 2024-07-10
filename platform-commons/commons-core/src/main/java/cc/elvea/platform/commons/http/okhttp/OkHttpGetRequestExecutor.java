package cc.elvea.platform.commons.http.okhttp;

import cc.elvea.platform.commons.http.HttpConfig;
import cc.elvea.platform.commons.http.HttpUtils;
import cc.elvea.platform.commons.http.executor.HttpGetRequestExecutor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    public String execute(String uri, Map<String, String> paramMap) throws IOException {
        uri = this.handleQueryParam(uri, paramMap);

        log.info("OkHttpGetRequestExecutor.execute() - [{}] - start.", uri);
        OkHttpClient client = getHttpClient();
        Request request = new Request.Builder().url(uri).build();
        try (Response response = client.newCall(request).execute()) {
            String content = response.body().string();
            log.info("OkHttpGetRequestExecutor.execute() - [{}] - response - [{}].", uri, content);
            return this.handleResponse(content);
        } finally {
            log.info("OkHttpGetRequestExecutor.execute() - [{}] - finish.", uri);
        }
    }

    protected OkHttpClient getHttpClient() {
        return HttpUtils.getOkHttpClient(this.config);
    }

}
