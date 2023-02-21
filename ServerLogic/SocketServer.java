package ServerLogic;

import Common.Colour;
import Common.InvalidMoveException;
import Common.Position;
import GameLogic.Board;
import GameLogic.IBoard;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//todo: setup socket server and await connection
//todo: handle incoming data (validate and make moves)
//todo: send board state after each turn

public class SocketServer {
    private final Board board;
    private static ServerSocket server;
    private static Socket playerWhiteSocket, playerBlackSocket;
    private static final int defaultPort = 9876;

    public SocketServer(Board board) throws IOException {
        this(defaultPort, board);
    }

    public SocketServer(int port, Board board) throws IOException{
        this.board = board;
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

    public void onPlayerMessageReceived(Colour player, String message) throws InvalidMoveException {
        if(board.getPlayerToMove() != player)sendMessageToPlayer(player, "not your turn");

        if(Pattern.compile("[0-7]:[0-7]", Pattern.CASE_INSENSITIVE).matcher(message).find()){
            board.ClickAt(new Position(message.charAt(0)-'0', message.charAt(2)-'0'));
        }
    }

    private void sendMessageToPlayer(Colour player, String message){
        //communicate
    }
}
