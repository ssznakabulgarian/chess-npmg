package ServerLogic;

import Common.Colour;
import Common.InvalidMoveException;

import java.io.*;
import java.net.Socket;

public class SocketServerWorker extends Thread {
    private final Socket socket;
    private final SocketServer parent;
    private final Colour player;

    public SocketServerWorker(Socket clientSocket, SocketServer server, Colour player) {
        this.socket = clientSocket;
        this.parent = server;
        this.player = player;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null || socket.isClosed()) {
                    parent.onPlayerDisconnected(player);
                    return;
                } else {
                    parent.onPlayerMessageReceived(player, line);
                }
            }
        } catch (IOException | InvalidMoveException e) {
            throw new RuntimeException(e);
        }
    }
}
