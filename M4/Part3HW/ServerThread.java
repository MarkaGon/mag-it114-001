package M4.Part3HW;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private boolean isRunning = false;
    private ObjectOutputStream out;//exposed here for send()
    private Server server;// ref to our server so we can call methods on it
    // more easily

    private void info(String message) {
        System.out.println(String.format("Thread[%s]: %s", getId(), message));
    }

    public ServerThread(Socket myClient, Server server) {
        info("Thread created");
        // get communication channels to single client
        this.client = myClient;
        this.server = server;

    }

    public void disconnect() {
        info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    public boolean send(String message) {
        // added a boolean so we can see if the send was successful
        try {
            out.writeObject(message);
            return true;
        } catch (IOException e) {
            info("Error sending message to client (most likely disconnected)");
            // comment this out to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        }
    }

    // UCID:mag DATE:10-16-2023      DICE ROLLER 
    private void diceRoll(String die) {
        if (die.matches("roll\\s+\\d+d\\d+")) {
            String[] parts = die.trim().split("\s+");
            int dice = Integer.parseInt(parts[1].split("d")[0]);
            int faces = Integer.parseInt(parts[1].split("d")[1]);
    
            int result = 0;
            for (int i = 0; i < dice; i++) {
                int roll = (int) (Math.random() * faces) + 1;
                result += roll;
            }
    
            String rollMessage = String.format("User[%d] rolled %dd%d and got %d", getId(), dice, faces, result);
            server.broadcast(rollMessage, getId());
        }
    }

    
// UCID:mag DATE:10-16-2023     coin toss
     private void coinToss(String toss) {
        if (tossCoin(toss)){
            doToss();
        }
    }

    private boolean tossCoin(String toss) {
        return toss.equalsIgnoreCase("toss a coin") || toss.equalsIgnoreCase("flip a coin");
    }

    private void doToss() {
        String result = (Math.random() < 0.5) ? "heads" : "tails";
        String toss = String.format("User[%d] Flipped a coin and got %s", getId(), result);
        server.broadcast(toss, getId());
    }


    @Override
    public void run() {
        info("Thread starting");
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            String fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (String) in.readObject()) != null // reads an object from inputStream (null would
                                                                    // likely mean a disconnect)
            ) {

                info("Received from client: " + fromClient);
                diceRoll(fromClient);
                coinToss(fromClient);
                server.broadcast(fromClient, this.getId());
            } // close while loop
        } catch (Exception e) {
            // happens when client disconnects
            e.printStackTrace();
            info("Client disconnected");
        } finally {
            isRunning = false;
            info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }

    private void cleanup() {
        info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            info("Client already closed");
        }
        info("Thread cleanup() complete");
    }
}