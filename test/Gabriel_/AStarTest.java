package Gabriel_;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AStarTest {
    // map assumes a X,Y coordinate system in a 3x4 grid
    int[][] map = {{1,3}, {2,2}, {0,3}, {0,2}, {2,1}, {2,3}, {1, 2}, {1, 1}, {1, 0}, {0,0}};
    @BeforeAll
    static void setUp() {
        MainTest.convertToAdjList();
    }
    @Test
    void aStar() {
        int result = AStar.aStar(MainTest.adjListDirectedWeightedPGB, 0, 9,
                (a,b) -> Math.abs(map[a][0] - map[b][0]) + Math.abs(map[a][1] - map[b][1]));
        assertEquals(17, result);
    }
}