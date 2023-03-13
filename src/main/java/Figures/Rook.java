package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;

public class Rook extends Figure {
    public Rook(Position position, Colour Color, Board board) {
        super(position, Color, board);
    }
    @Override
    public boolean isMoveValid(Position newPosition) {
        // edge cases
        if (newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() > 7 || newPosition.getY() > 7
                || getPosition().equals(newPosition) || (getPosition().getX() != newPosition.getX() && getPosition().getY() != newPosition.getY())
                || (board.getField()[newPosition.getX()][newPosition.getY()] != null
                && board.getField()[newPosition.getX()][newPosition.getY()].getColour().equals(getColour())))
            return false;

        // check if a figure is in the way
        if (getPosition().getX() == newPosition.getX()) {
            for (int i = 1; i < Math.abs((getPosition().getY()) - newPosition.getY()); i++) {
                if (board.getField()[getPosition().getX()]
                        [getPosition().getY() + (getPosition().getY() > newPosition.getY() ? -i : i)] != null
                && i != Math.abs((getPosition().getY()) - newPosition.getY())) return false;
            }
        }else {
            for (int i = 1; i < Math.abs((getPosition().getX()) - newPosition.getX()); i++) {
                if (board.getField()[getPosition().getX() + (getPosition().getX() > newPosition.getX() ? -i : i)]
                        [getPosition().getY()] != null
                && i != Math.abs((getPosition().getX()) - newPosition.getX())) return false;
            }
        }
        return true;
    }
}