package ru.stqu.pft.sandbox;

public class Square {
    public double l;

    public Square(double len) { // конструктор
        this.l = len;
    }
    public double area() {
        return this.l * this.l;
    }
}
