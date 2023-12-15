package Gabriel_;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CycleDetectorTest {
    @BeforeAll
    static void setUp() {
        MainTest.convertToAdjList();
    }
    @Test
    public void isCyclicTest() {
        assertTrue(CycleDetector.isCyclic(MainTest.adjListTGB));
        assertTrue(CycleDetector.isCyclic(MainTest.adjListBeegCycle));
        assertFalse(CycleDetector.isCyclic(MainTest.adjListDegenerate));
    }
}
