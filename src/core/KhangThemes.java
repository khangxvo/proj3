package core;

import tileengine.Tileset;

import java.util.HashMap;
import java.util.Random;

public class KhangThemes {
    private HashMap<String, KhangTheme> themes = new HashMap<>();
    private final KhangTheme BASIC = new KhangTheme(Tileset.NOTHING, Tileset.WALL, Tileset.FLOOR, Tileset.AVATAR);
    private Random seed;

    public HashMap<String, KhangTheme> getThemes() {
        themes.put("basic", BASIC);
        return themes;
    }

    public KhangThemes(Random seed) {
        this.seed = seed;
    }
}
