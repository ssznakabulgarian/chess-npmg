package Common;

import java.util.Objects;

public class Position {
    public int x;
    public int y;
    public Position(int x, int y) {
        this.x=x;
        this.y=y;
    }
    public Position(Position position) {
        this(position.x, position.y);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return this.x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return this.y;
    }
}