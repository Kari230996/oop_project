package com.mygdx.game.Units;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Magician extends Unit {

    protected boolean mana;
    protected int causeDamage;

    public Magician(String name, String type, int hp, int speed, int armor, int hit, int x, int y, int causeDamage) {
        super(name, type, hp, speed, armor, hit, x, y);
        this.mana = true;
        this.causeDamage = causeDamage;
    }

    @Override
    public void step(ArrayList<Unit> attackers, ArrayList<Unit> targets) {
        if (isAlive && mana) {
            ArrayList<Unit> victims = new ArrayList<>();
            for (Unit unit : attackers) {
                if (!(unit.TYPE.equals("Monk")) && !(unit.TYPE.equals("Witch")) && !(unit.TYPE.equals("Peasant"))) {
                    victims.add(unit);
                }
            }
            victims.sort(new Comparator<Unit>() {
                @Override
                public int compare(Unit o1, Unit o2) {
                    return o1.getHp() - o2.getHp();
                }
            });

            for (Unit victim : victims) {
                if (victim.isAlive() && victim.hp < victim.maxHp) {
                    healHero(victim);
                    mana = false;
                    break;
                }
            }
        }
    }

    public void healHero(Unit target) {
        if (mana) {
            target.healing(causeDamage);
            mana = false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\t   \t        ";
    }

}
