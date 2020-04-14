package com.nexmosms.client.sms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsSubmissionResponse {
    @JsonProperty("message-count")
    private int messageCount;

    @JsonProperty("messages")
    private List<SmsSubmissionResponseMessage> messages;

    public static SmsSubmissionResponse fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, SmsSubmissionResponse.class);
    }

    public int getMessageCount() {
        return this.messageCount;
    }

    public List<SmsSubmissionResponseMessage> getMessages() {
        return this.messages;
    }
}
