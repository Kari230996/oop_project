package com.mygdx.game.Units;

import java.util.ArrayList;

public class Peasant extends Unit {

    protected boolean hasDelivery;

    public Peasant(String name, String type, int hp, int speed, int armor, int hit, int x, int y, boolean hasDelivery) {
        super(name, type, hp, speed, armor, hit, x, y);
        this.hasDelivery = hasDelivery;
    }

    public Peasant(String name, int x, int y) {
        this(name, "Peasant", 1, 3, 1, 1, x, y,true);
    }


    public boolean getDelivery() {
        return hasDelivery;
    }

    @Override
    public void step(ArrayList<Unit> attackers, ArrayList<Unit> targets) {
        if (isAlive) System.out.print(' ');
    }

    @Override
    public String getInfo() {
        return "Peasant";
    }

    @Override
    public String toString() {
        return super.toString() + "                ";
    }
}
