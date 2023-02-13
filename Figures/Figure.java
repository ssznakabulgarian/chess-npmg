package Figures;

import Common.Position;
import GameLogic.Board;

public abstract class Figure {
    private Position StartingPosition;
    private Position position;
    private boolean Colour;
    private Board board;

    public Position getStartingPosition() {
        return StartingPosition;
    }

    public boolean isColour() {
        return Colour;
    }

    public Figure(Position startingPosition, boolean Color, Board board){
        startingPosition = this.StartingPosition;
        Color = this.Colour;
        board = this.board;
    }

    abstract boolean isMoveValid(Position newPosition);
    abstract void Move(Position newPosition);
}
