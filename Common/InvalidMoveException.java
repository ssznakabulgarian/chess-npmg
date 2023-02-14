package Common;

import Figures.Figure;

public class InvalidMoveException extends Exception{
    private Figure figure;
    private Position newPosition;
    public InvalidMoveException(String message, Figure figure, Position newPosition){
        super(message);
        this.figure=figure;
        this.newPosition = newPosition;
    }
}
