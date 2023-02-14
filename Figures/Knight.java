package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;

public class Knight extends Figure{
    public Knight(Position startingPosition, Colour Colour, Board board) {
        super(startingPosition, Colour, board);
    }

    public static Figure[][] testField = new Figure[8][8];


    @java.lang.Override
    boolean isMoveValid(Position newPosition) {
        Figure[][] boardArray = testField;
        if (boardArray[newPosition.x][newPosition.y] == null ||
            boardArray[newPosition.x][newPosition.y].getColour() != boardArray[getPosition().x][getPosition().y].getColour())
        {
            if (boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x+2][getPosition().y+1]) {
                return true;
            }
            if (boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x+2][getPosition().y-1]) {
                return true;
            }
            if (boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x+1][getPosition().y+2]) {
                return true;
            }
            if (boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x+1][getPosition().y-2]) {
                return true;
            }
            if (boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x-1][getPosition().y+2]) {
                return true;
            }
            if (boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x-1][getPosition().y-2]) {
                return true;
            }
            if (boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x-2][getPosition().y+1]) {
                return true;
            }
            if (boardArray[newPosition.x][newPosition.y] == boardArray[getPosition().x-2][getPosition().y-1]) {
                return true;
            }
        }
        return false;
    }
}
