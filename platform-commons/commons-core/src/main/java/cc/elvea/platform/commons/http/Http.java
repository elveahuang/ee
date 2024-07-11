package cc.elvea.platform.commons.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHost;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Http {

    @Builder.Default
    private HttpType type = HttpType.OKHTTP;

    @Builder.Default
    private HttpProxy proxy = HttpProxy.builder().build();

    /**
     * 获取配置
     */
    public HttpConfig getConfig() {
        return HttpConfig.builder().type(this.type).proxy(this.proxy).build();
    }

    public OkHttpClient getOkHttpClient() {
        return Http.getOkHttpClient(this.getConfig());
    }

    public CloseableHttpClient getApacheHttpClient() {
        return Http.getApacheHttpClient(this.getConfig());
    }

    //
    // 静态方法
    //

    public static OkHttpClient getOkHttpClient(HttpConfig config) {
        log.info("Creating OkHttpClient instance start.");
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (config.getProxy() != null && config.getProxy().isEnabled()) {
            log.info("Creating OkHttpClient instance. proxy [{}] [{}] enabled.", config.getProxy().getHost(), config.getProxy().getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(config.getProxy().getHost(), config.getProxy().getPort()));
            builder.proxy(proxy);
        }
        return builder.build();
    }

    public static CloseableHttpClient getApacheHttpClient(HttpConfig config) {
        log.info("Creating ApacheHttpClient instance start.");
        HttpClientBuilder builder = HttpClients.custom();
        if (config.getProxy() != null && config.getProxy().isEnabled()) {
            log.info("Creating ApacheHttpClient instance. proxy [{}] [{}] enabled.", config.getProxy().getHost(), config.getProxy().getPort());
            HttpHost proxy = new HttpHost(config.getProxy().getHost(), config.getProxy().getPort());
            builder.setProxy(proxy);
        }
        return builder.build();
    }

}
