package cc.elvea.platform.commons.http.utils;

import cc.elvea.platform.commons.utils.GsonUtils;
import com.google.common.collect.Maps;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * OkHttpUtils
 * 基于OkHttp封装网络请求相关的方法
 * <a href="https://square.github.io/okhttp/">...</a>
 *
 * @author elvea
 * @since 24.1.0
 */
public abstract class OkHttpUtils {

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private static final int CONNECT_TIMEOUT = 3 * 60 * 1000;
    private static final int READ_TIMEOUT = 3 * 60 * 1000;
    private static final int WRITE_TIMEOUT = 3 * 60 * 1000;

    // -----------------------------------------------------------------------------------------------------------------
    // Get
    // -----------------------------------------------------------------------------------------------------------------

    public static Response get(String uri, Map<String, String> headers, Map<String, String> params) throws Exception {
        HttpUrl.Builder builder = HttpUrl.get(uri).newBuilder();
        if (MapUtils.isNotEmpty(params)) {
            params.forEach(builder::addQueryParameter);
        }
        Request.Builder request = new Request.Builder().url(builder.build()).get();
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }
        return execute(request.build());
    }

    public static Response get(String uri, Map<String, String> params) throws Exception {
        return get(uri, Maps.newHashMap(), params);
    }

    public static Response get(String uri) throws Exception {
        return get(uri, Maps.newHashMap());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Post
    // -----------------------------------------------------------------------------------------------------------------

    public static Response post(String uri, Map<String, String> headers, Map<String, String> params) throws Exception {
        FormBody.Builder builder = new FormBody.Builder();
        if (MapUtils.isNotEmpty(params)) {
            params.forEach(builder::add);
        }
        Request.Builder request = new Request.Builder().url(uri).post(builder.build());
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }
        return execute(request.build());
    }

    public static Response post(String uri, Map<String, String> params) throws Exception {
        return post(uri, Maps.newHashMap(), params);
    }

    public static Response post(String uri) throws Exception {
        return post(uri, Maps.newHashMap());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Post JSON
    // -----------------------------------------------------------------------------------------------------------------

    public static Response postJson(String uri, Map<String, String> headers, String json) throws Exception {
        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request.Builder request = new Request.Builder().url(uri).post(body);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }
        return execute(request.build());
    }

    public static Response postJson(String uri, Map<String, String> headers, Map<String, String> params) throws Exception {
        return postJson(uri, headers, GsonUtils.toJson(params));
    }

    public static Response postJson(String uri, Map<String, String> params) throws Exception {
        return postJson(uri, Collections.emptyMap(), params);
    }

    public static Response postJson(String uri, String json) throws Exception {
        return postJson(uri, Collections.emptyMap(), json);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Commons
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 获取客户端构建器
     */
    public static OkHttpClient.Builder getBuilder() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取客户端
     */
    public static OkHttpClient getClient() {
        return getBuilder().build();
    }

    /**
     * 执行请求
     */
    public static Response execute(Request request) throws Exception {
        return execute(getClient(), request);
    }

    /**
     * 执行请求
     */
    public static Response execute(OkHttpClient client, Request request) throws Exception {
        return client.newCall(request).execute();
    }

}
