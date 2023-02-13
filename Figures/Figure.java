package Figures;

import Common.Position;

public abstract class Figure {
    private Position StartingPosition;
    private Position position;
    private boolean Colour;

    public Position getStartingPosition() {
        return StartingPosition;
    }

    public boolean isColour() {
        return Colour;
    }

    public Figure(Position startingPosition, boolean Color){

    }

    abstract boolean isMoveValid(Position newPosition);
    abstract void Move(Position newPosition);
}
