package ru.nsu.mmf.g0917.meshcheryakov.demoShapes;

import ru.nsu.mmf.g0917.meshcheryakov.shapes.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DemoShapes {

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();

        shapes.add(new Square(4.95));
        shapes.add(new Triangle(3, -17.5, 23.8, 1, 8, 0.15));
        shapes.add(new Triangle(-9, 2, 0.07, 6, -7, 2.8));
        shapes.add(new Rectangle(9, 1));
        shapes.add(new Rectangle(0.7, 19));
        shapes.add(new Rectangle(4, 12.4));
        shapes.add(new Circle(0.08));
        shapes.add(new Circle(4));

        Shape[] shapesArray = new Shape[shapes.size()];
        shapesArray = shapes.toArray(shapesArray);

        Arrays.sort(shapesArray, new SortedByAreaComparator());

        System.out.printf("Фигура с максимальной площадью (%.3f): %s%n",
                shapesArray[0].getArea(), shapesArray[0].toString());

        Arrays.sort(shapesArray, new SortedByPerimeterComparator());

        System.out.printf("Фигура со вторым по величине периметром (%.3f): %s",
                shapesArray[1].getPerimeter(), shapesArray[1].toString());
    }
}