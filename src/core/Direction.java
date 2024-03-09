package core;

import java.util.Random;

public class Direction {
    private static final int[] NORTH = {0, 1};
    private static final int[] SOUTH = {0, -1};
    private static final int[] WEST = {-1, 0};
    private static final int[] EAST = {1, 0};
    private Random seed;

    public Direction (Random seed) {
        this.seed = seed;
    }

    private int[] pickDirection() {
        int randInt = seed.nextInt(4);
        switch (randInt) {
            case 0:
                return NORTH;
            case 1:
                return SOUTH;
            case 2:
                return WEST;
            case 3:
                return EAST;
        }
        return new int[] {0, 0};
    }

    public int[] pickRandomDir(int[] lastDir) {
        int[] currDir = pickDirection();
        while (currDir[0] == -lastDir[0] &&
                currDir[1] == -lastDir[1]) {
            currDir = pickDirection();
        }
        return currDir;
    }

    public static int[] getEAST() {
        return EAST;
    }

    public static int[] getNORTH() {
        return NORTH;
    }

    public static int[] getSOUTH() {
        return SOUTH;
    }

    public static int[] getWEST() {
        return WEST;
    }
}
