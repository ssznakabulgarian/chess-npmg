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

        // check if newPosition has valid coordinates
        if (newPosition.getX() < 0 || newPosition.getY() < 0 || newPosition.getX() > 7 || newPosition.getY() > 7)
            return false;
        if (getPosition().equals(newPosition))
            return false;

        // check if on newPosition is a same colour figure
        if (board.getField()[newPosition.getX()][newPosition.getY()] != null
                && board.getField()[newPosition.getX()][newPosition.getY()].getColour().equals(getColour()))
            return false;

        if (getColour().equals(Colour.white)) {
            // check if 1 space forward is a valid move
            if (newPosition.getX() == getPosition().getX() && newPosition.getY() == getPosition().getY() + 1
                    && board.getField()[newPosition.getX()][newPosition.getY()] == null)
                return true;

            // check if 2 spaces forward is a valid move
            if (!getHasMoved() && newPosition.getX() == getPosition().getX()
                    && newPosition.getY() == getPosition().getY() + 2
                    && board.getField()[getPosition().getX()][getPosition().getY() + 1] == null
                    && board.getField()[getPosition().getX()][getPosition().getY() + 2] == null) {
                board.setEnPassantPawn(board.getField()[getPosition().getX()][getPosition().getY()]);
                return true;
            }

            // check if pawn can take
            if (board.getField()[newPosition.getX()][newPosition.getY()] != null
                    && (board.getField()[newPosition.getX()][newPosition
                            .getY()] == board.getField()[getPosition().getX() + 1][getPosition().getY() + 1]
                            ||
                            board.getField()[newPosition.getX()][newPosition
                                    .getY()] == board.getField()[getPosition().getX() - 1][getPosition().getY() + 1]))
                return true;

            // check for En Passant
            if (board.getEnPassantPawn() != null && board.getEnPassantPawn() instanceof Pawn
                    && !board.getEnPassantPawn().getColour().equals(getColour())) {
                // if it is to the right of us
                if (board.getField()[getPosition().getX() + 1][getPosition().getY()].equals(board.getEnPassantPawn())) {
                    if (newPosition.getX() == getPosition().getX() + 1
                            && newPosition.getY() == getPosition().getY() + 1)
                        return true;

                }

                // if it is to the left of us
                if (board.getField()[getPosition().getX() - 1][getPosition().getY()].equals(board.getEnPassantPawn())) {
                    if (newPosition.getX() == getPosition().getX() - 1
                            && newPosition.getY() == getPosition().getY() + 1)
                        return true;
                }
            }

        } else {
            // check if 1 space forward is a valid move
            if (newPosition.getX() == getPosition().getX() && newPosition.getY() == getPosition().getY() - 1
                    && board.getField()[newPosition.getX()][newPosition.getY()] == null)
                return true;

            // check if 2 spaces forward is a valid move
            if (!getHasMoved() && newPosition.getX() == getPosition().getX()
                    && newPosition.getY() == getPosition().getY() - 2
                    && board.getField()[getPosition().getX()][getPosition().getY() - 1] == null
                    && board.getField()[getPosition().getX()][getPosition().getY() - 2] == null) {
                board.setEnPassantPawn(board.getField()[getPosition().getX()][getPosition().getY()]);
                return true;
            }

            // check if pawn can take
            if (board.getField()[newPosition.getX()][newPosition.getY()] != null
                    && (board.getField()[newPosition.getX()][newPosition
                            .getY()] == board.getField()[getPosition().getX() + 1][getPosition().getY() - 1]
                            ||
                            board.getField()[newPosition.getX()][newPosition
                                    .getY()] == board.getField()[getPosition().getX() - 1][getPosition().getY() - 1]))
                return true;

            // check for En Passant
            if (board.getEnPassantPawn() != null && board.getEnPassantPawn() instanceof Pawn
                    && !board.getEnPassantPawn().getColour().equals(getColour())) {
                // if it is to the right of us
                if (board.getField()[getPosition().getX() + 1][getPosition().getY()].equals(board.getEnPassantPawn())) {
                    if (newPosition.getX() == getPosition().getX() + 1
                            && newPosition.getY() == getPosition().getY() - 1)
                        return true;

                }

                // if it is to the left of us
                if (board.getField()[getPosition().getX() - 1][getPosition().getY()].equals(board.getEnPassantPawn())) {
                    if (newPosition.getX() == getPosition().getX() - 1
                            && newPosition.getY() == getPosition().getY() - 1)
                        return true;
                }
            }

        }

        return false;
    }
}
