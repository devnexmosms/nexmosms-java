package com.nexmosms.client.sms;

import com.nexmosms.client.HttpWrapper;
import com.nexmosms.client.sms.messages.Message;

public class SmsClient {
    private SendMessageEndpoint message;

    public SmsClient(HttpWrapper httpWrapper) {
        this.message = new SendMessageEndpoint(httpWrapper);
    }

    public SmsSubmissionResponse submitMessage(Message message) throws Exception {
        return this.message.execute(message);
    }
}
