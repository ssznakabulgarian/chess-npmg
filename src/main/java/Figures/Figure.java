package Figures;

import Common.Colour;
import Common.InvalidMoveException;
import Common.Position;
import GameLogic.Board;

import javax.naming.OperationNotSupportedException;

public abstract class Figure {
    protected boolean hasMoved = false;
    protected Position position;
    protected Colour colour;
    protected Board board;

    public Position getPosition() {
        return position;
    }

    public Colour getColour() {
        return colour;
    }
    public boolean getHasMoved(){
        return hasMoved;
    }
    public Figure(Position position, Colour colour, Board board){
        this.position = position;
        this.colour = colour;
        this.board = board;
    }
    public abstract boolean isMoveValid(Position newPosition);
    public void Move(Position newPosition) throws InvalidMoveException {
        Position oldPosition = new Position(position);
        if(!isMoveValid(newPosition)) throw new InvalidMoveException("an invalid move was attempted", this, newPosition);

        //virtually move the piece to the new position (capturing a piece if needed) to check for checks
        Figure capturedFigure = board.getFigureAt(newPosition);
        position = newPosition;
        if(capturedFigure != null){
            try {
                board.capture(capturedFigure);
            } catch (OperationNotSupportedException e) {
                throw new RuntimeException(e);//should never occur
            }
        }

        if(board.isPlayerToMoveInCheck()) {
            position = oldPosition;
            if(capturedFigure != null){
                try {
                    board.unCapture(capturedFigure);
                } catch (OperationNotSupportedException e) {
                    throw new RuntimeException(e);//should never occur
                }
            }
            throw new InvalidMoveException("this move opens a check and is thus not permitted", this, newPosition);
        }

        if(this instanceof Pawn && Math.abs(newPosition.y - oldPosition.y) == 2) board.setEnPassantPawn((Pawn) this);

        if((this instanceof Rook || this instanceof King || this instanceof Pawn) && !this.hasMoved) this.hasMoved = true;
    }
}
