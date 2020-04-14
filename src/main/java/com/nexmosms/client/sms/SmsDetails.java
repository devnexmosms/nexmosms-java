package com.nexmosms.client.sms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsDetails {
    private String messageId;
    private String accountId;
    private String network;
    private String from;
    private String to;
    private String body;
    private String price;
    private Date dateReceived;
    private String finalStatus;
    private Date dateClosed;
    private Integer latency;
    private String type;

    @JsonProperty("message-id")
    public String getMessageId() {
        return messageId;
    }

    @JsonProperty("account-id")
    public String getAccountId() {
        return accountId;
    }

    public String getNetwork() {
        return network;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBody() {
        return body;
    }

    public String getPrice() {
        return price;
    }

    @JsonProperty("date-received")
    public Date getDateReceived() {
        return dateReceived;
    }

    @JsonProperty("final-status")
    public String getFinalStatus() {
        return finalStatus;
    }

    @JsonProperty("date-closed")
    public Date getDateClosed() {
        return dateClosed;
    }

    public Integer getLatency() {
        return latency;
    }

    public String getType() {
        return type;
    }
}
