package Figures;

import Common.Position;
import GameLogic.Board;

public abstract class Figure {
    private Position position;
    private boolean Colour;
    private Board board;

    public Position getPosition() {
        return position;
    }

    public boolean isColour() {
        return Colour;
    }

    public Figure(Position position, boolean Color, Board board){
        position = this.position;
        Color = this.Colour;
        board = this.board;
    }

    abstract boolean isMoveValid(Position newPosition);
    //abstract void Move(Position newPosition);
}
