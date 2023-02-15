package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;

public class Rook extends Figure {
    private static boolean hasMoved = false;

    public Rook(Position position, Colour Color, Board board, boolean hasMoved) {
        super(position, Color, board);
    }

    public static boolean getHasMoved() {
        return hasMoved;
    }

    @Override
    public boolean isMoveValid(Position newPosition) {

        // check if move is valid
        if (newPosition.getX() < 0 && newPosition.getY() < 0 && newPosition.getX() > 7 && newPosition.getY() > 7)
            return false;
        if (getPosition().getX() == newPosition.getX() && getPosition().getY() == newPosition.getY())
            return false;
        if (getPosition().getX() != newPosition.getX() && getPosition().getY() != newPosition.getY())
            return false;

        int sameCoordX = -1, sameCoordY = -1;
        if (getPosition().getX() == newPosition.getX())
            sameCoordX = getPosition().getX();
        if (getPosition().getY() == newPosition.getY())
            sameCoordY = getPosition().getY();

        // check if same colour figure is in the way

        if (sameCoordX != -1) {
            if (getPosition().getY() < newPosition.getY()) {

                for (int i = getPosition().getY(); i <= newPosition.getY(); i++) {
                    if (board.getField()[sameCoordX][i] != null && i != newPosition.getY()) {
                        return false;
                    }
                    if (board.getField()[sameCoordX][i].getColour() != getColour() && i == newPosition.getY()) {
                        hasMoved = true;
                        return true;
                    }
                }
                hasMoved = true;
                return true;
            } else {

                for (int i = getPosition().getY(); i >= newPosition.getY(); i--) {
                    if (board.getField()[sameCoordX][i] != null && i != newPosition.getY()) {
                        return false;
                    }
                    if (board.getField()[sameCoordX][i].getColour() != getColour() && i == newPosition.getY()) {
                        hasMoved = true;
                        return true;
                    }
                }
                hasMoved = true;
                return true;
            }
        }

        if (sameCoordY != -1) {
            if (getPosition().getX() < newPosition.getX()) {

                for (int i = getPosition().getX(); i <= newPosition.getX(); i++) {
                    if (board.getField()[i][sameCoordY] != null && i != newPosition.getX()) {
                        return false;
                    }
                    if (board.getField()[i][sameCoordY].getColour() != getColour() && i == newPosition.getX()) {
                        hasMoved = true;
                        return true;
                    }
                }
                hasMoved = true;
                return true;
            } else {

                for (int i = getPosition().getX(); i >= newPosition.getX(); i--) {
                    if (board.getField()[i][sameCoordY] != null && i != newPosition.getX()) {
                        return false;
                    }
                    if (board.getField()[i][sameCoordY].getColour() != getColour() && i == newPosition.getX()) {
                        hasMoved = true;
                        return true;
                    }
                }
                hasMoved = true;
                return true;
            }
        }

        return false;
    }

}
