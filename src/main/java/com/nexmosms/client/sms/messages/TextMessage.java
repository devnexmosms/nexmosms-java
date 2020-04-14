package com.nexmosms.client.sms.messages;


import org.apache.http.client.methods.RequestBuilder;

public class TextMessage extends Message {
    private final String messageBody;

    private boolean unicode;

    public TextMessage(final String from,
                       final String to,
                       final String messageBody) {
        this(from, to, messageBody, false);
    }

    public TextMessage(final String from,
                       final String to,
                       final String messageBody,
                       final boolean unicode) {
        super(null, from, to);
        this.messageBody = messageBody;
        this.unicode = unicode;
    }

    public String getMessageBody() {
        return this.messageBody;
    }

    public boolean isUnicode() {
        return this.unicode;
    }

    @Override
    public MessageType getType() {
        if (unicode) {
            return MessageType.UNICODE;
        } else {
            return MessageType.TEXT;
        }
    }

    @Override
    public void addParams(RequestBuilder request) {
        super.addParams(request);
        request.addParameter("text", messageBody);
    }
}
