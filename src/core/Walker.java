package core;

import org.junit.jupiter.api.Test;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Walker {
    private int worldHeight;
    private int worldWidth;
    private Random seed;
    public int x;
    public int y;
    private Checker checker;
    private TETile[][] world;
    private Direction directionGen;
    private int[] lastDir;
    private KhangTheme theme;
    private HallWays hallWays;

    public Walker(int worldWidth, int worldHeight,
                  Random seed, TETile[][] world, KhangTheme theme) {
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;
        this.seed = seed;
        x = randomGenerated(worldWidth, 1);
        y = randomGenerated(worldHeight, 1);
        checker = new Checker(worldWidth, worldHeight);
        this.world = world;
        world[x][y] = theme.getWall(); //Comment this line out later
        directionGen = new Direction(seed);
        lastDir = directionGen.pickRandomDir(new int[] {0, 0});
        this.theme = theme;
        hallWays = new HallWays(worldWidth, worldHeight);

        double roomDensity = 0.45;
        int worldArea = worldHeight * worldWidth;
        int tileUsed = 0;
        int i = 0;
//        while (i < 3) {
//            roomGenerator();
//            hallWayGenerator();
//            i++;
//        }
//        roomGenerator();
//        hallWayGenerator();
//        roomGenerator();
        double percentUsed = tileUsed * 1.0 / worldArea;
        while (percentUsed < roomDensity) {
            tileUsed += roomGenerator();
            tileUsed += hallWayGenerator();
            percentUsed = tileUsed * 1.0 / worldArea;
        }
        addWall();
    }

    public int roomGenerator() {
        int tileUsed = 0;
        int roomWidth = pickRoomEdge();
        int roomHeight = pickRoomEdge();
        if (checkRoomTiles(roomWidth, roomHeight)) {
            int widthLength = roomWidth / 2;
            int heightLength = roomHeight / 2;
            int tempX = x - widthLength;
            int tempY = y - heightLength;
            Room r = new Room(this, roomWidth, roomHeight, theme);
            for (int row = 0; row < roomWidth; row++) {
                for (int col = 0; col < roomHeight; col++) {
                    if (world[tempX][tempY] == theme.getBackground()) {
                        tileUsed++;
                    }
                    if (row == 0 || row == roomWidth - 1 ||
                        col == 0 || col == roomHeight - 1) {
                        if (!hallWays.isHallWayTile(tempX, tempY)) {
                            world[tempX][tempY] = theme.getWall();
                        }
                    } else {
                        world[tempX][tempY] = theme.getFloor();
                    }
//                    world[tempX][tempY] = theme.getFloor();
                    tempY++;
                }
                tempY = y - heightLength;
                tempX++;
            }
        }
//        world[x][y] = theme.getAvatar();
        return tileUsed;
    }

    //Make sure to replace wall theme here
    private boolean checkRoomEdge(int roomWidth, int roomHeight) {
        int widthLength = roomWidth / 2;
        int heightLength = roomHeight / 2;
        return checker.checkBound(widthLength, Direction.getWEST(), this) &&
                checker.checkBound(widthLength, Direction.getEAST(), this) &&
                checker.checkBound(heightLength, Direction.getNORTH(), this) &&
                checker.checkBound(heightLength, Direction.getSOUTH(), this);
    }

    public boolean checkRoomTiles(int roomWidth, int roomHeight) {
        int widthLength = roomWidth / 2;
        int heightLength = roomHeight / 2;
        if (checkRoomEdge(roomWidth, roomHeight)) {
            int tempX = x - widthLength;
            int tempY = y - heightLength;
            for (int row = 0; row < roomWidth; row++) {
                for (int col = 0; col < heightLength; col++) {
                    if (world[tempX][tempY] == theme.getWall()) {
                        return false;
                    }
                    tempY++;
                }
                tempY = y - heightLength;
                tempX++;
            }
            return true;
        }
        return false;
    }

    public int hallWayGenerator() {
        int tileUsed = 0;
        int length = pickHallLength();
        int[] randDir = directionGen.pickRandomDir(lastDir);
        while (!checker.checkBound(length, randDir, this)) {
            length = pickHallLength();
            randDir = directionGen.pickRandomDir(lastDir);
        }
//        if (Arrays.equals(randDir, Direction.getNORTH())) {
//            for (int col = 0; col < length; col++) {
//                if (world[x][y] != theme.getFloor()) {
//                    tileUsed++;
//                }
//
//            }
//        }

        int dirX = randDir[0];
        int dirY = randDir[1];
        Room hallWay;
        if (dirX == 0) {
            hallWay = new Room(this, 3, length, theme);
        } else {
            hallWay = new Room(this, length, 3, theme);
        }
        for (int i = 0; i < length; i++) {
            if (world[x][y] != theme.getFloor()) {
                tileUsed++;
            }
            world[x][y] = theme.getFloor();
            hallWays.setHallWayTile(x, y);
            x += dirX;
            y += dirY;
        }
        lastDir = randDir;
        return tileUsed;
    }

    //for test walker
//    public Walker(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * randomly generate a number using seed
     * @param upperBound exclusive
     * @param lowerBound inclusive
     * @return a number x in lowerBound <= x < upperbound
     */
    private int randomGenerated(int upperBound, int lowerBound) {
        return seed.nextInt(upperBound) + lowerBound;
    }

    private int pickRoomEdge() {
        return randomGenerated(9, 4);
    }

    private int pickHallLength() {
        return randomGenerated(11, 5);
    }

    private void addWall() {
        for (int row = 1; row < worldWidth - 1; row++) {
            for (int col = 1; col < worldHeight - 1; col++) {
                if (world[row][col] == theme.getBackground()) {
                    if (world[row - 1][col] == theme.getFloor() ||
                        world[row + 1][col] == theme.getFloor() ||
                        world[row][col + 1] == theme.getFloor() ||
                        world[row][col - 1] == theme.getFloor()) {
                        // add wall here.
                        world[row][col] = theme.getWall();
                    }
                }
            }
        }
    }
}
