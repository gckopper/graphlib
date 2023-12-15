package Gabriel_;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DfsTest {
    @BeforeAll
    static void setUp() {
        MainTest.convertToAdjList();
    }
    @Test
    public void dfsSearchTest() {
        int[][] result1 = Dfs.dfs(MainTest.adjListTGB);
        assertAll(() -> assertArrayEquals(new int[]{1, 3, 2, 4, 8, 7},result1[0]),
                () -> assertArrayEquals(new int[]{12, 6, 11, 5, 9, 10},result1[1]));
        int[][] result2 = Dfs.dfs(MainTest.adjListBeegCycle);
        assertAll(() -> assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, result2[0]),
                () -> assertArrayEquals(new int[]{16, 15, 14, 13, 12, 11, 10, 9}, result2[1]));
        int[][] result3 = Dfs.dfs(MainTest.adjListDegenerate);
        assertAll(() -> assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8},result3[0]),
                () -> assertArrayEquals(new int[]{16, 15, 14, 13, 12, 11, 10, 9},result3[1]));
    }
}
