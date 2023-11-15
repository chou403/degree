package com.second.common.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * jackson json相关操作
 *
 * @author Administrator
 */
public class JsonHelper {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 忽略 null 值
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略对象中没有的值
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 设置全局的时间转化
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 解决时区差8小时问题
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        // localDateTime
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER));
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER.registerModule(javaTimeModule);

        OBJECT_MAPPER.findAndRegisterModules();
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * check if json object
     */
    public static boolean isJsonObject(String json) {
        try {
            OBJECT_MAPPER.readValue(json, Map.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * check if json array object
     */
    public static boolean isJsonArray(String json) {
        try {
            OBJECT_MAPPER.readValue(json, List.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * parse to object
     */
    public static <T> T parseToObject(InputStream is, Class<T> toClass) {
        try {
            return OBJECT_MAPPER.readValue(is, toClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * parse to object
     */
    public static <T> T parseToObject(InputStream is, TypeReference<T> type) {
        try {
            return OBJECT_MAPPER.readValue(is, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * parse to object
     */
    public static <T> T parseToObject(byte[] b, int offset, int len, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(b, offset, len, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * parse to object
     */
    public static <T> T parseToObject(byte[] b, int offset, int len, TypeReference<T> type) {
        try {
            return OBJECT_MAPPER.readValue(b, offset, len, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * parse to object
     */
    public static <T> T parseToObject(String json, Class<T> toClass) {
        try {
            return OBJECT_MAPPER.readValue(json, toClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 此方法可以用于复杂对象比如，List<Account>，其他方法返回的则是List<Map>
     */
    public static <T> T parseToObject(String json, TypeReference<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * parse to json
     */
    public static String parseToJson(Object o) {
        if (o == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * parse to map
     */
    public static Map parseToMap(String o) {
        if (o == null) {
            return null;
        }
        try {
            return parseToObject(o, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * parse to list
     */
    public static List parseToList(Object o) {
        if (o == null) {
            return null;
        }
        try {
            return parseToObject(parseToJson(o), List.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * object to dto
     */
    public static <T> T jsonToDto(Class<T> clazz, Object object) {
        return parseToObject(parseToJson(object), clazz);
    }

    /**
     * List<Map> to List<dto>
     */
    public static <T> List<T> listMapToDto(Class<T> clazz, List<Map> list) {
        return list
                .stream()
                .map(item -> JsonHelper.jsonToDto(clazz, item))
                .collect(Collectors.toList());
    }

}
