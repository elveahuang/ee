package cn.elvea.platform.commons.core.utils;

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
 * @since 0.0.1
 */
public abstract class OkHttpUtils {

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private static final int CONNECT_TIMEOUT = 3 * 60 * 1000;
    private static final int READ_TIMEOUT = 3 * 60 * 1000;
    private static final int WRITE_TIMEOUT = 3 * 60 * 1000;

    private static final OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Get
    // -----------------------------------------------------------------------------------------------------------------

    public static Response get(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        HttpUrl.Builder builder = HttpUrl.get(url).newBuilder();
        if (MapUtils.isNotEmpty(params)) {
            params.forEach(builder::addQueryParameter);
        }
        Request.Builder request = new Request.Builder().url(builder.build()).get();
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }
        return execute(request.build());
    }

    public static Response get(String url, Map<String, String> params) throws Exception {
        return get(url, Maps.newHashMap(), params);
    }

    public static Response get(String url) throws Exception {
        return get(url, Maps.newHashMap());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Post
    // -----------------------------------------------------------------------------------------------------------------

    public static Response post(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        FormBody.Builder builder = new FormBody.Builder();
        if (MapUtils.isNotEmpty(params)) {
            params.forEach(builder::add);
        }
        Request.Builder request = new Request.Builder().url(url).post(builder.build());
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }
        return execute(request.build());
    }

    public static Response post(String url, Map<String, String> params) throws Exception {
        return post(url, Maps.newHashMap(), params);
    }

    public static Response post(String url) throws Exception {
        return post(url, Maps.newHashMap());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Post JSON
    // -----------------------------------------------------------------------------------------------------------------

    public static Response postJson(String url, Map<String, String> headers, String json) throws Exception {
        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request.Builder request = new Request.Builder().url(url).post(body);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }
        return execute(request.build());
    }

    public static Response postJson(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        return postJson(url, headers, GsonUtils.toJson(params));
    }

    public static Response postJson(String url, Map<String, String> params) throws Exception {
        return postJson(url, Collections.emptyMap(), params);
    }

    public static Response postJson(String url, String json) throws Exception {
        return postJson(url, Collections.emptyMap(), json);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Commons
    // -----------------------------------------------------------------------------------------------------------------

    public static Response execute(Request request) throws Exception {
        return client.newCall(request).execute();
    }

}
