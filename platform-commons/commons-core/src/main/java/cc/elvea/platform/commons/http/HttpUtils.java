package cc.elvea.platform.commons.http;

import cc.elvea.platform.commons.utils.http.ApacheHttpUtils;
import okhttp3.OkHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author elvea
 * @since 24.1.0
 */
public final class HttpUtils {

    public static OkHttpClient getOkHttpClient(HttpConfig config) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (config.getProxy() != null && config.getProxy().isEnabled()) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(config.getProxy().getHost(), config.getProxy().getPort()));
            builder.proxy(proxy);
        }
        return builder.build();
    }

    public static CloseableHttpClient getApacheHttpClient(HttpConfig config) {
        return ApacheHttpUtils.getClient();
    }

}
