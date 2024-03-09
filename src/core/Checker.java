package core;

public class Checker {
    private int northBound;
    private int westBound;
    private int southBound;
    private int eastBound;

    public Checker(int worldWidth, int worldHeight) {
        westBound = 2;
        southBound = 2;
        northBound = worldHeight - 3;
        eastBound = worldWidth - 3;
    }

    public boolean checkBound(int length, int[] direction, Walker w) {
        int x = w.getX();
        int y = w.getY();
        if (direction[0] == 0) {
            if (direction[1] == 1) {
                return w.getY() + length <= northBound;
            } else if (direction[1] == -1){
                return w.getY() - length >= southBound;
            }
        } else if (direction[1] == 0){
            if (direction[0] == 1) {
                return w.getX() + length <= eastBound;
            } else if (direction[0] == -1){
                return w.getX() - length >= westBound;
            }
        }
        return false;
    }

    //For test only
    public boolean checkBound2(int length, int[] direction, int x, int y) {
        if (direction[0] == 0) {
            if (direction[1] == 1) {
                return y + length <= northBound;
            } else if (direction[1] == -1){
                return y - length >= southBound;
            }
        } else if (direction[1] == 0){
            if (direction[0] == 1) {
                return x + length <= eastBound;
            } else if (direction[0] == -1){
                return x - length >= westBound;
            }
        }
        return false;
    }
}
