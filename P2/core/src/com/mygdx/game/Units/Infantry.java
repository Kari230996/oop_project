package com.mygdx.game.Units;

import java.util.ArrayList;

public abstract class Infantry extends Unit {

    public int[] causeDamage;

    public Infantry(String name, String type, int hp, int speed, int armor, int hit, int x, int y, int[] causeDamage) {
        super(name, type, hp, speed, armor, hit, x, y);
        this.causeDamage = causeDamage;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\t☠️%-3d        ", (causeDamage[0] + causeDamage[1]) / 2);
    }

    @Override
    public void step(ArrayList<Unit> attackers, ArrayList<Unit> targets) {
        Unit target = findTarget(targets);
        if (isAlive) {
            if (position.getDist(target.position) < 1.5) {
                if (target.getArmor() < this.hit) {
                    target.getDamage(causeDamage[1]);
                } else {
                    target.getDamage(causeDamage[0]);
                }
            } else {
                this.move(target, attackers);
            }
        }
    }

    public void move(Unit unitAim, ArrayList<Unit> friends) {
        Position temp = this.position;
        int dX = this.position.getX() - unitAim.position.getX();
        int dY = this.position.getY() - unitAim.position.getY();
        if (Math.abs(dX) >= Math.abs(dY)) {
            if (dX > 0) {
                temp.setX(temp.getX() - 1);
                if (checkCells(temp, friends)) {
                    this.position.setX(this.position.getX() - 1);
                }
            } else {
                temp.setX(temp.getX() + 1);
                if (checkCells(temp, friends)) {
                    this.position.setX(this.position.getX() + 1);
                }
            }
        } else {
            if (dY > 0) {
                temp.setY(temp.getY() - 1);
                if (checkCells(temp, friends)) {
                    this.position.setY(this.position.getY() - 1);
                }
            } else {
                temp.setY(temp.getY() + 1);
                if (checkCells(temp, friends)) {
                    this.position.setY(this.position.getY() + 1);
                }
            }
        }
    }

    public boolean checkCells(Position newPosition, ArrayList<Unit> friends) {
        for (Unit friend : friends) {
            if (newPosition.isEquals(friend.position)) return false;
        }
        return true;
    }

}
