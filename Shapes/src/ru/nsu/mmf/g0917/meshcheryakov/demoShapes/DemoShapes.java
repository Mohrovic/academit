package ru.nsu.mmf.g0917.meshcheryakov.demoShapes;

import ru.nsu.mmf.g0917.meshcheryakov.shapes.*;

import java.util.ArrayList;

public class DemoShapes {

    public static int getIndexOfMaxArea(ArrayList<Shapes> shapes) {
        double maxArea = 0;
        int maxIndex = -1;
        for (int i = 0; i < shapes.size(); i++) {
            double area = shapes.get(i).getArea();
            if (maxArea < area) {
                maxArea = area;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static int getIndexOf2ndPerimeter(ArrayList<Shapes> shapes) {
        double maxPerimeter = 0;
        int maxIndex = -1;
        int secondIndex = -1;
        for (int i = 0; i < shapes.size(); i++) {
            double perimeter = shapes.get(i).getPerimeter();
            if (maxPerimeter < perimeter) {
                maxPerimeter = perimeter;
                secondIndex = maxIndex;
                maxIndex = i;
            }
        }
        return secondIndex;
    }

    public static void main(String[] args) {
        ArrayList<Shapes> shapes = new ArrayList<>();

        shapes.add(new Square(4.95));
        shapes.add(new Triangle(3, -17.5, 23.8, 1, 8, 0.15));
        shapes.add(new Triangle(-9, 2, 0.07, 6, -7, 2.8));
        shapes.add(new Rectangle(9, 1));
        shapes.add(new Rectangle(0.7, 19));
        shapes.add(new Rectangle(4, 12.4));
        shapes.add(new Circle(0.08));
        shapes.add(new Circle(19));

        int indexOfMaxArea = getIndexOfMaxArea(shapes);
        System.out.printf("Фигура с максимальной площаью %.1f находится под индексом %d%n",
                shapes.get(indexOfMaxArea).getArea(), indexOfMaxArea);

        int indexOf2ndPerimeter = getIndexOf2ndPerimeter(shapes);
        System.out.printf("Фигура с периметром %.1f (второй по величине) находится под индексом %d%n",
                shapes.get(indexOf2ndPerimeter).getPerimeter(), indexOf2ndPerimeter);
    }
}