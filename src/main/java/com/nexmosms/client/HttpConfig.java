package com.nexmosms.client;

public class HttpConfig {
    private static final String DEFAULT_REST_BASE_URI = "https://rest.dartsms.com";

    private String restBaseUri;

    private HttpConfig(Builder builder) {
        this.restBaseUri = builder.restBaseUri;
    }

    public String getRestBaseUri() {
        return restBaseUri;
    }

    public String getVersionedRestBaseUri(String version) {
        return appendVersionToUri(restBaseUri, version);
    }

    private String appendVersionToUri(String uri, String version) {
        return uri + "/" + version;
    }

    public static HttpConfig defaultConfig() {
        return new Builder().build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String restBaseUri;

        public Builder() {
            this.restBaseUri = DEFAULT_REST_BASE_URI;
        }

        public Builder baseUri(String baseUri) {
            String sanitizedUri = sanitizeUri(baseUri);
            this.restBaseUri = sanitizedUri;
            return this;
        }

        public HttpConfig build() {
            return new HttpConfig(this);
        }

        private String sanitizeUri(String uri) {
            if (uri != null && uri.endsWith("/")) {
                return uri.substring(0, uri.length() - 1);
            }

            return uri;
        }
    }
}
