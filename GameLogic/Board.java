package GameLogic;

import Common.Colour;
import Common.InvalidMoveException;
import Common.Position;
import Figures.*;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard{
    private List<Figure> figures;
    private Figure selectedFigure = null;
    private King WhiteKing = new King(new Position(4,0), Colour.white, this);
    private King BlackKing = new King(new Position(4,7), Colour.black, this);
    private Colour playerToMove = Colour.white;
    private boolean isPlayerToMoveInCheck = false;
    public Board() {
        this.figures = new ArrayList<Figure>();
        populateBoard();
    }
    public boolean isPlayerToMoveInCheck() {
        return isPlayerToMoveInCheck;
    }
    public void setPlayerToMoveInCheck(boolean playerToMoveInCheck) {
        isPlayerToMoveInCheck = playerToMoveInCheck;
    }
    public Figure getSelectedFigure() {
        return selectedFigure;
    }
    public List<Figure> getFigures() {
        return figures;
    }
    public Colour getPlayerToMove() {
        return playerToMove;
    }
    private void populateBoard(){
        for(int x=0;x<8;x++){
            figures.add(new Pawn(new Position(x,1), Colour.white,this));
            figures.add(new Pawn(new Position(x,6), Colour.black,this));
        }

        figures.add(new Rook(new Position(0,0), Colour.white, this));
        figures.add(new Rook(new Position(7,0), Colour.white, this));
        figures.add(new Rook(new Position(0,7), Colour.black, this));
        figures.add(new Rook(new Position(7,7), Colour.black, this));

        figures.add(new Knight(new Position(1,0), Colour.white, this));
        figures.add(new Knight(new Position(6,0), Colour.white, this));
        figures.add(new Knight(new Position(1,7), Colour.black, this));
        figures.add(new Knight(new Position(6,7), Colour.black, this));

        figures.add(new Bishop(new Position(2,0), Colour.white, this));
        figures.add(new Bishop(new Position(5,0), Colour.white, this));
        figures.add(new Bishop(new Position(2,7), Colour.black, this));
        figures.add(new Bishop(new Position(5,7), Colour.black, this));

        figures.add(new Queen(new Position(3,0), Colour.white, this));
        figures.add(new Queen(new Position(3,7), Colour.black, this));

        figures.add(WhiteKing);
        figures.add(BlackKing);
    }
    public boolean isSquareEmpty(Position position) {
        for (Figure figure : figures) if(figure.getPosition().equals(position)) return true;
        return false;
    }
    public Figure getFigureAt(Position position) {
        for (Figure figure : figures) if(figure.getPosition().equals(position)) return figure;
        return null;
    }
    public Figure[][] getField() {
        Figure[][] field = new Figure[8][8];
        for (Figure figure : figures) field[figure.getPosition().x][figure.getPosition().y] = figure;
        return field;
    }
    public void SelectAt(Position position) throws UnsupportedOperationException{
        Figure selection = getFigureAt(position);
        if(selection == null) throw new UnsupportedOperationException("the square is empty and cannot be selected");
        if(selection.getColour() != getPlayerToMove()) throw new UnsupportedOperationException("the selected figure is not yours");
        selectedFigure = selection;
    }
    public void ClickAt(Position position) throws InvalidMoveException, UnsupportedOperationException {
        if(selectedFigure == null) SelectAt(position);
        else{
            selectedFigure.Move(position);
            playerToMove = playerToMove.equals(Colour.white) ? Colour.black : Colour.white;
            isPlayerToMoveInCheck = playerToMove.equals(Colour.white) ? WhiteKing.isInCheck() : BlackKing.isInCheck();
        }
    }
}
