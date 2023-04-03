package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.GANG_SIZE;
import static com.mygdx.game.MyGdxGame.teamBlack;
import static com.mygdx.game.MyGdxGame.teamWhite;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Units.*;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Init {

    public static void createTeams() {

        teamWhite = new ArrayList<>();
        for (int i = GANG_SIZE; i > 0; i--) {
            switch (new Random().nextInt(4)) {
                case 0: teamWhite.add(new Monk(getName(), 1, i)); break;
                case 1: teamWhite.add(new XBowMan(getName(), 1, i)); break;
                case 2: teamWhite.add(new Spearman(getName(), 1, i)); break;
                default: teamWhite.add(new Peasant(getName(), 1, i));
            }
        }

        teamBlack = new ArrayList<>();
        for (int i = GANG_SIZE; i > 0; i--) {
            switch (new Random().nextInt(4)) {
                case 0:  teamBlack.add(new Witch(getName(), 10, i)); break;
                case 1:  teamBlack.add(new Sniper(getName(), 10, i)); break;
                case 2:  teamBlack.add(new Bandit(getName(), 10, i)); break;
                default: teamBlack.add(new Peasant(getName(), 10, i));
            }
        }

    }

    public static void makeStep() {

        teamWhite.sort(new Comparator<Unit>() {
            @Override
            public int compare(Unit o1, Unit o2) {
                return o2.getSpeed() - o1.getSpeed();
            }
        });

        teamBlack.sort(new Comparator<Unit>() {
            @Override
            public int compare(Unit o1, Unit o2) {
                return o2.getSpeed() - o1.getSpeed();
            }
        });

        for (int i = 0; i < GANG_SIZE; i++) {
            teamWhite.get(i).step(teamWhite, teamBlack);
            teamBlack.get(i).step(teamBlack, teamWhite);
        }

        if (!Init.teamIsAlive(teamWhite) || !Init.teamIsAlive(teamBlack)) {
            System.out.print("Game Over! ");
            if (Init.teamIsAlive(teamWhite)) {
                System.out.println("Black team wins");
            } else {
                System.out.println("White team wins");
            }

        }

    }

    private static String getName() {
        return UnitsNames.values()[new Random().nextInt(UnitsNames.values().length)].toString();
    }

    public static boolean teamIsAlive(ArrayList<Unit>team) {
        for (Unit unit:team) {
            if (unit.isAlive()) return true;
        }
        return false;
    }

}
