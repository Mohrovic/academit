package ru.nsu.mmf.g0917.meshcheryakov.shapes;

public final class Square implements Shapes {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    public double getArea() {
        return width * width;
    }

    public double getPerimeter() {
        return 4 * width;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return width;
    }

    @Override
    public String toString() {
        return String.valueOf(width);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 17;
        hash = prime * hash + (int) width;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Square square = (Square) obj;
        return width == square.width;
    }
}