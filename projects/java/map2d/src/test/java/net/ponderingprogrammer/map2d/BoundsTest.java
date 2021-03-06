package net.ponderingprogrammer.map2d;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoundsTest {
    @Test
    @DisplayName("Max = Min + Size - 1")
    void testMax() {
        var bounds = new Bounds(1, 2, 3, 4);
        Assertions.assertEquals(3, bounds.getMaxX(), "Max X should be 3");
        Assertions.assertEquals(5, bounds.getMaxY(), "Max Y should be 5");
    }

    @Test
    @DisplayName("Illegal negative size")
    void testIllegalNegativeSize() {
        var exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Bounds(1,1,-1,1));
        Assertions.assertEquals("Zero or negative size is illegal (width: -1, height: 1)", exception.getMessage());
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Bounds(1,1,2,-3));
        Assertions.assertEquals("Zero or negative size is illegal (width: 2, height: -3)", exception.getMessage());
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Bounds(1,1,0,1));
        Assertions.assertEquals("Zero or negative size is illegal (width: 0, height: 1)", exception.getMessage());
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Bounds(1,1,2,0));
        Assertions.assertEquals("Zero or negative size is illegal (width: 2, height: 0)", exception.getMessage());
    }
}
