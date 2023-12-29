package com.second.common.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * jackson 序列化
 * {@code @author}  chou401
 * {@code @date}    2023/4/7 18:00
 */
@Configuration
public class JacksonSerializerConfig {

    @Value("${spring.jackson.date-format}")
    private String pattern;

    /**
     * 日期格式
     */
    @Bean
    public LocalDateSerializer localDateSerializer() {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 去除字符串前后空格
     */
    @Bean
    public StdScalarDeserializer<String> stdScalarDeserializer() {
        return new StdScalarDeserializer<>(String.class) {
            private static final long serialVersionUID = 1L;

            @Override
            public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return jsonParser.getValueAsString().strip();
            }
        };
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                .serializerByType(LocalDate.class, localDateSerializer())
                .deserializerByType(String.class, stdScalarDeserializer());
    }
}
