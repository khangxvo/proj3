package core;

import org.knowm.xchart.style.theme.Theme;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.Random;

public class KhangWorld {
    private Walker w;
    private TETile[][] world;
    private Random seedGenerator;
    private KhangTheme theme;

    public KhangWorld(int width, int height, Random seed, KhangTheme theme) {
        world = new TETile[width][height];
        this.seedGenerator = seed;
        this.theme = theme;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        w = new Walker(width, height, seedGenerator, world, theme);
    }

    public TETile[][] getWorld() {
        return world;
    }
}
