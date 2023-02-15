package Figures;

import Common.Colour;
import Common.InvalidMoveException;
import Common.Position;
import GameLogic.Board;
import GameLogic.IBoard;

public abstract class Figure {
    protected Position position;
    protected Colour colour;
    protected IBoard board;

    public Position getPosition() {
        return position;
    }

    public Colour getColour() {
        return colour;
    }

    public Figure(Position position, Colour colour, IBoard board){
        this.position = position;
        this.colour = colour;
        this.board = board;
    }

    abstract boolean isMoveValid(Position newPosition);
    public void Move(Position newPosition) throws InvalidMoveException {
        if(isMoveValid(newPosition)) position = newPosition;
        else throw new InvalidMoveException("an invalid move was attempted", this, newPosition);
    }
}
