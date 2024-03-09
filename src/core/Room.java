package core;

public class Room {
    // Center of the room
    private int x;
    private int y;
    private int roomWidth;
    private int roomHeight;
    private KhangTheme theme;

    public Room (Walker w, int roomWidth, int roomHeight, KhangTheme theme) {
        x = w.getX();
        y = w.getY();
        this.roomWidth = roomWidth;
        this.roomHeight = roomHeight;
        this.theme = theme;
    }

//    public int roomGenerator() {
//        int widthLength = roomWidth / 2;
//        int heightLength = roomHeight / 2;
//
//    }



}
