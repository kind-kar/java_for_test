package ru.stqu.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello();

        Square s = new Square(5.0);
        System.out.println("Квадрат " + s.l + " равен " + area(s));

        Rectangle r = new Rectangle(3.0, 4.0);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));
    }
    public static void hello() {
        System.out.println("Hello, world");
    }
    public static double area(Square s) {
        return s.l * s.l;
    }

    public static double area(Rectangle r) {
        return r.a * r.b;
    }
}