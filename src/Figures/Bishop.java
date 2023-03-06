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
        Figure[][] arr = board.getField();
        if((newPosition.x>7 || newPosition.x<0) && (newPosition.y>7 || newPosition.y<0)) return false;
        if(!board.isSquareEmpty(newPosition)){
            if (arr[newPosition.x][newPosition.y]!= null && (arr[newPosition.x][newPosition.y].getColour() == (this.colour))){
                return false;
            }
        }
        int horizontal = getPosition().x - newPosition.x;
        if(horizontal<0) horizontal=horizontal*(-1);
        int vertical = getPosition().y - newPosition.y;
        if(vertical<0) vertical=vertical*(-1);
        if(horizontal == vertical){


            if(getPosition().x>newPosition.x){
                for (int i = 1; i < horizontal; i++) {
                    Position between;
                    if(getPosition().y>newPosition.y){
                        between = new Position((getPosition().x - i), (getPosition().y - i));

                    }
                    else{
                        between = new Position((getPosition().x - i), (getPosition().y + i));

                    }
                    if (!board.isSquareEmpty(between)){
                        return false;
                    }
                }
            }
            else{
                for (int i = horizontal-1; i >0 ; i--) {
                    Position between;
                    if(getPosition().y>newPosition.y){
                        between = new Position((getPosition().x + i), (getPosition().y - i));

                    }
                    else {
                        between = new Position((getPosition().x + i), (getPosition().y + i));

                    }
                    if (!board.isSquareEmpty(between)){
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }


}
