import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PrimTest {

    @BeforeEach
    void setUp() {
        MainTest.convertToAdjList();
    }

    @Test
    void primPGB() {
        int[] a = Prim.prim(MainTest.adjListUndirectedWeightedPGB, 0);
        assertArrayEquals(new int[]{0, 3, 1, 5, 2, 2, 6, 8, 1, 2},
                a);
        assertEquals(30,
                Arrays.stream(a).sum());
    }
    @Test
    void primTGB1() {
        int[] a = Prim.prim(MainTest.adjListUndirectedWeightedTGB1, 0);
        assertArrayEquals(new int[]{0, 3, 7, 1, 5, 8, 17, 19, 2, 6, 4},
                a);
        assertEquals(72,
                Arrays.stream(a).sum());
    }
}