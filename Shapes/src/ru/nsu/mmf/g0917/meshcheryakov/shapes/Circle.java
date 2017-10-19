package ru.nsu.mmf.g0917.meshcheryakov.shapes;

public final class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getWidth() {
        return 2 * radius;
    }

    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public String toString() {
        return "[ " + String.valueOf(radius) + " ]";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 17;
        hash = prime * hash + (int) radius;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Circle circle = (Circle) obj;
        return radius == circle.radius;
    }
}
