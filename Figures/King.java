package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;

public class King extends Figure {
    public boolean isKingMoved;

    public King(Position position, Colour colour, Board board) {
        super(position, colour, board);
    }

    @Override
    public boolean isMoveValid(Position newPosition){
        if(board.isSquareEmpty(newPosition)) {
            return true;
        }
        if(!board.isSquareEmpty(newPosition)) {
            if(board.getFigureAt(newPosition).getColour() == colour) {
                return false;
            }else {
                return true;
            }
        }
        return false;
    }




}
