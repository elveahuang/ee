package cc.elvea.platform.commons.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Map;

/**
 * @author elvea
 */
public abstract class JacksonUtils {

    public final static JsonMapper cacheObjectMapper = JsonMapper.builder()
            .enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES)
            .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS)
            .enable(JsonReadFeature.ALLOW_MISSING_VALUES)
            .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .visibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)
            .build();

    private final static JsonMapper objectMapper = JsonMapper.builder()
            .enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES)
            .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS)
            .enable(JsonReadFeature.ALLOW_MISSING_VALUES)
            .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .visibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
            .addModule(new JavaTimeModule())
            .addModule(new Jdk8Module())
            .build();

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static ObjectMapper getCacheObjectMapper() {
        return cacheObjectMapper;
    }

    /**
     * 把对象转换成JSON文本
     */
    public static String toJson(Object obj) throws Exception {
        return getObjectMapper().writeValueAsString(obj);
    }

    /**
     * 把JSON文本转成对象
     */
    public static <T> T toObject(String json, Class<T> clazz) throws Exception {
        return getObjectMapper().readValue(json, clazz);
    }

    /**
     * 把JSON文本转成对象
     */
    public static <T> T toObject(String json, TypeReference<T> typeReference) throws Exception {
        return getObjectMapper().readValue(json, typeReference);
    }

    /**
     * 把JSON文本转成对象
     */
    public static Map<String, Object> toMap(String json) throws Exception {
        return getObjectMapper().readValue(json, new TypeReference<>() {
        });
    }

    /**
     *
     */
    public static byte[] toBytes(Object object) throws Exception {
        return getObjectMapper().writeValueAsBytes(object);
    }

    public static Long findLongValue(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return (value != null) ? value.asLong() : null;
    }

    public static boolean findBooleanValue(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return false;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return value != null && value.isTextual() && value.asBoolean();
    }

    public static String findStringValue(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return (value != null && value.isTextual()) ? value.asText() : null;
    }

    public static <T> T findValue(JsonNode jsonNode, String fieldName, TypeReference<T> valueTypeReference,
                                  ObjectMapper mapper) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return (value != null && value.isContainerNode()) ? mapper.convertValue(value, valueTypeReference) : null;
    }

    public static JsonNode findObjectNode(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return (value != null && value.isObject()) ? value : null;
    }

}
