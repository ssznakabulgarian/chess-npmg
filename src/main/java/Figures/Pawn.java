package Figures;

import Common.Colour;
import Common.Position;
import GameLogic.Board;

public class Pawn extends Figure {
    public Pawn(Position position, Colour Color, Board board) {
        super(position, Color, board);
    }

    @Override
    public boolean isMoveValid(Position newPosition) {
        Position enPassantTaking = new Position(newPosition.getX(), getPosition().getY());
        if (Math.abs(newPosition.getX() - getPosition().getX()) == 1
                && newPosition.getY() - getPosition().getY() == (getColour() == Colour.white ? 1 : -1)
                && (    (board.getFigureAt(newPosition) != null && !board.getFigureAt(newPosition).getColour().equals(getColour()))
                    ||  (!board.isSquareEmpty(enPassantTaking) && board.getFigureAt(enPassantTaking).equals(board.getEnPassantPawn()))
                   )
            ) return true;

         if(newPosition.getX() == getPosition().getX()
          && newPosition.getY() - getPosition().getY() == (getColour() == Colour.white ? 1 : -1)
          && board.getFigureAt(newPosition) == null) return true;

        return newPosition.getX() == getPosition().getX()
                && newPosition.getY() - getPosition().getY() == (getColour() == Colour.white ? 2 : -2)
                && !getHasMoved()
                && board.getFigureAt(new Position(getPosition().getX(), getPosition().getY() + (getColour() == Colour.white ? 1 : -1))) == null
                && board.getFigureAt(newPosition) == null;
    }
}
