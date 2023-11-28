package Project.common;

import java.io.Serializable;

public class Payload implements Serializable {
    private static final long serialVersionUID = 2L;

    private PayloadType payloadType;
    private String clientName;
    private long clientId;

    private String message;
    private String sender; // New field to hold sender's username

    public Payload() {
    }

    public PayloadType getPayloadType() {
        return payloadType;
    }

    public String getClientName() {
        return clientName;
    }
    
    public long getClientId() {
        return clientId;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getSender() {
        return sender;
    }
    
    public void setPayloadType(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return String.format("Type[%s], Sender[%s], ClientId[%s], ClientName[%s], Message[%s]",
                getPayloadType().toString(), getSender(), getClientId(), getClientName(), getMessage());
    }
}