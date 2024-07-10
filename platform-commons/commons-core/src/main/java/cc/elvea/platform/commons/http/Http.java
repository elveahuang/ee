package cc.elvea.platform.commons.http;

import cc.elvea.platform.commons.utils.http.ApacheHttpUtils;
import cc.elvea.platform.commons.utils.http.OkHttpUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Http {

    @Builder.Default
    private HttpType type = HttpType.OKHTTP;

    @Builder.Default
    private HttpProxy proxy = HttpProxy.builder().build();

    public HttpConfig getConfig() {
        return HttpConfig.builder().type(this.type).build();
    }

    public OkHttpClient getOkHttpClient() {
        return Http.getOkHttpClient(this.getConfig());
    }

    public CloseableHttpClient getApacheHttpClient() {
        return Http.getApacheHttpClient(this.getConfig());
    }

    public static OkHttpClient getOkHttpClient(HttpConfig config) {
        return OkHttpUtils.getClient();
    }

    public static CloseableHttpClient getApacheHttpClient(HttpConfig config) {
        return ApacheHttpUtils.getClient();
    }

}
