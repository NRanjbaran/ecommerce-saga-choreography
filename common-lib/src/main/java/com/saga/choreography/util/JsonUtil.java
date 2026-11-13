package com.saga.choreography.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * Serialize/deserialize JSON for events across all services.
 */
@Slf4j
public final class JsonUtil {

    @Getter
    private static final JsonMapper objectMapper = JsonMapper.builder()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .build();

    private JsonUtil() {
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JacksonException e) {
            log.error("Failed to serialize object to JSON: {}", object.getClass().getName(), e);
            throw new IllegalStateException("JSON serialization failed", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JacksonException e) {
            log.error("Failed to deserialize JSON to {}: {}", clazz.getName(), json, e);
            throw new IllegalStateException("JSON deserialization failed", e);
        }
    }
}
