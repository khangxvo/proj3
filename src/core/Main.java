package core;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // build your own world!
        int width = 50;
        int height = 50;
        Random seed = new Random(777);
        KhangThemes themes = new KhangThemes(seed);
        KhangTheme theme = themes.getThemes().get("basic");

        KhangWorld world = new KhangWorld(width, height, seed, theme);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(world.getWorld());

    }

}
