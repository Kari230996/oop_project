package com.mygdx.game.Units;

public class Position {
    protected int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public float getDist(Position pos) {
        return (float) Math.sqrt(Math.pow((this.x-pos.getX()), 2) + Math.pow((this.y - pos.getY()), 2));
    }

    public boolean isEquals(Position position) {
        return this.x == position.getX() && this.y == position.getY();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
