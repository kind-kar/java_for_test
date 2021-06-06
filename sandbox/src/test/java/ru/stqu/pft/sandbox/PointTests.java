package ru.stqu.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point startInMethod = new Point(3, 5);
        Point end = new Point(6, 9);
        Assert.assertEquals(startInMethod.start(end), 5);
    }

    @Test
    public void testDistanceWithNull() {
        Point startInMethod = new Point(0, 0);
        Point end = new Point(0, 0);
        Assert.assertEquals(startInMethod.start(end), 0);
    }

    @Test
    public void testDistanceWithMinus() {
        Point startInMethod = new Point(-7, 0);
        Point end = new Point(3, 5);
        Assert.assertEquals(startInMethod.start(end), 11.180339887498949);
    }

}
