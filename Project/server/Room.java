package Project.server;
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

    private List<String> mutedUsers = new ArrayList<>();

    private boolean isMuted(String targetUsername, String mutedByUsername) {
        return mutedUsers.contains(targetUsername.toLowerCase() + "_" + mutedByUsername.toLowerCase());
    }
    
    public synchronized void muteUser(ServerThread client, String targetUserName) {
        String username = extractUsername(targetUserName);
        if (username != null) {
            ServerThread targetUser = findClientByName(username);
            if (targetUser != null) {
                mutedUsers.add(username.toLowerCase() + "_" + client.getClientName().toLowerCase());
                targetUser.sendMessage(Constants.DEFAULT_CLIENT_ID, "You have been muted.");
                client.sendMessage(Constants.DEFAULT_CLIENT_ID, "Muted user: " + username);
            } else {
                client.sendMessage(Constants.DEFAULT_CLIENT_ID, "User not found or already muted.");
            }
        } else {
            client.sendMessage(Constants.DEFAULT_CLIENT_ID, "Invalid username provided.");
        }
    }
    
    public synchronized void unmuteUser(ServerThread client, String targetUserName) {
        String username = extractUsername(targetUserName);
        if (username != null) {
            boolean removed = mutedUsers.remove(username.toLowerCase() + "_" + client.getClientName().toLowerCase());
            if (removed) {
                ServerThread targetUser = findClientByName(username);
                if (targetUser != null) {
                    targetUser.sendMessage(Constants.DEFAULT_CLIENT_ID, "You have been unmuted.");
                    client.sendMessage(Constants.DEFAULT_CLIENT_ID, "Unmuted user: " + username);
                }
            } else {
                client.sendMessage(Constants.DEFAULT_CLIENT_ID, "User not found or not muted.");
            }
        } else {
            client.sendMessage(Constants.DEFAULT_CLIENT_ID, "Invalid username provided.");
        }
    }
    
    private String extractUsername(String targetUserName) {
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
						roll(client, rollCommand);
						break;
                    case "MUTE":
                        String userToMute = comm2[1];
                        muteUser(client, userToMute);
                        wasCommand = true;
                        break;
                    case "UNMUTE":
                        String userToUnmute = comm2[1];
                        unmuteUser(client, userToUnmute);
                        wasCommand = true;
                        break;
					case "PM":
						String y = message;
						if(y.indexOf("@")>-1)
						{
							List<String> g = new ArrayList<String>();
							String[] t = y.split("@");
							for(int i=0;i<t.length;i++){
								if(i%2!=0)
								{
									String[]d=t[i].split(" ");
									String u = d[0];
									g.add(u);
								}
							}
							sendPrivateMessage(client, g, message);
						}
						break;                      
					default:
						wasCommand = false;
						break;
				}
			}
			if (!message.startsWith(COMMAND_TRIGGER)) {
				String m = message;
				List<String> clientss = new ArrayList<String>();
				if (m.indexOf("@") > -1) {
					String arr[] = m.split("@");
					String clientName = "";
					for (int i = 0; i < arr.length; i++) {
						if (i > 0) {
							clientName = clientName.trim().toLowerCase();
							clientss.add(clientName);
						}
					}
					sendPrivateMessage(client, clientss, message);	
				}
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return wasCommand;
		// return response;
	}

    // Command helper methods
    // Command helper methods
	protected String change(boolean change, String msg, String delimiter, String tag, String end) {
		String[] splitmsg = msg.split(delimiter);
		String temp = "";
		for (int i = 0; i < splitmsg.length; i++) {
			if (i % 2 == 0) {temp += splitmsg[i];
			} else {
				temp += tag + splitmsg[i] + end;
			}
		}

		return temp;
	}

	
    
    // Method to replace the triggers in the message with the HTML tags
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
    protected synchronized void sendMessage(ServerThread sender, String message) {
        if (!isRunning) {
            return;
        }
        logger.info(String.format("Sending message to %s clients", clients.size()));
        
        if (sender != null && processCommands(message, sender)) {
            // it was a command, don't broadcast
            return;
        }
        
        // Apply text formatting to the message
        message = processTextFormatting(message);
        
        long from = sender == null ? Constants.DEFAULT_CLIENT_ID : sender.getClientId();
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread client = iter.next();
            // Check if the sender or the receiver is muted
            if (isMuted(sender.getClientName(), client.getClientName()) || isMuted(client.getClientName(), sender.getClientName())) {
                continue; // Skip this client if either is muted
            }
            boolean messageSent = client.sendMessage(from, message);
            if (!messageSent) {
                handleDisconnect(iter, client);
            }
        }
     }
     
    

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
    
    protected synchronized void flip(ServerThread sender) {
        Random random = new Random();
        boolean isHeads = random.nextBoolean();
      
        String message;
        if (isHeads) {
          message = " Flipped a coin and got HEADS";
        } else {
          message = " Flipped a coin and got Tails";
        }

        message = "[red]" + message + "[/red]";
        sendMessage(sender, processTextFormatting(message));
      }
      

    protected synchronized void roll(ServerThread sender, String rollCommand) {
        // Check for format 1: /roll 0 - X or 1 - X
        if (rollCommand.matches("^\\d+(\\s-\\s\\d+)?$")) {
            // Extract min and max values
            String[] parts = rollCommand.split(" - ");
            int min;
            int max;
            if (parts.length == 1) {
                min = 1;
                max = Integer.parseInt(parts[0]);
            } else {
                min = Integer.parseInt(parts[0]);
                max = Integer.parseInt(parts[1]);
            }
     
            if (min > max) {
                sendMessage(sender, "[red] Invalid roll command. The minimum value must be less than or equal to the maximum value. [/red]");
                return;
            }
     
            // Generate a random number within the range
            int result = new Random().nextInt(max - min + 1) + min;
     
            // Format and send the result message
            String resultMessage = "[red] You rolled " + result + " out of " + (max - min + 1) + "[/red]";
            sendMessage(sender, processTextFormatting(resultMessage));
        }
        // Check for format 2: /roll #d#
        else if (rollCommand.matches("^\\d+d\\d+$")) {
            // Extract number of dice and sides
            String[] parts = rollCommand.split("d");
            int numDice = Integer.parseInt(parts[0]);
            int sides = Integer.parseInt(parts[1]);
     
            if (numDice <= 0 || sides <= 0) {
                sendMessage(sender, "[red] Invalid dice parameters. Please enter valid values for the dice roll. [/red]");
                return;
            }
     
            // Roll the dice and generate results
            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < numDice; i++) {
                results.add(new Random().nextInt(sides) + 1);
            }
     
            // Format the results message
            String resultMessage = "[red] You rolled ";
            for (int i = 0; i < results.size(); i++) {
                resultMessage += results.get(i);
                if (i < results.size() - 1) {
                    resultMessage += ", ";
                }
            }
            resultMessage += " from " + numDice + "d" + sides + "[/red]";
     
            sendMessage(sender, processTextFormatting(resultMessage));
        } else {
            // Invalid format, send error message
            sendMessage(sender, "[red] Invalid roll command. Please use the correct format. [/red]");
        }
     }
    
    protected synchronized void sendPrivateMessage(ServerThread sender, List<String> dest, String message) {
        long from = (sender == null) ? Constants.DEFAULT_CLIENT_ID : sender.getClientId();
    
        for (ServerThread client : clients) {
            if (dest.contains(client.getClientName().toLowerCase())) {
                if (!isMuted(client.getClientName(), sender.getClientName())) {
                    boolean messageSent = client.sendMessage(from, message);
                    if (!messageSent) {
                        // Handle message delivery failure if needed
                    }
                }
            }
        }
        if (!dest.contains(sender.getClientName().toLowerCase())) {
            boolean senderMessageSent = sender.sendMessage(from, message);
            if (!senderMessageSent) {
                // Handle sender message delivery failure if needed
            }
        }
    }
    public void close() {
        Server.INSTANCE.removeRoom(this);
        isRunning = false;
        clients.clear();
    }
}