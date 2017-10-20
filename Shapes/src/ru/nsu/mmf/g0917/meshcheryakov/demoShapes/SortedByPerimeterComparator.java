package ru.nsu.mmf.g0917.meshcheryakov.demoShapes;

import ru.nsu.mmf.g0917.meshcheryakov.shapes.Shape;

import java.util.Comparator;

class SortedByPerimeterComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape shapeA, Shape shapeB) {
        return -Double.compare(shapeA.getPerimeter(), shapeB.getPerimeter());
    }
}