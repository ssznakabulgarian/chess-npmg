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
        return false;
    }
}
