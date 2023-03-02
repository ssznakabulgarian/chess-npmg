package ServerLogic;
import Common.Colour;
import Common.InvalidMoveException;
import Common.Position;
import GameLogic.Board;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.regex.Pattern;

@ServerEndpoint("/server")
public class SocketServerEndpoint {
    @Inject
    private Board board;
    private Colour playerColor;
    private Session session;
    private static SocketServerEndpoint WhitePlayerEndpoint;
    private static SocketServerEndpoint BlackPlayerEndpoint;
    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        if(WhitePlayerEndpoint == null) { WhitePlayerEndpoint = this; playerColor = Colour.white; }
        else if (BlackPlayerEndpoint == null) { BlackPlayerEndpoint = this; playerColor = Colour.black; }
        else session.close();
    }
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        if(board.getPlayerToMove() != playerColor) session.getBasicRemote().sendText("error: not your turn!");
        try {
            if (Pattern.compile("[0-7]:[0-7]", Pattern.CASE_INSENSITIVE).matcher(message).find()) {
                board.ClickAt(new Position(message.charAt(0) - '0', message.charAt(2) - '0'));
                broadcastBoardState();
            }
        } catch (InvalidMoveException e){
            session.getBasicRemote().sendText("error: not your turn!");
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();//internal err
        }
    }
    @OnClose
    public void onClose(Session session) {
        try {
            SocketServerEndpoint target = this == WhitePlayerEndpoint ? BlackPlayerEndpoint : WhitePlayerEndpoint;
            target.session.getBasicRemote().sendText("opponent disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            String errorMessage = this.playerColor.toString() + " player socket error: " + throwable.getLocalizedMessage();
            WhitePlayerEndpoint.session.getBasicRemote().sendText(errorMessage);
            BlackPlayerEndpoint.session.getBasicRemote().sendText(errorMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void broadcastBoardState(){
        try {
            String json = new Gson().toJson(board.getFigures());
            WhitePlayerEndpoint.session.getBasicRemote().sendText(json);
            BlackPlayerEndpoint.session.getBasicRemote().sendText(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
