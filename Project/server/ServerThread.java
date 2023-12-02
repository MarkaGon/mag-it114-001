package Project.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import Project.common.Constants;
import Project.common.Payload;
import Project.common.PayloadType;
import Project.common.RoomResultPayload;

/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private String clientName;
    private boolean isRunning = false;
    private ObjectOutputStream out;// exposed here for send()
    // private Server server;// ref to our server so we can call methods on it
    // more easily
    private Room currentRoom;
    private static Logger logger = Logger.getLogger(ServerThread.class.getName());
    private long myClientId;

    public void setClientId(long id) {
        myClientId = id;
    }

    public long getClientId() {
        return myClientId;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public ServerThread(Socket myClient, Room room) {
        logger.info("ServerThread created");
        // get communication channels to single client
        this.client = myClient;
        this.currentRoom = room;

    }

    protected void setClientName(String name) {
        if (name == null || name.isBlank()) {
            logger.warning("Invalid name being set");
            return;
        }
        clientName = name;
    }

    public String getClientName() {
        return clientName;
    }

    protected synchronized Room getCurrentRoom() {
        return currentRoom;
    }

    protected synchronized void setCurrentRoom(Room room) {
        if (room != null) {
            currentRoom = room;
        } else {
            logger.info("Passed in room was null, this shouldn't happen");
        }
    }

    public void disconnect() {
        sendConnectionStatus(myClientId, getClientName(), false);
        logger.info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }


    public boolean sendReadyStatus(long clientId) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.READY);
        p.setClientId(clientId);
        return send(p);
    }

    public boolean sendRoomName(String name) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.JOIN_ROOM);
        p.setMessage(name);
        return send(p);
    }

    public boolean sendRoomsList(String[] rooms, String message) {
        RoomResultPayload payload = new RoomResultPayload();
        payload.setRooms(rooms);
        if (message != null) {
            payload.setMessage(message);
        }
        return send(payload);
    }

    public boolean sendExistingClient(long clientId, String clientName) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.SYNC_CLIENT);
        p.setClientId(clientId);
        p.setClientName(clientName);
        return send(p);
    }

    public boolean sendResetUserList() {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.RESET_USER_LIST);
        return send(p);
    }

    public boolean sendClientId(long id) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.CLIENT_ID);
        p.setClientId(id);
        return send(p);
    }

    public boolean sendMessage(long clientId, String message) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.MESSAGE);
        p.setClientId(clientId);
        p.setMessage(message);
        return send(p);
    }

    public boolean sendConnectionStatus(long clientId, String who, boolean isConnected) {
        Payload p = new Payload();
        p.setPayloadType(isConnected ? PayloadType.CONNECT : PayloadType.DISCONNECT);
        p.setClientId(clientId);
        p.setClientName(who);
        // p.setMessage(isConnected ? "connected" : "disconnected");
        p.setMessage(String.format("%s the room %s", (isConnected ? "Joined" : "Left"), currentRoom.getName()));
        return send(p);
    }

    private boolean send(Payload payload) {
        try {
            logger.log(Level.FINE, "Outgoing payload: " + payload);
            out.writeObject(payload);
            logger.log(Level.INFO, "Sent payload: " + payload);
            return true;
        } catch (IOException e) {
            logger.info("Error sending message to client (most likely disconnected)");
            // uncomment this to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        } catch (NullPointerException ne) {
            logger.info("Message was attempted to be sent before outbound stream was opened: " + payload);
            // uncomment this to inspect the stack trace
            // e.printStackTrace();
            return true;// true since it's likely pending being opened
        }
    }

    // end send methods
    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            Payload fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (Payload) in.readObject()) != null // reads an object from inputStream (null would
                                                                     // likely mean a disconnect)
            ) {

                logger.info("Received from client: " + fromClient);
                processPayload(fromClient);

            } // close while loop
        } catch (Exception e) {
            // happens when client disconnects
            e.printStackTrace();
            logger.info("Client disconnected");
        } finally {
            isRunning = false;
            logger.info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }

    private String  processMessageEffects(String message) {
        // Scource material: https://stackoverflow.com/questions/36267354/java-string-replaceall-with-back-reference
        /* UCID: mag DATE: 11/28/23
        Source of this code in the link: Roughly explains using .replaceall and shows examples
        I coppied a bit from it but changed the commands 
        User will type the pattern and then the replaceall method will read that pattern
        and replace the text with the html tags around it 
        The color part is the same idea but will type out the color 
        bold, italics, underline*/
         message = message.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>")
                     .replaceAll("\\*(.*?)\\*", "<i>$1</i>")
                     .replaceAll("_(.*?)_", "<u>$1</u>");
        //  color
        message = message.replaceAll("\\[red\\](.*?)\\[/red\\]", "<font color='red'>$1</font>");
        message = message.replaceAll("\\[green\\](.*?)\\[/green\\]", "<font color='green'>$1</font>");
        message = message.replaceAll("\\[blue\\](.*?)\\[/blue\\]", "<font color='blue'>$1</font>");
    
        return message;
    }
/*UCID:mag DATE:11/28/23
 If less than 0.5, result becomes heads
 if greater, result becomes tails
 then send a message to everyone in the room
 with teh result
 */
    private void flipCoin() {
        String result = (Math.random() < 0.5) ? "Heads" : "Tails";
        currentRoom.sendMessage(this, "Flipped a coin and got: " + result);
    } 

/*UCID:mag DATE:11/28/23
 I used the same roll command from 
 our previous assignemnt.
 Use .split to see the fromat for rolling the dice
 .length allows us to take two different parts of the comman
 says how many dice we will roll and how many faces
 d must be in between both numbers
parseint will parse the string holding info of the dice
both value must be greater than 0
Math for rolling dice and some exceptition handling 
 */
    private void rollDice(String message) {
        String[] side = message.split(" ");
        if (side.length != 2) {
            currentRoom.sendMessage(this, "try again:");
            return;
        }

        String[] rolling = side[1].split("d");
        if (rolling.length != 2) {
            currentRoom.sendMessage(this, "try again");
            return;
        }
        try {
            int dice = Integer.parseInt(rolling[0]);
            int faces = Integer.parseInt(rolling[1]);
            if (dice <= 0 || faces <= 0) {
                currentRoom.sendMessage(this, "try again");
                return;
            }
    
            StringBuilder rollResult = new StringBuilder("Rolled a dice and got: ");
            for (int i = 0; i < dice; i++) {
                int roll = (int) (Math.random() * faces) + 1;
                rollResult.append(roll).append(" ");
            }
            currentRoom.sendMessage(this, rollResult.toString());
        } catch (NumberFormatException e) {
            currentRoom.sendMessage(this, "try again");
        }
    }


    void processPayload(Payload p) {
        switch (p.getPayloadType()) {
            case CONNECT:
                setClientName(p.getClientName());
                break;
            case DISCONNECT:
                Room.disconnectClient(this, getCurrentRoom());
                break;
                case MESSAGE:
                if (currentRoom != null) {
                    String message = p.getMessage().trim();
                    if (message.startsWith("/roll")) {
                        rollDice(message);
                    } else if (message.equals("/flip")) {
                        flipCoin();
                    } else {
                        String processedMessage = processMessageEffects(message);
                        currentRoom.sendMessage(this, processedMessage);
                    }
                } else {
                    logger.log(Level.INFO, "Migrating to lobby on message with null room");
                    Room.joinRoom(Constants.LOBBY, this);
                }
                break;
            case GET_ROOMS:
                Room.getRooms(p.getMessage().trim(), this);
                break;
            case CREATE_ROOM:
                Room.createRoom(p.getMessage().trim(), this);
                break;
            case JOIN_ROOM:
                Room.joinRoom(p.getMessage().trim(), this);
                break;
            default:
                break;

        }

    }

    private void cleanup() {
        logger.info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            logger.info("Client already closed");
        }
        logger.info("Thread cleanup() complete");
    }
}