package com.second.common.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * jackson localDateTime 序列化
 * {@code @author}  JSY
 * {@code @date}    2023/4/7 18:00
 */
//@Configuration
public class LocalDateTimeSerializerConfig {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Value("${spring.jackson.date-format}")
    private String pattern;

    @Bean
    public LocalDateSerializer localDateSerializer() {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        Map<Class<?>, JsonSerializer<?>> serializers = new HashMap<>();
//        serializers.put(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
//        serializers.put(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
//        serializers.put(LocalTime.class, new LocalTimeSerializer(TIME_FORMATTER));
//
//        Map<Class<?>, JsonDeserializer<?>> deserializers = new HashMap<>();
//        deserializers.put(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
//        deserializers.put(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
//        deserializers.put(LocalTime.class, new LocalTimeDeserializer(TIME_FORMATTER));
//        return builder -> builder.serializersByType(serializers).deserializersByType(deserializers);

        return builder -> builder.serializerByType(LocalDate.class, localDateSerializer());
    }
}
