package com.mygdx.game.Units;

import java.util.ArrayList;

public abstract class Shooter extends Unit implements UnitsInterface {

    protected int arrows;
    public int[] causeDamage;

    public Shooter(String name, String type, int hp, int speed, int armor, int hit, int x, int y, int arrows, int[] causeDamage) {
        super(name, type, hp, speed, armor, hit, x, y);
        this.arrows = arrows;
        this.causeDamage = causeDamage;
    }


    @Override
    public void step(ArrayList<Unit> attackers, ArrayList<Unit> targets) {
        if ((arrows > 0) && isAlive) {
            Unit target = findTarget(targets);
            if (target.getArmor() < this.hit) {
                target.getDamage(this.hitPower(target));
            } else {
                target.getDamage(this.hitPower(target) / 2);
            }
            arrows--;
            for (Unit unit : attackers) {
                if (unit.getInfo().equals("Peasant")) {
                    Peasant deliver = (Peasant) unit;
                    if (deliver.getDelivery()) {
                        arrows++;
                        deliver.hasDelivery = false;
                        break;
                    }
                }
            }
        }
    }

    public int hitPower(Unit target) {
        int attackPower;
        if (this.position.getDist(target.position) < 5) {
            attackPower = this.causeDamage[1];
        } else if (this.position.getDist(target.position) >= 5) {
            attackPower = this.causeDamage[0];
        } else {
            attackPower = (this.causeDamage[0] + this.causeDamage[1]) / 2;
        }
        return attackPower;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\t☠️%-3d\t➹%-3d", (causeDamage[0] + causeDamage[1]) / 2, arrows);
    }

}
