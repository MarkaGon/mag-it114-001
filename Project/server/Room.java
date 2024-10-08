package Project.server;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import Project.common.Constants;

public class Room implements AutoCloseable {
    // protected static Server server;// used to refer to accessible server
    // functions
    private String name;
    protected List<ServerThread> clients = new ArrayList<ServerThread>();
    private boolean isRunning = false;

    // Commands
    private final static String COMMAND_TRIGGER = "/";
    private final static String FLIP = "flip";
	private final static String ROLL = "roll";
    private final static String MUTE = "mute";
	private final static String UNMUTE = "unmute";
    private final static String WHISPER = "whisper";
	private final static String CREATE_ROOM = "createroom";
	private final static String JOIN_ROOM = "joinroom";
	private final static String DISCONNECT = "disconnect";
	private final static String LOGOUT = "logout";
	private final static String LOGOFF = "logoff";
	
    private static Logger logger = Logger.getLogger(Room.class.getName());
    public static Server server;

    public Room(String name) {
        this.name = name;
        isRunning = true;
        loadMuteListFromFile();
    }
    public String getName() {
        return name;
    }
    public boolean isRunning() {
        return isRunning;
    }
    protected synchronized void addClient(ServerThread client) {
        if (!isRunning) {
            return;
        }
        client.setCurrentRoom(this);
        if (clients.indexOf(client) > -1) {
            logger.warning("Attempting to add client that already exists in room");
        } else {
            clients.add(client);
            client.sendResetUserList();
            syncCurrentUsers(client);
            sendConnectionStatus(client, true);
        }
    }
    protected synchronized void removeClient(ServerThread client) {
        if (!isRunning) {
            return;
        }
        // attempt to remove client from room
        try {
            clients.remove(client);
        } catch (Exception e) {
            logger.severe(String.format("Error removing client from room %s", e.getMessage()));
            e.printStackTrace();
        }
        // if there are still clients tell them this person left
        if (clients.size() > 0) {
            sendConnectionStatus(client, false);
        }
        checkClients();
    }
    private void syncCurrentUsers(ServerThread client) {
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread existingClient = iter.next();
            if (existingClient.getClientId() == client.getClientId()) {
                continue;// don't sync ourselves
            }
            boolean messageSent = client.sendExistingClient(existingClient.getClientId(),
                    existingClient.getClientName());
            if (!messageSent) {
                handleDisconnect(iter, existingClient);
                break;// since it's only 1 client receiving all the data, break if any 1 send fails
            }
        }
    }

    /***
     * Checks the number of clients.
     * If zero, begins the cleanup process to dispose of the room
     */
    private void checkClients() {
        // Cleanup if room is empty and not lobby
        if (!name.equalsIgnoreCase(Constants.LOBBY) && (clients == null || clients.size() == 0)) {
            close();
        }
    }

    /***
     * Helper function to process messages to trigger different functionality.
     * 
     * @param message The original message being sent
     * @param client  The sender of the message (since they'll be the ones
     *                triggering the actions)
     */
    @Deprecated // not used in my project as of this lesson, keeping it here in case things
                // change
    private boolean processCommands(String message, ServerThread client) {
        boolean wasCommand = false;
        try {
            if (message.startsWith(COMMAND_TRIGGER)) {
                String[] comm = message.split(COMMAND_TRIGGER);
                String part1 = comm[1];
                String[] comm2 = part1.split(" ");
                String command = comm2[0];
                String roomName;
                wasCommand = true;
                switch (command) {
                    case CREATE_ROOM:
                        roomName = comm2[1];
                        Room.createRoom(roomName, client);
                        break;
                    case JOIN_ROOM:
                        roomName = comm2[1];
                        Room.joinRoom(roomName, client);
                        break;
                    case DISCONNECT:
                    case LOGOUT:
                    case LOGOFF:
                        Room.disconnectClient(client, this);
                    break;
                        case FLIP:
						flip(client);
						break;
					case ROLL:
                        String rollCommand = comm2[1];
						rollDice(client, rollCommand);
						break;
                    case MUTE:
                        String userToMute = comm2[1];
                        muteUser(client, userToMute);
                        wasCommand = true;
                        break;
                    case UNMUTE:
                        String userToUnmute = comm2[1];
                        unmuteUser(client, userToUnmute);
                        wasCommand = true;
                        break;
                /*UCID:mag DATE:12/9/2023
                 * message is in whisperMessage going to the recipent 
                 * checks if user, used @ and stores teh targetuser in a list
                 * split text to take all the info we need and @ is the delimiter
                 * call on sendprivatemessage
                 */
                    case WHISPER:
                        String whisperMessage = message;
                        if (whisperMessage.indexOf("@") > -1) {
                            List<String> targetedUsers = new ArrayList<String>();
                            String[] splitMessage = whisperMessage.split("@");
                            for (int i = 0; i < splitMessage.length; i++) {
                                if (i % 2 != 0) {
                                    String[] usernameParts = splitMessage[i].split(" ");
                                    String username = usernameParts[0];
                                    targetedUsers.add(username);
                                }
                            }
                            sendPrivateMessage(client, targetedUsers, whisperMessage);
                        }
                        break;                    
					default:
						wasCommand = false;
						break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return wasCommand;
	}
    

    protected static void getRooms(String query, ServerThread client) {
        String[] rooms = Server.INSTANCE.getRooms(query).toArray(new String[0]);
        client.sendRoomsList(rooms,
                (rooms != null && rooms.length == 0) ? "No rooms found containing your query string" : null);
    }
    protected static void createRoom(String roomName, ServerThread client) {
        if (Server.INSTANCE.createNewRoom(roomName)) {
            Room.joinRoom(roomName, client);
        } else {
            client.sendMessage(Constants.DEFAULT_CLIENT_ID, String.format("Room %s already exists", roomName));
        }
    }
    /**
     * Will cause the client to leave the current room and be moved to the new room
     * if applicable
     * 
     * @param roomName
     * @param client
     */
    protected static void joinRoom(String roomName, ServerThread client) {
        if (!Server.INSTANCE.joinRoom(roomName, client)) {
            client.sendMessage(Constants.DEFAULT_CLIENT_ID, String.format("Room %s doesn't exist", roomName));
        }
    }
    protected static void disconnectClient(ServerThread client, Room room) {
        client.disconnect();
        room.removeClient(client);
    }
    // end command helper methods

    /***
     * Takes a sender and a message and broadcasts the message to all clients in
     * this room. Client is mostly passed for command purposes but we can also use
     * it to extract other client info.
     * 
     * @param sender  The client sending the message
     * @param message The message to broadcast inside the room
     */
    
    protected synchronized void sendConnectionStatus(ServerThread sender, boolean isConnected) {
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread receivingClient = iter.next();
            boolean messageSent = receivingClient.sendConnectionStatus(
                    sender.getClientId(),
                    sender.getClientName(),
                    isConnected);
            if (!messageSent) {
                handleDisconnect(iter, receivingClient);
            }
        }
    }
    protected void handleDisconnect(Iterator<ServerThread> iter, ServerThread client) {
        iter.remove();
        logger.info(String.format("Removed client %s", client.getClientName()));
        sendMessage(null, client.getClientName() + " disconnected");
        checkClients();
    }

    public void saveMuteListToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("muteList.txt"))) {
            outputStream.writeObject(mutedUser);                                    //ucid:mag date: 12/13/23
        } catch (IOException e) {
            logger.severe("Error saving mute list to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadMuteListFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("muteList.txt"))) {
            mutedUser = (List<String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.severe("Error loading mute list from file: " + e.getMessage());
        }
    }



    //UCID:mag DATE:12/9/2023
    private List<String> mutedUser = new ArrayList<>();
    private boolean muteCheck(String targetUsername, String mutedBy) {
        return mutedUser.contains(targetUsername.toLowerCase() + "_" + mutedBy.toLowerCase());
    }


    public synchronized void muteUser(ServerThread client, String targetUserName) {
        String user = getUser(targetUserName);
        if (user != null) {
            ServerThread targetUser = findClientByName(user);
            if (targetUser != null) {
                mutedUser.add(user.toLowerCase() + "_" + client.getClientName().toLowerCase());
                targetUser.sendMessage(Constants.DEFAULT_CLIENT_ID, "You have been muted by: " + client.getClientName() + "");
                client.sendMessage(Constants.DEFAULT_CLIENT_ID, "Muted user: " + user);
            } else {
                client.sendMessage(Constants.DEFAULT_CLIENT_ID, "User not found or already muted.");
            }
        } else {
            client.sendMessage(Constants.DEFAULT_CLIENT_ID, "Invalid username provided.");
        }
        saveMuteListToFile();
    }

    // ucid:mag date:11/13/23
    public synchronized void unmuteUser(ServerThread client, String targetUserName) {
        String username = getUser(targetUserName);
        if (username != null) {
            boolean removed = mutedUser.remove(username.toLowerCase() + "_" + client.getClientName().toLowerCase());
            if (removed) {
                ServerThread targetUser = findClientByName(username);
                if (targetUser != null) {
                    targetUser.sendMessage(Constants.DEFAULT_CLIENT_ID, "You have been unmuted by: " + client.getClientName() + "");
                    client.sendMessage(Constants.DEFAULT_CLIENT_ID, "Unmuted user: " + username);
                }
            } else {
                client.sendMessage(Constants.DEFAULT_CLIENT_ID, "User not found or not muted.");
            }
        } else {
            client.sendMessage(Constants.DEFAULT_CLIENT_ID, "Invalid username provided.");
        }
        saveMuteListToFile();
    }
    


    private String getUser(String targetUserName) {
        // Extracting username after @ symbol
        int atIndex = targetUserName.indexOf("@");
        if (atIndex != -1 && atIndex + 1 < targetUserName.length()) {
            return targetUserName.substring(atIndex + 1).trim().toLowerCase();
        }
        return null;
    }


    
    private ServerThread findClientByName(String targetUserName) {
        for (ServerThread client : clients) {
            if (client.getClientName().equalsIgnoreCase(targetUserName)) {
                return client;
            }
        }
        return null;
    }





    protected synchronized void sendMessage(ServerThread sender, String message) {
        if (!isRunning) {
            return;
        }
        logger.info(String.format("Sending message to %s clients", clients.size()));
        if (sender != null && processCommands(message, sender)) {
            return;
        }
        message = processTextFormatting(message);
        long from = sender == null ? Constants.DEFAULT_CLIENT_ID : sender.getClientId();
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread client = iter.next();
            if (muteCheck(sender.getClientName(), client.getClientName()) || muteCheck(client.getClientName(), sender.getClientName())) {
                continue;
            }
            boolean messageSent = client.sendMessage(from, message);
            if (!messageSent) {
                handleDisconnect(iter, client);
            }
        }
     }


     private String processTextFormatting(String message) {
        // Process bold
        message = message.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>");
    
        // Process italics
        message = message.replaceAll("\\*(.*?)\\*", "<i>$1</i>");
    
        // Process underline
        message = message.replaceAll("__(.*?)__", "<u>$1</u>");
    
        // Process color
        message = message.replaceAll("\\[red\\](.*?)\\[/red\\]", "<font color='red'>$1</font>");
        message = message.replaceAll("\\[green\\](.*?)\\[/green\\]", "<font color='green'>$1</font>");
        message = message.replaceAll("\\[blue\\](.*?)\\[/blue\\]", "<font color='blue'>$1</font>");
    
        return message;
    }


    
    /*UCID:mag DATE:12/9/2023
     * uses boolean type so it can generate true or false which can work
     * as true for heads and false for tails.
     * then sends message depedning if you get get true or false
     * then surround by the trigger for the boolean opperaters so the text is red
     */
    protected synchronized void flip(ServerThread sender) {
        Random random = new Random();
        boolean fResult = random.nextBoolean();
        String message;
        if (fResult) {
          message = " Flipped a coin and got HEADS";
        } else {
          message = " Flipped a coin and got Tails";
        }
        message = "[red]" + message + "[/red]";
        sendMessage(sender, processTextFormatting(message));
    }
      

    //UCID:mag DATE:12/9/2023
    protected synchronized void rollDice(ServerThread sender, String rollCommand) {
        if (rollCommand.matches("^\\d+(\\s-\\s\\d+)?$")) {
            //conditonal check based on how the command is formated
            String[] rangeParts = rollCommand.split(" - ");
            int minimum;
            int maximum;
            // for rolling a single dice or roll between two ranges
            if (rangeParts.length == 1) {
                minimum = 1;
                maximum = Integer.parseInt(rangeParts[0]);
            } else {
                minimum = Integer.parseInt(rangeParts[0]);
                maximum = Integer.parseInt(rangeParts[1]);
            }
            if (minimum > maximum) {
                sendMessage(sender, "[red] Invalid parameters [/red]");
                return;
            }
            int result = new Random().nextInt(maximum - minimum + 1) + minimum;
            String resultMessage = "[red] You rolled " + result + " out of " + (maximum - minimum + 1) + "[/red]";
            sendMessage(sender, processTextFormatting(resultMessage));

            //UCID:mag DATE:12/9/2023
        } else if (rollCommand.matches("^\\d+d\\d+$")) {
            //conditonal check based on how the command is formated
            String[] diceParts = rollCommand.split("d");
            int numberOfDice = Integer.parseInt(diceParts[0]);
            int sidesOnDice = Integer.parseInt(diceParts[1]);
            //takes in how many dice and sides od the dice
            //takes a result for each dice in the range
            if (numberOfDice <= 0 || sidesOnDice <= 0) {
                sendMessage(sender, "[red] Invalid parameters. [/red]");
                return;
            }

            // logic for rolling a dice and sending messages for results
            //wrapping everything we send with the short cuts of html scripts making it red
            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < numberOfDice; i++) {
                results.add(new Random().nextInt(sidesOnDice) + 1);
            }
            String resultMessage = "[red] You rolled ";
            for (int i = 0; i < results.size(); i++) {
                resultMessage += results.get(i);
                if (i < results.size() - 1) {
                    resultMessage += ", ";
                }
            }
            resultMessage += " from " + numberOfDice + "d" + sidesOnDice + "[/red]";
            sendMessage(sender, processTextFormatting(resultMessage));
        } else {
            sendMessage(sender, "[red] Invalid parameters [/red]");
        }
    }
    

    /*UCID:mag DATE:12/9/2023
     * checks list of clients and checks if the person your trying to whisper is in the list
     * checks if the user is muted and if not they get the message
     * also checks if sender is in the list 
     * 
    */
    protected synchronized void sendPrivateMessage(ServerThread sender, List<String> clientNames, String message) {
        long from = (sender == null) ? Constants.DEFAULT_CLIENT_ID : sender.getClientId();
    
        for (ServerThread client : clients) {
            if (clientNames.contains(client.getClientName().toLowerCase())) {
                if (!muteCheck(client.getClientName(), sender.getClientName())) {
                    boolean messageSent = client.sendMessage(from, message);
                    if (!messageSent) {
                    }
                }
            }
        }
        if (!clientNames.contains(sender.getClientName().toLowerCase())) {
            boolean senderMessageSent = sender.sendMessage(from, message);
            if (!senderMessageSent) {
            }
        }
    }



    public void close() {
        Server.INSTANCE.removeRoom(this);
        isRunning = false;
        clients.clear();
    }
}