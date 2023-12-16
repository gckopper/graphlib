package Gabriel_;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BfsTest {

    @BeforeAll
    static void setUp() {
        MainTest.convertToAdjList();
    }
    @Test
    void bfs() {
        assertArrayEquals(new int[]{0,2,1,2,1,1}, Bfs.bfs(MainTest.adjListTGB, 0));
        assertArrayEquals(new int[]{0,1,2,3,4,3,2,1}, Bfs.bfs(MainTest.adjListBeegCycle, 0));
        assertArrayEquals(new int[]{0,1,2,3,4,5,6,7}, Bfs.bfs(MainTest.adjListDegenerate, 0));
    }
}