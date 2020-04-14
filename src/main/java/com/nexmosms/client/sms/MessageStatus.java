package com.nexmosms.client.sms;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum MessageStatus {
    OK(0),
    THROTTLED(1),
    MISSING_PARAMS(2),
    INVALID_PARAMS(3),
    INVALID_CREDENTIALS(4),
    INTERNAL_ERROR(5),
    INVALID_MESSAGE(6),
    NUMBER_BARRED(7),
    PARTNER_ACCOUNT_BARRED(8),
    PARTNER_QUOTA_EXCEEDED(9),
    TOO_MANY_BINDS(10),
    ACCOUNT_NOT_HTTP(11),
    MESSAGE_TOO_LONG(12),
    COMMS_FAILURE(13),
    INVALID_SIGNATURE(14),
    INVALID_FROM_ADDRESS(15),
    INVALID_TTL(16),
    NUMBER_UNREACHABLE(17),
    TOO_MANY_DESTINATIONS(18),
    FACILITY_NOT_ALLOWED(19), INVALID_MESSAGE_CLASS(20), UNKNOWN(Integer.MAX_VALUE);

    private int messageStatus;

    private static final Map<Integer, MessageStatus> MESSAGE_STATUS_INDEX = new HashMap<>();

    static {
        for (MessageStatus messageStatus : MessageStatus.values()) {
            MESSAGE_STATUS_INDEX.put(messageStatus.messageStatus, messageStatus);
        }
    }
    
    @JsonCreator
    public static MessageStatus fromInt(int messageStatus) {
        MessageStatus foundStatus = MESSAGE_STATUS_INDEX.get(messageStatus);
        return (foundStatus != null) ? foundStatus : UNKNOWN;
    }

    MessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public int getMessageStatus() {
        return this.messageStatus;
    }
}
