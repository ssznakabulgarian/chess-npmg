package GameLogic;

import Common.Colour;
import Common.Position;
import Figures.Figure;
import Figures.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard{
    private List<Figure> figures;
    private Figure enPassantPawn;

    public Board() {
        this.figures = new ArrayList<Figure>();
        this.enPassantPawn = null;
        //populateBoard();
    }

    public void setEnPassantPawn(Pawn enPassantPawn){
        this.enPassantPawn = enPassantPawn;
    }
    public Figure getEnPassantPawn(){
        return this.enPassantPawn;
    }

    public boolean isSquareEmpty(Position position) {
        return false;
    }
    public Figure getFigureAt(Position position) {
        return null;
    }
    public Colour getPlayerToMove() {
        return null;
    }

    @Override
    public Figure[][] getField() {
        return new Figure[8][8];
    }
}