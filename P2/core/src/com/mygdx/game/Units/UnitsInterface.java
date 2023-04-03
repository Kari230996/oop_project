package com.mygdx.game.Units;

import java.util.ArrayList;

public interface UnitsInterface {
    void step(ArrayList<Unit> attackers, ArrayList<Unit> targets);

    String getInfo();

    void healing(int addHp);

    int compare(Unit o1, Unit o2);
}
