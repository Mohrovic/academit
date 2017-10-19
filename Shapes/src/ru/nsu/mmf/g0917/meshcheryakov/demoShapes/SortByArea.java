package ru.nsu.mmf.g0917.meshcheryakov.demoShapes;

import ru.nsu.mmf.g0917.meshcheryakov.shapes.Shape;

import java.util.Comparator;

class SortByArea implements Comparator<Shape> {

    @Override
    public int compare(Shape sapeA, Shape shapeB) {
        Double shapeAArea = sapeA.getArea();
        return -1 * shapeAArea.compareTo(shapeB.getArea());
    }
}