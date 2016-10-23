package ru.stqa.training.sandbox;

public class DistanceBetweenPoints {
    public static void main(String[] args) {
        Point p1 = new Point(6,7);
        Point p2 = new Point(3,3);
        System.out.println("Растстояние между точками равно "+p1.distance(p2));
    }
}