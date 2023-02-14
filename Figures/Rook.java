package Figures;

import Common.Position;
import GameLogic.Board;
public class Rook extends Figure{
    private static boolean hasMoved = false;

    public Rook(Position position, boolean Color, Board board, boolean hasMoved){
        super(position, Color, board);
    }

    public static boolean getHasMoved(){
        return hasMoved;
    }

    @Override
    public boolean isMoveValid(Position newPosition){
        
    }


}
