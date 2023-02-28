package Figures;

import Common.Colour;
import Common.InvalidMoveException;
import Common.Position;
import GameLogic.Board;

public abstract class Figure {
    protected boolean hasMoved = false;
    protected Position position;
    protected Colour colour;
    protected Board board;
    public Position getPosition() {
        return position;
    }
    public Colour getColour() {
        return colour;
    }
    public boolean getHasMoved(){
        return hasMoved;
    }
    public Figure(Position position, Colour colour, Board board){
        this.position = position;
        this.colour = colour;
        this.board = board;
    }
    abstract boolean isMoveValid(Position newPosition);
    public void Move(Position newPosition) throws InvalidMoveException {

        if(isMoveValid(newPosition)) position = newPosition;
        else throw new InvalidMoveException("an invalid move was attempted", this, newPosition);

        //this has to be done last in the move method
        board.setEnPassantPawn(null);
    }
}