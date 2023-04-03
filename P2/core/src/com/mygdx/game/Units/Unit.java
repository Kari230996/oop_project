package com.mygdx.game.Units;

import java.util.ArrayList;

public abstract class Unit implements UnitsInterface {

    protected final String NAME;
    protected final String TYPE;
    protected int hp;
    protected int maxHp;
    protected int speed;
    protected int armor;
    protected int hit;
    protected boolean isAlive = true;
    protected Position position;


    public Unit(String name, String type, int hp, int speed, int armor, int hit, int x, int y) {
        NAME = name;
        TYPE = type;
        this.hp = hp;
        this.maxHp = hp;
        this.speed = speed;
        this.armor = armor;
        this.hit = hit;
        position = new Position(x, y);
    }


    public String getTYPE() {
        return TYPE;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getArmor() {
        return armor;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Position getPosition() {
        return position;
    }

    public void getDamage(int damage) {
        if (hp > damage) {
            hp = this.hp - damage;
        } else {
            hp = 0;
            isAlive = false;
        }
    }

    @Override
    public void healing(int addHp) {
        hp = Math.min(hp + addHp, maxHp);
    }

    @Override
    public String toString() {
        return String.format("\t%-12s\t⚔️ %-3d\t\uD83D\uDEE1 %-3d\t♥️%-3d%%", TYPE, hit, armor, (hp * 100 / maxHp));
    }

    @Override
    public int compare(Unit o1, Unit o2) {
        return o1.getSpeed() - o2.getSpeed();
    }

    protected Unit findTarget(ArrayList<Unit> team) {
        float minDist = 13;
        int index = 0;
        for (int i = 0; i < team.size(); i++) {
            if (team.get(i).isAlive()) {
                float dist = position.getDist(team.get(i).position);
                if (dist < minDist) {
                    minDist = dist;
                    index = i;
                }
            }
        }
        return team.get(index);
    }

}
