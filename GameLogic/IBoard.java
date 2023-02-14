package GameLogic;

import Common.Colour;
import Common.Position;
import Figures.Figure;

public interface IBoard {
    boolean isSquareEmpty(Position position);
    Figure getFigureAt(Position position);
    Colour getPlayerToMove();
    Figure[][] getField();
}
