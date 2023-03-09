package Common;

import Figures.Figure;

public class InvalidMoveException extends Exception{
    private final Figure figure;
    private final Position newPosition;
    public InvalidMoveException(String message, Figure figure, Position newPosition){
        super(message);
        this.figure=figure;
        this.newPosition = newPosition;
    }
}
