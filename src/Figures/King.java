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

    public boolean isCastleValid(Rook rook){
        if(hasMoved || rook.hasMoved) return false;

        Position rookPosition = rook.getPosition(), kingPosition = getPosition();

        for (int x = kingPosition.getX() + (rookPosition.getX() < kingPosition.getX() ? -1 : 1); x!= rookPosition.getX(); x+= (rookPosition.getX() < kingPosition.getX() ? -1 : 1)){
            if(board.getFigureAt(new Position(x, kingPosition.getY())) != null)
                return false;
        }

        for(int i=0; i<=2; i++){
            Position positionToCheck = new Position(kingPosition.getX() + (rookPosition.getX() < kingPosition.getX() ? -1 : 1)*i, kingPosition.getY());
            for (Figure figure : board.getFigures()) {
                if(!figure.getColour().equals(getColour()) && figure.isMoveValid(positionToCheck))
                    return false;
            }
        }

        return true;
    }
    public void castle(Rook rook){
        Position rookPosition = rook.getPosition(), kingPosition = getPosition();

        getPosition().setX(kingPosition.getX()+(rookPosition.getX() < kingPosition.getX() ? -2 : 2));
        rook.getPosition().setX(kingPosition.getX()+(rookPosition.getX() > kingPosition.getX() ? -1 : 1));

        this.hasMoved = true;
        rook.hasMoved = true;
    }
}