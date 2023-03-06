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

        int loopHelper = -1;
        // determine whether a move is vertical or horizontal (true: vertical; false: horizontal)
        boolean direction = getPosition().getX() == newPosition.getX();

        // check if a figure is in the way
        if (direction) {
            if (getPosition().getY() < newPosition.getY()) loopHelper = 1;
            for (int i = getPosition().getY() + loopHelper; i != newPosition.getY(); i+=loopHelper) {
                if (board.getField()[getPosition().getX()][i] != null && i != newPosition.getY()) return false;
            }
        }else {
            if (getPosition().getX() < newPosition.getX()) loopHelper = 1;
            for (int i = getPosition().getX() + loopHelper; i != newPosition.getX(); i+=loopHelper) {
                if (board.getField()[i][getPosition().getY()] != null && i != newPosition.getX()) return false;
            }
        }
        return true;
    }
}