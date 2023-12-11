import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {
    static final int[][] edgesTGB = {{0,2}, {0,4}, {0,5}, {1,2}, {1,3}, {3,2}, {5,2}, {5,4}};
    static final int[][] edgesDirectedWeightedPGB = {{0,2,1}, {0,4,2}, {0,5,2}, {1,4,3}, {1,5,4}, {2,3,5}, {3,7,8}, {3,9,12}, {4,2,8}, {4,5,10}, {4,6,11}, {4,7,12}, {6,7,6}, {7,8,1}, {8,9,2}};
    static final int[][] edgesBeegCycle = {{0,1}, {1,2}, {2,3}, {3,4}, {4,5}, {5,6}, {6,7}, {7,0}};
    static final int[][] edgesDegenerate = {{0,1}, {1,2}, {2,3}, {3,4}, {4,5}, {5,6}, {6,7}};

    static ArrayList<Integer>[] adjListTGB;
    static ArrayList<Integer>[] adjListBeegCycle;
    static ArrayList<Integer>[] adjListDegenerate;
    static ArrayList<Main.Edge>[]  adjListDirectedWeightedPGB;


    @BeforeAll
    public static void convertToAdjList() {
        adjListTGB = Main.toAdjList(edgesTGB, 6);
        adjListBeegCycle = Main.toAdjList(edgesBeegCycle, 8);
        adjListDegenerate = Main.toAdjList(edgesDegenerate, 8);
        adjListDirectedWeightedPGB = Main.toAdjListDirectedWeighted(edgesDirectedWeightedPGB, 10);
    }

    @Test
    public void toAdjListTest() {
        int[][] graph1 = {{1,7}, {0,2}, {1,3}, {2,4}, {3,5}, {4,6}, {5,7}, {0,6}};
        for (int i = 0; i < graph1.length; i++) {
            for (int j = 0; j < graph1[i].length; j++) {
                assertEquals(graph1[i][j], adjListBeegCycle[i].get(j));
            }
        }
        int[][] graph2 = {{2,4,5}, {2,3}, {0,1,3,5}, {1,2}, {0,5}, {0,2,4}};
    }

    @Test
    public void dfsSearchTest() {
        int[][] result1 = Main.dfsSearch(adjListTGB);
        assertAll(() -> assertArrayEquals(new int[]{1, 3, 2, 4, 8, 7},result1[0]),
                () -> assertArrayEquals(new int[]{12, 6, 11, 5, 9, 10},result1[1]));
        int[][] result2 = Main.dfsSearch(adjListBeegCycle);
        assertAll(() -> assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, result2[0]),
                () -> assertArrayEquals(new int[]{16, 15, 14, 13, 12, 11, 10, 9}, result2[1]));
        int[][] result3 = Main.dfsSearch(adjListDegenerate);
        assertAll(() -> assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8},result3[0]),
                () -> assertArrayEquals(new int[]{16, 15, 14, 13, 12, 11, 10, 9},result3[1]));
    }

    @Test
    public void isCyclicTest() {
        assertTrue(Main.isCyclic(adjListTGB));
        assertTrue(Main.isCyclic(adjListBeegCycle));
        assertFalse(Main.isCyclic(adjListDegenerate));
    }

    @Test
    public void dijkstraTest() {
        System.out.println(Arrays.toString(Main.dijkstra(adjListDirectedWeightedPGB, 0)));
    }
}
