package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;

public class Queen extends Figure{
    public Queen(Position position, Colour colour, Board board) {
        super(position, colour, board);
    }

    @Override
    public boolean isMoveValid(Position newPosition) {
        Figure[][] boardField = board.getField();

        if (newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() > 7 || newPosition.getY() > 7
                || getPosition().equals(newPosition)
                || (board.getField()[newPosition.getX()][newPosition.getY()] != null
                && board.getField()[newPosition.getX()][newPosition.getY()].getColour().equals(getColour())))
            return false;

        int horizontal = Math.abs(getPosition().getX() - newPosition.getX());
        int vertical = Math.abs(getPosition().getY() - newPosition.getY());

        if(horizontal == vertical){
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
        }else if(getPosition().getX() == newPosition.getX() || getPosition().getY() == newPosition.getY()){
            if (getPosition().getX() == newPosition.getX()) {
                for (int i = 1; i < vertical; i++) {
                    if (board.getField()[getPosition().getX()]
                            [getPosition().getY() + (getPosition().getY() > newPosition.getY() ? -i : i)] != null
                            && i != vertical) return false;
                }
            }else {
                for (int i = 1; i < horizontal; i++) {
                    if (board.getField()[getPosition().getX() + (getPosition().getX() > newPosition.getX() ? -i : i)]
                            [getPosition().getY()] != null
                            && i != horizontal)
                        return false;
                }
            }
            return true;
        }
        return false;
    }
}