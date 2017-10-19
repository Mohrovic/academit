package ru.nsu.mmf.g0917.meshcheryakov.shapes;

public final class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    private double lengthOfSide(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getArea() {
        return Math.abs(0.5 * ((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3)));
    }

    public double getPerimeter() {
        double ab = lengthOfSide(x1, y1, x2, y2);
        double ac = lengthOfSide(x1, y1, x3, y3);
        double bc = lengthOfSide(x2, y2, x3, y3);

        return ab + ac + bc;
    }

    private double getMaxCoordinate(double coordinate1, double coordinate2, double coordinate3) {
        double maxCoordinate = (coordinate1 > coordinate2) ? coordinate1 : coordinate2;
        maxCoordinate = (maxCoordinate > coordinate3) ? maxCoordinate : coordinate3;
        return maxCoordinate;
    }

    private double getMinCoordinate(double coordinate1, double coordinate2, double coordinate3) {
        double minCoordinate = (coordinate1 < coordinate2) ? coordinate1 : coordinate2;
        minCoordinate = (minCoordinate < coordinate3) ? minCoordinate : coordinate3;
        return minCoordinate;
    }

    public double getWidth() {
        return getMaxCoordinate(x1, x2, x3) - getMinCoordinate(x1, x2, x3);
    }

    public double getHeight() {
        return getMaxCoordinate(y1, y2, y3) - getMinCoordinate(y1, y2, y3);
    }

    @Override
    public String toString() {
        StringBuilder sbString = new StringBuilder();

        sbString.append("[ ");
        sbString.append(Double.toString(x1));
        sbString.append(", ");
        sbString.append(Double.toString(y1));
        sbString.append(", ");
        sbString.append(Double.toString(x2));
        sbString.append(", ");
        sbString.append(Double.toString(y2));
        sbString.append(", ");
        sbString.append(Double.toString(x3));
        sbString.append(", ");
        sbString.append(Double.toString(y3));
        sbString.append(" ]");

        return sbString.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 17;
        hash = prime * hash + (int) x1;
        hash = prime * hash + (int) x2;
        hash = prime * hash + (int) x3;
        hash = prime * hash + (int) y1;
        hash = prime * hash + (int) y2;
        hash = prime * hash + (int) y3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Triangle triangle = (Triangle) obj;
        return x1 == triangle.x1 && x2 == triangle.x2 && x3 == triangle.x3 &&
                y1 == triangle.y1 && y2 == triangle.y2 && y3 == triangle.y3;
    }
}