package core;

public class HallWays {
    private boolean[][] hallWayTiles;

    public HallWays(int worldWidth, int worldHeight) {
        hallWayTiles = new boolean[worldWidth][worldHeight];
    }

    public void setHallWayTile(int x, int y) {
        hallWayTiles[x][y] = true;
    }

    public boolean isHallWayTile(int x, int y) {
        return hallWayTiles[x][y];
    }
}
