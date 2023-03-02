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
        if(position.getX() - newPosition.getX() <= 1  && position.getX() - newPosition.getX()  >= 0 &&  position.getY() - newPosition.getY() <= 1  && position.getY() - newPosition.getY()  >= 0 ) {
            if (board.isSquareEmpty(newPosition)) {
                return true;
            }
            if (!board.isSquareEmpty(newPosition)) {
                if (board.getFigureAt(newPosition).getColour() == colour) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void Move(Position position) throws InvalidMoveException, InvalidMoveException {

        Figure otherFigure = board.getFigureAt(position);
        if(otherFigure instanceof Rook
                && otherFigure.getColour().equals(this.colour)) castle((Rook, position) otherFigure); //castle
        else super.Move(position);
    }

    public void castle(Rook rook, Position newPosition){
        if(rook.getPosition().getX() > position.getX()){
            int x1 = position.getX();
            int x2 = rook.getPosition().getX();
        }else {
            int x1 = rook.getPosition().getX();
            int x2 = position.getX();
        }
        if(!King.hasMoved && !rook.hasMoved){
            boolean inCheck = true;
            for (Figure Figure : figures) {
                for(int x = x1; x <= x2; x++) {
                    if(Figure.getColour() != this.colour){
                        Position check = new Position(x, position.getY());
                        if(Figure.isMoveValid(check)){
                            inCheck = false;
                            break;
                        }
                    }
                }
            }
            if(inCheck){
                position = newPosition;
            }

        }
    }


}
