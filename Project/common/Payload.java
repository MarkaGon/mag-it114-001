package Project.common;

import java.io.Serializable;

public class Payload implements Serializable {
    private static final long serialVersionUID = 2L;
/* UCID: mag  DATE: 11/28/23
change the serialVersionUID changed becasue 
class structure changed, make sure that the nothing
will be deserialized and keeps compatibilty.
*/


    private PayloadType payloadType; //holds commands
    private String clientName; //name of client
    private long clientId;// help manage lcients

    private String message;// data sent to clients
    private String sender;/* username of sender, Now in
                            toString showing this info*/

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
/*UCID: mag DATE: 11/28/23 
 added Sender to the toString so it will displays
 on the server side of our application */
    @Override 
    public String toString() {
        return String.format("Type[%s], Sender[%s], ClientId[%s], ClientName[%s], Message[%s]",
                getPayloadType().toString(), getSender(), getClientId(), getClientName(), getMessage());
    }
}