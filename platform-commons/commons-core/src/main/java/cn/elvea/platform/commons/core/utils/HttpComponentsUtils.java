package cn.elvea.platform.commons.core.utils;

import cn.elvea.platform.commons.core.constants.GlobalConstants;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpComponentsUtils
 * 基于HttpComponents封装网络请求相关的方法
 * <a href="https://hc.apache.org/">...</a>
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class HttpComponentsUtils {

    // -----------------------------------------------------------------------------------------------------------------
    // Get
    // -----------------------------------------------------------------------------------------------------------------

    public static String get(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(createURI(url, params)).build();
            setRequestHeader(httpGet, headers);
            return getHttpResponse(client, httpGet);
        }
    }

    public static String get(String url, Map<String, String> params) throws Exception {
        return get(url, new HashMap<>(), params);
    }

    public static String get(String url) throws Exception {
        return get(url, new HashMap<>(), new HashMap<>());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Post
    // -----------------------------------------------------------------------------------------------------------------

    public static String post(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            ClassicHttpRequest httpPost = ClassicRequestBuilder.post(createURI(url)).build();
            setRequestHeader(httpPost, headers);
            setRequestParams(httpPost, params);
            return getHttpResponse(client, httpPost);
        }
    }

    public static String post(String url, Map<String, String> params) throws Exception {
        return post(url, Maps.newHashMap(), params);
    }

    public static String post(String url) throws Exception {
        return post(url, Maps.newHashMap());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Post Json
    // -----------------------------------------------------------------------------------------------------------------

    public static String postJson(String url, Map<String, String> headers, Map<String, Object> params) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            ClassicHttpRequest httpPost = ClassicRequestBuilder.post(createURI(url)).build();
            setRequestHeader(httpPost, headers);
            setRequestJson(httpPost, params);
            return getHttpResponse(client, httpPost);
        }
    }

    public static String postJson(String url, Map<String, Object> params) throws Exception {
        return postJson(url, Maps.newHashMap(), params);
    }

    public static String postJson(String url) throws Exception {
        return postJson(url, Maps.newHashMap());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Put
    // -----------------------------------------------------------------------------------------------------------------

    public static String put(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            ClassicHttpRequest httpPut = ClassicRequestBuilder.post(createURI(url)).build();
            setRequestHeader(httpPut, headers);
            setRequestParams(httpPut, params);
            return getHttpResponse(client, httpPut);
        }
    }

    public static String put(String url, Map<String, String> params) throws Exception {
        return put(url, Maps.newHashMap(), params);
    }

    public static String put(String url) throws Exception {
        return put(url, Maps.newHashMap());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Delete
    // -----------------------------------------------------------------------------------------------------------------

    public static String delete(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            ClassicHttpRequest httpDelete = ClassicRequestBuilder.delete(createURI(url)).build();
            setRequestHeader(httpDelete, headers);
            setRequestParams(httpDelete, params);
            return getHttpResponse(client, httpDelete);
        }
    }

    public static String delete(String url, Map<String, String> params) throws Exception {
        return delete(url, Maps.newHashMap(), params);
    }

    public static String delete(String url) throws Exception {
        return delete(url, Maps.newHashMap());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Commons
    // -----------------------------------------------------------------------------------------------------------------

    private static URI createURI(String url) throws Exception {
        return createURI(url, Maps.newHashMap());
    }

    private static URI createURI(String url, Map<String, String> params) throws Exception {
        URIBuilder builder = new URIBuilder(url);
        if (!ObjectUtils.isEmpty(params)) {
            params.forEach(builder::setParameter);
        }
        return builder.build();
    }

    private static void setRequestHeader(ClassicHttpRequest httpRequest, Map<String, String> headers) {
        if (!ObjectUtils.isEmpty(headers)) {
            headers.forEach(httpRequest::setHeader);
        }
    }

    private static void setRequestParams(ClassicHttpRequest httpRequest, Map<String, String> params) {
        if (!ObjectUtils.isEmpty(params)) {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            params.forEach((key, value) -> nameValuePairs.add(new BasicNameValuePair(key, value)));
            httpRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs, GlobalConstants.UTF8));
        }
    }

    private static void setRequestJson(ClassicHttpRequest httpRequest, Map<String, Object> params) throws Exception {
        if (!ObjectUtils.isEmpty(params)) {
            String json = JacksonUtils.toJson(params);
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpRequest.setEntity(stringEntity);
        }
    }

    private static String getHttpResponse(HttpClient httpClient, ClassicHttpRequest httpRequest) throws Exception {
        return httpClient.execute(httpRequest, response -> {
            String resp = "";
            if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getEntity())) {
                resp = EntityUtils.toString(response.getEntity(), GlobalConstants.UTF8);
            }
            return resp;
        });
    }

    public static String createBearerAuthorization(String token) {
        return "Bearer " + token;
    }

}
