package com.mygdx.game.Units;

public class Witch extends Magician {

    public Witch(String name, int x, int y) {
        super(name, "Witch", 30, 9, 12, 17, x, y, 5);
    }

    @Override
    public String getInfo() {
        return "Witch";
    }

}
