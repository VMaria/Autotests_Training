package ru.stqa.training.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceBetweenPointsTests {

    @Test
    public void testArea() {

        Point p1 = new Point(6,7);
        Point p2 = new Point(3,3);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }
}
