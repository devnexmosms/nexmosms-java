package com.nexmosms.client.sms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsSubmissionResponseMessage {
    private String to;
    private String id;
    private MessageStatus status;
    private BigDecimal remainingBalance;
    private BigDecimal messagePrice;
    private String network;
    private String errorText;
    private String clientRef;

    @JsonProperty("to")
    public String getTo() {
        return this.to;
    }

    @JsonProperty("message-id")
    public String getId() {
        return this.id;
    }

    @JsonProperty("status")
    public MessageStatus getStatus() {
        return this.status;
    }

    @JsonProperty("error-text")
    public String getErrorText() {
        return this.errorText;
    }

    @JsonProperty("client-ref")
    public String getClientRef() {
        return this.clientRef;
    }

    @JsonProperty("remaining-balance")
    public BigDecimal getRemainingBalance() {
        return this.remainingBalance;
    }

    @JsonProperty("message-price")
    public BigDecimal getMessagePrice() {
        return this.messagePrice;
    }

    @JsonProperty("network")
    public String getNetwork() {
        return this.network;
    }

    public boolean isTemporaryError() {
        return this.status == MessageStatus.INTERNAL_ERROR || this.status == MessageStatus.TOO_MANY_BINDS
                || this.status == MessageStatus.THROTTLED;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }
}
