package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;

public class Bishop extends Figure{
    public Bishop(Position position, Colour colour, Board board) {
        super(position, colour, board);
    }

    @Override
    public boolean isMoveValid(Position newPosition) {
        return false;
    }
}
