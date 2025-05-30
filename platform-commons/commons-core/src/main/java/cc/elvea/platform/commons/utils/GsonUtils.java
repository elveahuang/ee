package cc.elvea.platform.commons.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * @author elvea
 */
public abstract class GsonUtils {

    private static final Gson gson;

    static {
        gson = new GsonBuilder().create();
    }

    public static GsonBuilder builder() {
        return new GsonBuilder();
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T parse(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }

    public static <T> Map<String, T> toMap(String json) {
        return gson.fromJson(json, new TypeToken<Map<String, T>>() {
        }.getType());
    }

    public static <T> List<T> toList(String json, Class<T> cls) {
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    public static <T> List<Map<String, T>> toMapList(String json) {
        return gson.fromJson(json, new TypeToken<List<Map<String, T>>>() {
        }.getType());
    }

    public static boolean isNull(JsonElement element) {
        return element == null || element.isJsonNull();
    }

    public static boolean isNotNull(JsonElement element) {
        return !isNull(element);
    }

    public static String getAsString(JsonElement element) {
        return isNull(element) ? null : element.getAsString();
    }

    public static Integer getAsInteger(JsonElement element) {
        return isNull(element) ? null : element.getAsInt();
    }

    public static int getAsPrimitiveInt(JsonElement element) {
        Integer r = getAsInteger(element);
        return r == null ? 0 : r;
    }

}
