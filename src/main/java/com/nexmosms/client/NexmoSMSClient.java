package com.nexmosms.client;

import com.nexmosms.client.auth.*;
import com.nexmosms.client.sms.SmsClient;
import org.apache.http.client.HttpClient;

/**
 * Please check nexmosms for documentations
 */
public class NexmoSMSClient {
    private SmsClient sms;
    private HttpWrapper httpWrapper;

    private NexmoSMSClient(Builder builder) {
        this.httpWrapper = new HttpWrapper(builder.httpConfig, builder.authMethod);
        this.httpWrapper.setHttpClient(builder.httpClient);
        this.sms = new SmsClient(this.httpWrapper);
    }

    public SmsClient getSmsClient() {
        return this.sms;
    }

    HttpWrapper getHttpWrapper() {
        return httpWrapper;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private TokenAuthMethod authMethod;
        private HttpConfig httpConfig = HttpConfig.defaultConfig();
        private HttpClient httpClient;
        private String applicationId;
        private String apiKey;
        private String apiSecret;

        public Builder httpConfig(HttpConfig httpConfig) {
            this.httpConfig = httpConfig;
            return this;
        }

        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public Builder applicationId(String applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder apiSecret(String apiSecret) {
            this.apiSecret = apiSecret;
            return this;
        }

        public NexmoSMSClient build() {
            validateAuthParameters(
                this.applicationId,
                this.apiKey,
                this.apiSecret
            );
            authMethod = new TokenAuthMethod(this.apiKey, this.apiSecret);
            return new NexmoSMSClient(this);
        }

        private void validateAuthParameters(String applicationId, String key, String secret) {
            if (secret != null && key == null) {
                throw new IllegalStateException("You must provide an API key in addition to your API secret.");
            }
        }
    }
}
