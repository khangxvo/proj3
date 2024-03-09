import core.Checker;
import core.Direction;
import org.junit.jupiter.api.Test;
import tileengine.Tileset;

public class TestRoomChecker {
    int width = 5;
    int height = 5;
    Checker checker = new Checker(width, height);
    char[][] world = new char[width][height];
    public void addChar() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = ' ';
            }
        }
    }

    private boolean checkRoomEdge(int roomWidth, int roomHeight, int x, int y) {
        int widthLength = roomWidth / 2;
        int heightLength = roomHeight / 2;
        return checker.checkBound2(widthLength, Direction.getWEST(), x, y) &&
                checker.checkBound2(widthLength, Direction.getEAST(), x, y) &&
                checker.checkBound2(heightLength, Direction.getNORTH(), x, y) &&
                checker.checkBound2(heightLength, Direction.getSOUTH(), x, y);
    }

    public boolean checkRoomTiles(int roomWidth, int roomHeight, int x, int y) {
        int widthLength = roomWidth / 2;
        int heightLength = roomHeight / 2;
        if (checkRoomEdge(roomWidth, roomHeight, x, y)) {
            int tempX = x - widthLength;
            int tempY = y - heightLength;
            for (int row = 0; row < roomWidth; row++) {
                for (int col = 0; col < heightLength; col++) {
                    if (world[tempX][tempY] == '·') {
                        return false;
                    }
                    tempY++;
                }
                tempX++;
            }
            return true;
        }
        return false;
    }

    @Test
    public void testBasic() {
        addChar();
//        world[4][4] = '·';
        boolean result = checkRoomTiles(4,3, 2, 2);
        System.out.println(result);
    }
}
