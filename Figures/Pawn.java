package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;

public class Pawn extends Figure {
    public Pawn(Position position, Colour Color, Board board) {
        super(position, Color, board);
    }

    @Override
    public boolean isMoveValid(Position newPosition) {

        // check if newPosition has valid coordinates
        if (newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() > 7 || newPosition.getY() > 7)
            return false;
        if (getPosition().equals(newPosition))
            return false;

        // check if on newPosition is a same colour figure
        if (board.getField()[newPosition.getX()][newPosition.getY()] != null
                && board.getField()[newPosition.getX()][newPosition.getY()].getColour().equals(getColour()))
            return false;

        // check if 1 space forward is a valid move
        if (board.getField()[getPosition().getX()][newPosition
                .getY()] == board.getField()[getPosition().getX()][getPosition().getY() + 1]
                && board.getField()[getPosition().getX()][getPosition().getY() + 1] == null)
            return true;

        // check if 2 spaces forward is a valid move
        if (!getHasMoved() && board.getField()[getPosition().getX()][newPosition
                .getY()] == board.getField()[getPosition().getX()][getPosition().getY() + 2]
                && board.getField()[getPosition().getX()][getPosition().getY() + 1] == null
                && board.getField()[getPosition().getX()][getPosition().getY() + 2] == null)
            return true;

        // check if pawn can take
        if (board.getField()[newPosition.getX()][newPosition.getY()] != null
                && (board.getField()[newPosition.getX()][newPosition
                        .getY()] == board.getField()[getPosition().getX() + 1][getPosition().getY() + 1]
                        ||
                        board.getField()[newPosition.getX()][newPosition
                                .getY()] == board.getField()[getPosition().getX() - 1][getPosition().getY() + 1]))
            return true;

        return false;
    }
}
