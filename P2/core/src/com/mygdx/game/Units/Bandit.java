package com.mygdx.game.Units;

public class Bandit extends Infantry {

    public Bandit(String name, int x, int y) {
        super(name, "Bandit", 10, 6, 3, 8, x, y, new int[]{2, 4});
    }


    @Override
    public String getInfo() {
        return "Bandit";
    }

}
