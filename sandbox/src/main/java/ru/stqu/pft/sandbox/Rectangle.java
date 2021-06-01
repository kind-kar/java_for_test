package ru.stqu.pft.sandbox;

public class Rectangle {
    public double a;
    public  double b;

    public Rectangle(Double a, Double b) {
        this.a = a;
        this.b = b;
    }
    public double area() {
        return this.a * this.b;
    }
}
