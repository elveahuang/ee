package cc.elvea.platform.commons.extensions.http;

import cc.elvea.platform.commons.extensions.http.enums.HttpClientTypeEnum;
import cc.elvea.platform.commons.utils.http.ApacheHttpUtils;
import cc.elvea.platform.commons.utils.http.OkHttpUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpHost;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class HttpFactory {

    @Builder.Default
    private HttpClientTypeEnum type = HttpClientTypeEnum.OKHTTP;

    @Builder.Default
    private HttpConfig.HttpDebug debug = HttpConfig.HttpDebug.builder().build();

    @Builder.Default
    private HttpConfig.HttpProxy proxy = HttpConfig.HttpProxy.builder().build();

    /**
     * 获取配置
     */
    public HttpConfig getConfig() {
        return HttpConfig.builder().debug(this.debug).type(this.type).proxy(this.proxy).build();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 静态方法
    // -----------------------------------------------------------------------------------------------------------------

    public static OkHttpClient getOkHttpClient(HttpConfig config) {
        log.info("Creating OkHttpClient instance start.");
        OkHttpClient.Builder builder = OkHttpUtils.getBuilder();
        if (config.getProxy() != null && config.getProxy().isEnabled()) {
            log.info("Creating OkHttpClient instance. proxy [{}] [{}] enabled.", config.getProxy().getHost(), config.getProxy().getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(config.getProxy().getHost(), config.getProxy().getPort()));
            builder.proxy(proxy);
        }
        return builder.build();
    }

    public static CloseableHttpClient getApacheHttpClient(HttpConfig config) {
        log.info("Creating ApacheHttpClient instance start.");
        HttpClientBuilder builder = ApacheHttpUtils.getBuilder();
        if (config.getProxy() != null && config.getProxy().isEnabled()) {
            log.info("Creating ApacheHttpClient instance. proxy [{}] [{}] enabled.", config.getProxy().getHost(), config.getProxy().getPort());
            HttpHost proxy = new HttpHost(config.getProxy().getHost(), config.getProxy().getPort());
            builder.setProxy(proxy);
        }
        return builder.build();
    }

}
