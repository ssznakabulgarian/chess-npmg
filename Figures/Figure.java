package Figures;

import Common.Colour;
import Common.InvalidMoveException;
import Common.Position;
import GameLogic.Board;

public abstract class Figure {
    protected Position position;
    protected Colour colour;
    protected Board board;

    public Position getPosition() {
        return position;
    }

    public Colour getColour() {
        return colour;
    }

    public Figure(Position position,Colour colour , Board board){
        position = this.position;
        colour = this.colour;
        board = this.board;
    }

    abstract boolean isMoveValid(Position newPosition);
    public void Move(Position newPosition) throws InvalidMoveException {
        if(isMoveValid(newPosition)) position = newPosition;
        else throw new InvalidMoveException("an invalid move was attempted", this, newPosition);
    }
}
