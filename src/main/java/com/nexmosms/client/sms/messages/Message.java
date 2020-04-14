package com.nexmosms.client.sms.messages;


import org.apache.http.client.methods.RequestBuilder;

public abstract class Message {
    public enum MessageType {
        TEXT,
        BINARY,
        WAPPUSH,
        UNICODE;

        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    private final MessageType type;
    private final String from;
    private final String to;

    private String clientReference;
    private boolean statusReportRequired;
    private MessageClass messageClass = null;
    private Long timeToLive = null;
    private String callbackUrl = null;

    protected Message(final MessageType type,
                      final String from,
                      final String to) {
        this(type, from, to, false);
    }

    protected Message(final MessageType type,
                      final String from,
                      final String to,
                      final boolean statusReportRequired) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.statusReportRequired = statusReportRequired;
    }

    public MessageType getType() {
        return this.type;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public String getClientReference() {
        return this.clientReference;
    }

    public void setClientReference(String clientReference) {
        if (clientReference.length() > 40) {
            throw new IllegalArgumentException("Client reference must be 40 characters or less.");
        }
        this.clientReference = clientReference;
    }

    public MessageClass getMessageClass() {
        return this.messageClass;
    }

    public void setMessageClass(MessageClass messageClass) {
        this.messageClass = messageClass;
    }

    public Long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public boolean getStatusReportRequired() {
        return this.statusReportRequired;
    }

    public void setStatusReportRequired(boolean statusReportRequired) {
        this.statusReportRequired = statusReportRequired;
    }

    public void addParams(RequestBuilder request) {
        request.addParameter("from", getFrom())
                .addParameter("to", getTo())
                .addParameter("type", getType().toString());
        if (statusReportRequired) {
            request.addParameter("status-report-req", "1");
        }
        if (clientReference != null) {
            request.addParameter("client-ref", clientReference);
        }
        if (timeToLive != null) {
            request.addParameter("ttl", timeToLive.toString());
        }
        if (callbackUrl != null) {
            request.addParameter("callback", callbackUrl);
        }
        if (messageClass != null) {
            request.addParameter("message-class", Integer.toString(messageClass.getMessageClass()));
        }
    }

    public enum MessageClass {
        CLASS_0(0),
        CLASS_1(1),
        CLASS_2(2),
        CLASS_3(3);

        private final int messageClass;

        MessageClass(int messageClass) {
            this.messageClass = messageClass;
        }

        public int getMessageClass() {
            return this.messageClass;
        }

    }
}
