package Figures;

import Common.Colour;
import Common.InvalidMoveException;
import Common.Position;
import GameLogic.Board;

public class King extends Figure {

    public King(Position position, Colour colour, Board board) {
        super(position, colour, board);
    }

    @Override
    public boolean isMoveValid(Position newPosition){
        if(Math.abs(position.getX() - newPosition.getX()) <= 1
        && Math.abs(position.getY() - newPosition.getY()) <= 1)
            return board.isSquareEmpty(newPosition) || !board.getFigureAt(newPosition).getColour().equals(getColour());

        return false;
    }
    public void castle(Rook rook) throws InvalidMoveException{
        if(hasMoved || rook.hasMoved) throw new InvalidMoveException("cannot castle after the king or the rook have moved", this, getPosition());

        Position rookPosition = rook.getPosition(), kingPosition = getPosition();

        for (int x = kingPosition.getX() + (rookPosition.getX() < kingPosition.getX() ? -1 : 1); x!= rookPosition.getX(); x+= (rookPosition.getX() < kingPosition.getX() ? -1 : 1)){
            if(board.getFigureAt(new Position(x, kingPosition.getY())) != null) throw new InvalidMoveException("cannot castle through figures", this, new Position(x, kingPosition.getY()));
        }

        for(int i=0; i<=2; i++){
            Position positionToCheck = new Position(kingPosition.getX() + (rookPosition.getX() < kingPosition.getX() ? -1 : 1), kingPosition.getY());
            for (Figure figure : board.getFigures()) {
                if(!figure.getColour().equals(getColour()) && figure.isMoveValid(positionToCheck))
                    throw new InvalidMoveException("cannot castle through a check or while in check", this, positionToCheck);
            }
        }

        getPosition().setX(kingPosition.getX()+(rookPosition.getX() < kingPosition.getX() ? -2 : 2));
        rook.getPosition().setX(kingPosition.getX()+(rookPosition.getX() < kingPosition.getX() ? -1 : 1));

        this.hasMoved = true;
        rook.hasMoved = true;
    }
}