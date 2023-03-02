package GameLogic;

import Common.Colour;
import Common.InvalidMoveException;
import Common.Position;
import Figures.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import javax.naming.OperationNotSupportedException;
import java.awt.dnd.InvalidDnDOperationException;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class Board implements java.io.Serializable, IBoard{
    private final List<Figure> figures;
    private final List<Figure> capturedFigures;
    private Pawn enPassantPawn = null;
    private Figure selectedFigure = null;
    private final King WhiteKing = new King(new Position(4,0), Colour.white, this);
    private final King BlackKing = new King(new Position(4,7), Colour.black, this);
    private Colour playerToMove = Colour.white;
    private boolean isPlayerToMoveInCheck = false;
    public Board() {
        figures = new ArrayList<>();
        capturedFigures = new ArrayList<>();
        populateBoard();
    }
    public List<Figure> getFigures(){
        return new ArrayList<>(figures);
    }
    public List<Figure> getCapturedFigures(){
        return new ArrayList<>(capturedFigures);
    }
    public boolean isPlayerToMoveInCheck() {
        for (Figure Figure : figures) {
            if(Figure.isMoveValid(playerToMove == Colour.white? WhiteKing.getPosition() : BlackKing.getPosition())){
                return true;
            }
        }
        return false;
    }

    public void setPlayerToMoveInCheck(boolean playerToMoveInCheck) {
        isPlayerToMoveInCheck = playerToMoveInCheck;
    }
    public Figure getSelectedFigure() {
        return selectedFigure;
    }
    public Colour getPlayerToMove() {
        return playerToMove;
    }
    public void setEnPassantPawn(Pawn enPassantPawn){
        this.enPassantPawn = enPassantPawn;
    }
    public Figure getEnPassantPawn(){
        return this.enPassantPawn;
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
    private void capture(Figure figure) throws OperationNotSupportedException{
        if(!figures.contains(figure)) throw new OperationNotSupportedException("this figure is not on the board!");
        figures.remove(figure);
        capturedFigures.add(figure);
    }
    public void SelectAt(Position position) throws UnsupportedOperationException{
        Figure selection = getFigureAt(position);
        if(selection == null) throw new UnsupportedOperationException("the square is empty and cannot be selected");
        if(selection.getColour() != getPlayerToMove()) throw new UnsupportedOperationException("the selected figure is not yours");
        selectedFigure = selection;
    }
    public void ClickAt(Position position) throws InvalidMoveException, OperationNotSupportedException {
        if(selectedFigure == null || (getFigureAt(position) != null && selectedFigure.getColour().equals(getFigureAt(position).getColour()))) SelectAt(position);
        else{
            selectedFigure.Move(position);
            for (Figure capturedFigure : figures)
                if(capturedFigure.getPosition().equals(selectedFigure.getPosition()) && !capturedFigure.equals(selectedFigure)) {
                    capture(capturedFigure);
                    break;
                }
            if(enPassantPawn != null
            && enPassantPawn.getPosition().getY() == (enPassantPawn.getColour() == Colour.white ? 3 : 4)
            && !selectedFigure.equals(enPassantPawn)) {
                Figure figureBehindEnPassantPawn = getFigureAt(new Position(enPassantPawn.getPosition().getX(), enPassantPawn.getPosition().getY() + (enPassantPawn.getColour() == Colour.white ? -1 : 1)));
                if (figureBehindEnPassantPawn instanceof Pawn
                && !figureBehindEnPassantPawn.getColour().equals(enPassantPawn.getColour())) capture(enPassantPawn);

                enPassantPawn = null;
            }
            selectedFigure = null;
            playerToMove = playerToMove.equals(Colour.white) ? Colour.black : Colour.white;
            isPlayerToMoveInCheck = playerToMove.equals(Colour.white) ? WhiteKing.isInCheck() : BlackKing.isInCheck();
        }
    }
}