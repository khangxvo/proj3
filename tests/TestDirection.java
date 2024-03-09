import core.Direction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class TestDirection {
    @Test
    public void testBasic() {
        int[] lastDir = {1, 0};
        Random seed = new Random();
        Direction dirGen = new Direction(seed);
        int[] result = dirGen.pickRandomDir(lastDir);
//        System.out.println(result[0]);
//        while (result[0] != 0) {
//            result = dirGen.pickRandomDir(lastDir);
//        }
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
