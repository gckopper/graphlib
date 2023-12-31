package Gabriel_;
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

    static ArrayList<Main.Edge>[] adjListUndirectedWeightedTGB1 = new ArrayList[]{
            new ArrayList<>(Arrays.asList(new Main.Edge(1, 3), new Main.Edge(2, 7), new Main.Edge(9, 10))),
            new ArrayList<>(Arrays.asList(new Main.Edge(0, 3), new Main.Edge(3, 9), new Main.Edge(2, 11), new Main.Edge(4, 15))),
            new ArrayList<>(Arrays.asList(new Main.Edge(3, 1), new Main.Edge(9, 6), new Main.Edge(0, 7), new Main.Edge(1, 11), new Main.Edge(5, 23))),
            new ArrayList<>(Arrays.asList(new Main.Edge(2, 1), new Main.Edge(4, 5), new Main.Edge(1, 9), new Main.Edge(5, 13))),
            new ArrayList<>(Arrays.asList(new Main.Edge(3, 5), new Main.Edge(1, 15), new Main.Edge(6, 17), new Main.Edge(7, 19), new Main.Edge(5, 21))),
            new ArrayList<>(Arrays.asList(new Main.Edge(9, 8), new Main.Edge(10, 12), new Main.Edge(4, 21), new Main.Edge(2, 23), new Main.Edge(8, 33))),
            new ArrayList<>(Arrays.asList(new Main.Edge(4, 17), new Main.Edge(7, 25), new Main.Edge(8, 27))),
            new ArrayList<>(Arrays.asList(new Main.Edge(4, 19), new Main.Edge(6, 25), new Main.Edge(5, 29), new Main.Edge(8, 31))),
            new ArrayList<>(Arrays.asList(new Main.Edge(10, 2), new Main.Edge(6, 27), new Main.Edge(7, 31), new Main.Edge(5, 33))),
            new ArrayList<>(Arrays.asList(new Main.Edge(10, 4), new Main.Edge(2, 6), new Main.Edge(5, 8), new Main.Edge(0, 10))),
            new ArrayList<>(Arrays.asList(new Main.Edge(8, 2), new Main.Edge(9, 4), new Main.Edge(5, 12)))
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
}
