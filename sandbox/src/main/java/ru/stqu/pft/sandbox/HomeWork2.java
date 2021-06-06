package ru.stqu.pft.sandbox;

public class HomeWork2 {

    public static void main(String[] args) {

        Point start = new Point(1, 5);
        Point end = new Point(4, 1);
        System.out.println(distance(start, end));

        Point startInMethod = new Point(1, 5);
        Point endInMethod = new Point(4, 1);
        System.out.println(startInMethod.start(endInMethod));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.p1 - p2.p1)*(p1.p1 - p2.p1) + (p1.p2 - p2.p2)*(p1.p2 - p2.p2));
    }

}
