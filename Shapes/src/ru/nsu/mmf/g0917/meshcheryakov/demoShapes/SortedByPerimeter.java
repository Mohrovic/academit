package ru.nsu.mmf.g0917.meshcheryakov.demoShapes;

import ru.nsu.mmf.g0917.meshcheryakov.shapes.Shape;

import java.util.Comparator;

class SortedByPerimeter implements Comparator<Shape> {

    @Override
    public int compare(Shape shapeA, Shape shapeB) {
        Double shapeAPerimeter = shapeA.getPerimeter();
        return -1 * shapeAPerimeter.compareTo(shapeB.getPerimeter());
    }
}