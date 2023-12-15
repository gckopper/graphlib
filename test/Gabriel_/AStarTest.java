package Gabriel_;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AStarTest {
    // mapa assume uma grid com 3 colunas e 4 linhas
    int[][] mapa = {{1,3}, {2,2}, {0,3}, {0,2}, {2,1}, {2,3}, {1, 2}, {1, 1}, {1, 0}, {0,0}};
    @BeforeAll
    static void setUp() {
        MainTest.convertToAdjList();
    }
    @Test
    void aStar() {
        int result = AStar.aStar(MainTest.adjListDirectedWeightedPGB, 0, 9, (a,b) -> {
            return Math.abs(mapa[a][0] - mapa[b][0]) + Math.abs(mapa[a][1] - mapa[b][1]);
        });
        assertEquals(17, result);
    }
}