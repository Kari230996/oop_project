package com.mygdx.game.Units;

public class Sniper extends Shooter {

    public Sniper(String name, int x, int y) {
        super(name, "Sniper", 15, 9, 10, 12, x, y, 32, new int[]{8, 10});
    }

    @Override
    public String getInfo() {
        return "Sniper";
    }

}
