package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;
//import GameLogic.IBoard;

public class Bishop extends Figure{
    public Bishop(Position startingPosition, Colour Color, Board board) {
        super(startingPosition, Color, board);
    }
    @Override
    public boolean isMoveValid(Position newPosition) {
        Figure[][] boardField = board.getField();

        if((!board.isSquareEmpty(newPosition) && boardField[newPosition.getX()][newPosition.getY()].getColour().equals(getColour()))
            || newPosition.getX() > 7 || newPosition.getX() < 0 || newPosition.getY() > 7 || newPosition.getY() < 0)
            return false;

        int horizontal = Math.abs(getPosition().getX() - newPosition.getX());
        int vertical = Math.abs(getPosition().getY() - newPosition.getY());
        if(horizontal != vertical) return false;


        if(getPosition().getX() > newPosition.getX()){
            for (int i = 1; i < horizontal; i++) {
                if(boardField[getPosition().getX() - i]
                        [getPosition().getY() - (getPosition().getY() > newPosition.getY() ? i : -i)] != null
                && getPosition().getX() - i != newPosition.getX()) return false;
            }
        }
        else{
            for (int i = horizontal - 1; i > 0; i--) {
                if(boardField[getPosition().getX() + i]
                        [getPosition().getY() - (getPosition().getY() > newPosition.getY() ? i : -i)] != null
                && getPosition().getX() - i != newPosition.getX()) return false;
            }
        }
        return true;
    }
}
