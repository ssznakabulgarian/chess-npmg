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

        // check if newPosition has valid coordinates
        if (newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() > 7 || newPosition.getY() > 7)
            return false;
        if (getPosition().equals(newPosition))
            return false;
        if (getPosition().getX() != newPosition.getX() && getPosition().getY() != newPosition.getY())
            return false;

        // check if on newPosition is a same colour figure
        if(board.getField()[newPosition.getX()][newPosition.getY()] != null && board.getField()[newPosition.getX()][newPosition.getY()].getColour().equals(getColour()))
            return false;

        // determine whether a move is vertical or horizontal
        int sameCoordX = -1, sameCoordY = -1;
        if (getPosition().getX() == newPosition.getX())
            sameCoordX = getPosition().getX();
        if (getPosition().getY() == newPosition.getY())
            sameCoordY = getPosition().getY();


        // check if a figure is in the way
        if (sameCoordX != -1) {
            if (getPosition().getY() < newPosition.getY()) {
                for (int i = getPosition().getY() + 1; i <= newPosition.getY(); i++) {
                    if (board.getField()[sameCoordX][i] != null && i != newPosition.getY()) {
                        return false;
                    }
                }
                return true;

            } else {
                for (int i = getPosition().getY() - 1; i >= newPosition.getY(); i--) {
                    if (board.getField()[sameCoordX][i] != null && i != newPosition.getY()) {
                        return false;
                    }
                }
                return true;
            }
        }

        if (sameCoordY != -1) {
            if (getPosition().getX() < newPosition.getX()) {
                for (int i = getPosition().getX() + 1; i <= newPosition.getX(); i++) {
                    if (board.getField()[i][sameCoordY] != null && i != newPosition.getX()) {
                        return false;
                    }
                }
                return true;

            } else {
                for (int i = getPosition().getX() - 1; i >= newPosition.getX(); i--) {
                    if (board.getField()[i][sameCoordY] != null && i != newPosition.getX()) {
                        return false;
                    }
                }
                return true;
            }
        }

        return false;
    }

}
