package ru.stqa.training.sandbox;

public class DistanceBetweenPoints {
    public static void main (String[] args) {

        Point p1 = new Point(6,7);
        Point p2 = new Point(3,3);

        System.out.println(distance(p1,p2));
    }
    public static double distance(Point p1,Point p2) {
        double x = p1.x-p2.x;
        double y = p1.y-p2.y;
        return Math.sqrt(x*x + y*y);
    }
}
