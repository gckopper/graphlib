package Gabriel_;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Gabriel_.Dijkstra;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @BeforeAll
    static void setUp() {
        MainTest.convertToAdjList();
    }

    @Test
    void dijkstra() {
        assertArrayEquals(new int[]{0, Integer.MAX_VALUE, 1, 6, 2, 2, 13, 14, 15, 17},
                Dijkstra.dijkstra(MainTest.adjListDirectedWeightedPGB, 0));
    }
}