package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;

public class Bishop extends Figure{


    public Bishop(Position startingPosition, Colour Color, Board board) {
        super(startingPosition, Color, board);
    }
    //array 8x8, empty spaces are zeroes
    Figure arr[][] = board.getField();
    @Override
    boolean isMoveValid(Position newPosition) {
        if(!board.isSquareEmpty(newPosition)){
            if(board.getFigureAt(newPosition) instanceof King){
                return false;
            }
            if (arr[newPosition.x][newPosition.y].getColour().equals(arr[getPosition().x][getPosition().y].getColour())){
                return false;
            }
        }
        int x = getPosition().x - newPosition.x;
        if(x<0) x=x*(-1);
        int y = getPosition().y - newPosition.y;
        if(y<0) y=y*(-1);
        if(x == y){

            for (int i = 0; i<x; i++){
                Position between = new Position((getPosition().x+i), (getPosition().y+i));
                if (!board.isSquareEmpty(between)){
                    return false;
                }
            }
            return true;
        }

        return false;
    }





}
