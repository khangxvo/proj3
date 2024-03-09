package core;
import tileengine.TETile;

public class KhangTheme {
    private TETile background;
    private TETile wall;
    private TETile floor;

    public TETile avatar;

    public KhangTheme (TETile b, TETile w, TETile f, TETile a) {
        floor = f;
        wall = w;
        background = b;
        avatar = a;
    }

    public TETile getBackground() {
        return background;
    }
    public TETile getWall() {
        return wall;
    }

    public TETile getFloor() {
        return floor;
    }

    public TETile getAvatar() {
        return avatar;
    }
}
