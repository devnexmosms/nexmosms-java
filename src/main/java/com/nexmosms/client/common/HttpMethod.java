package com.nexmosms.client.common;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum HttpMethod {
    GET,
    POST,
    PUT,
    DELETE,
    PATCH,
    UNKNOWN;

    private static final Map<String, HttpMethod> HTTP_METHOD_INDEX = new HashMap<>();

    static {
        for (HttpMethod httpMethod : HttpMethod.values()) {
            HTTP_METHOD_INDEX.put(httpMethod.name(), httpMethod);
        }
    }

    @JsonCreator
    public static HttpMethod fromString(String name) {
        HttpMethod foundHttpMethod = HTTP_METHOD_INDEX.get(name.toUpperCase());
        return (foundHttpMethod != null) ? foundHttpMethod : UNKNOWN;
    }
}
