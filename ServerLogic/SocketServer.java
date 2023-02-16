package ServerLogic;

import Common.Colour;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//todo: setup socket server and await connection
//todo: handle incoming data (validate and make moves)
//todo: send board state after each turn

public class SocketServer {
    private static ServerSocket server;
    private static Socket playerWhiteSocket, playerBlackSocket;
    private static final int defaultPort = 9876;

    public SocketServer() throws IOException, ClassNotFoundException {
        this(defaultPort);
    }

    public SocketServer(int port) throws IOException, ClassNotFoundException{

        server = new ServerSocket(port);

        System.out.println("Waiting for the first player");
        playerWhiteSocket = server.accept();
        System.out.println("player White connected");

        System.out.println("Waiting for the second player");
        playerBlackSocket = server.accept();
        System.out.println("player Black connected");

        new SocketServerWorker(playerWhiteSocket,this, Colour.white).start();
        new SocketServerWorker(playerBlackSocket,this, Colour.black).start();
    }

    private void close() throws IOException {
        playerWhiteSocket.close();
        playerBlackSocket.close();
        server.close();
    }

    public void onPlayerDisconnected(Colour player){
        System.out.println("player "+player.toString()+" disconnected");
    }

    public void onPlayerMessageReceived(Colour player, String message){
        //do something
    }

    private void sendMessageToPlayer(Colour player, String message){
        //communicate
    }
}
