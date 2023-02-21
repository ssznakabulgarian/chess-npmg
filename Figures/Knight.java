package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;

public class Knight extends Figure{
    public Knight(Position position, Colour colour, Board board) {
        super(position, colour, board);
    }

    boolean isMoveValid(Position newPosition) {
        Figure[][] boardArray = board.getField();
        if (board.isSquareEmpty(newPosition) ||
            boardArray[newPosition.x][newPosition.y].getColour() != this.colour)
        {
            if (getPosition().x+2 < 8 && getPosition().y+1 < 8 &&
                boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x+2][getPosition().y+1]) {
                return true;
            }
            if (getPosition().x+2 < 8 && getPosition().y-1 > -1 &&
                boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x+2][getPosition().y-1]) {
                return true;
            }
            if (getPosition().x+1 < 8 && getPosition().y+2 < 8 &&
                boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x+1][getPosition().y+2]) {
                return true;
            }
            if (getPosition().x+1 < 8 && getPosition().y-2 > -1 &&
                boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x+1][getPosition().y-2]) {
                return true;
            }
            if (getPosition().x-1 > -1 && getPosition().y+2 < 8 &&
                boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x-1][getPosition().y+2]) {
                return true;
            }
            if (getPosition().x-1 > -1 && getPosition().y-2 > -1 &&
                boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x-1][getPosition().y-2]) {
                return true;
            }
            if (getPosition().x-2 > -1 && getPosition().y+1 < 8 &&
                boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x-2][getPosition().y+1]) {
                return true;
            }
            if (getPosition().x-2 > -1 && getPosition().y-1 > -1 &&
                boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x-2][getPosition().y-1]) {
                return true;
            }
        }
        return false;
    }
}
