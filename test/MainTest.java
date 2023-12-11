import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    static final int[][] edgesTGB = {{0,2}, {0,4}, {0,5}, {1,2}, {1,3}, {3,2}, {5,2}, {5,4}};
    static final int[][] edgesDirectedWeightedPGB = {{0,2,1}, {0,4,2}, {0,5,2}, {1,4,3}, {1,5,4}, {2,3,5}, {3,7,8}, {3,9,12}, {4,2,8}, {4,5,10}, {4,6,11}, {4,7,12}, {6,7,6}, {7,8,1}, {8,9,2}};
    static final int[][] edgesBeegCycle = {{0,1}, {1,2}, {2,3}, {3,4}, {4,5}, {5,6}, {6,7}, {7,0}};
    static final int[][] edgesDegenerate = {{0,1}, {1,2}, {2,3}, {3,4}, {4,5}, {5,6}, {6,7}};

    static ArrayList<Integer>[] adjListTGB;
    static ArrayList<Integer>[] adjListBeegCycle;
    static ArrayList<Integer>[] adjListDegenerate;
    static ArrayList<Main.Edge>[] adjListDirectedWeightedPGB;
    static ArrayList<Main.Edge>[] adjListUndirectedWeightedPGB = new ArrayList[]{
            new ArrayList<>(Arrays.asList(new Main.Edge(2, 1), new Main.Edge(4, 2), new Main.Edge(5, 2))),
            new ArrayList<>(Arrays.asList(new Main.Edge(4, 3), new Main.Edge(5, 4))),
            new ArrayList<>(Arrays.asList(new Main.Edge(0, 1), new Main.Edge(3, 5), new Main.Edge(4, 8))),
            new ArrayList<>(Arrays.asList(new Main.Edge(2, 5), new Main.Edge(7, 8), new Main.Edge(9, 9))),
            new ArrayList<>(Arrays.asList(new Main.Edge(0, 2), new Main.Edge(1, 3), new Main.Edge(2, 8), new Main.Edge(6, 11), new Main.Edge(7, 12))),
            new ArrayList<>(Arrays.asList(new Main.Edge(0, 2), new Main.Edge(1, 4), new Main.Edge(4, 10))),
            new ArrayList<>(Arrays.asList(new Main.Edge(7, 6), new Main.Edge(4, 11))),
            new ArrayList<>(Arrays.asList(new Main.Edge(8, 1), new Main.Edge(6, 6), new Main.Edge(3, 8), new Main.Edge(4, 12))),
            new ArrayList<>(Arrays.asList(new Main.Edge(7, 1), new Main.Edge(9, 2))),
            new ArrayList<>(Arrays.asList(new Main.Edge(8, 2), new Main.Edge(3, 9))),
    };


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
        for (int i = 0; i < graph2.length; i++) {
            for (int j = 0; j < graph2[i].length; j++) {
                assertEquals(graph2[i][j], adjListTGB[i].get(j));
            }
        }
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
}
