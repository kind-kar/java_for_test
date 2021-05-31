package ru.stqu.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello();

        double len = 5;
        System.out.println("Квадрат " + len + " равен " + area(len));
        System.out.println(area(3, 6));
    }
    public static void hello() {
        System.out.println("Hello, world");
    }
    public static double area(double l) {
        return l * l;
    }

    public static double area(double a, double b) {
        return a * b;
    }
}