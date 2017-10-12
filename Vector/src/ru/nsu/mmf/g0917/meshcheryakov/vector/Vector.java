package ru.nsu.mmf.g0917.meshcheryakov.vector;

public class Vector {
    private double[] vectorComponents;

    public Vector(int n) {
        if (n <= 0) throw new IllegalArgumentException("Количество компонент вектора должно быть больше нуля");

        this.vectorComponents = new double[n];

        for (int i = 0; i < n; i++) {
            this.vectorComponents[i] = 0;
        }
    }

    public Vector(Vector vector) {
        this.vectorComponents = vector.getComponents();
    }

    public Vector(double[] array) {
        if (array == null) throw new NullPointerException("Задан пустой массив");
        this.vectorComponents = array;
    }

    public Vector(int n, double[] array) {
        if (n <= 0) throw new IllegalArgumentException("Количество компонент вектора должно быть больше нуля");
        vectorComponents = new double[n];
        if (array == null) throw new NullPointerException("Задан пустой массив");
        for (int i = 0; i < array.length; i++) { //замена компонент "вручную" несмотря на warning,
                                 // т.к. если просто присвоить массив array, то может уменьшиться длина vectorComponent
            vectorComponents[i] = array[i];
        }
    }

    public double[] getComponents() {
        return vectorComponents;
    }

    public int getSize() {
        return vectorComponents.length;
    }

    public String toString() {
        StringBuilder sbVectorToStr = new StringBuilder();
        sbVectorToStr.append("{ ");

        for (double vectorComponent : vectorComponents) {
            sbVectorToStr.append(vectorComponent);
            sbVectorToStr.append(", ");
        }
        sbVectorToStr.setLength(sbVectorToStr.length() - 2);
        sbVectorToStr.append(" }");
        return sbVectorToStr.toString();
    }

    public void addVector(Vector vector) {
        int maxSize = (vector.getSize() < this.getSize()) ? vector.getSize() : this.getSize();
        for (int i = 0; i < maxSize; i++) {
            this.vectorComponents[i] += vector.getComponents()[i];
        }
    }

    public void deductVector(Vector vector) {
        int minuendVectorSize = this.vectorComponents.length;
        int subtrahendVectorSize = vector.getSize();
        if (subtrahendVectorSize > minuendVectorSize) throw new IllegalArgumentException(
                "Количество компонент вычитаемого вектора не может превосходить количество компоненет уменьшаемого");

        for (int i = 0; i < subtrahendVectorSize; i++) {
            this.vectorComponents[i] -= vector.getComponents()[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < this.vectorComponents.length; i++) {
            this.vectorComponents[i] *= scalar;
        }
    }

    public void reverse() {
        for (int i = 0; i < vectorComponents.length; i++) {
            vectorComponents[i] *= -1;
        }
    }

    public double getLength() {
        double length = 0;
        int vectorSize = vectorComponents.length;
        for (int i = 0; i < vectorSize; i++) {
            length += Math.pow(vectorComponents[i], 2);
        }
        length = Math.pow(length, 0.5);
        return length;
    }

    public double getComponent(int index) {
        int vectorSize = vectorComponents.length;
        if (index < 0 || index >= vectorSize) throw new IllegalArgumentException(
                "Недопустимый порядковый номер компоненты вектора");
        return vectorComponents[index];
    }

    public void setComponent(int index, double component) {
        int vectorSize = vectorComponents.length;
        if (index < 0 || index >= vectorSize) throw new IllegalArgumentException(
                "Недопустимый порядковый номер компоненты вектора");
        vectorComponents[index] = component;
    }

    public boolean equals(Vector vector) {
        int vectorSize = this.vectorComponents.length;
        if (vector == null) {
            return false;
        }
        if (vector.getSize() != vectorSize) {
            return false;
        }

        for (int i = 0; i < vectorSize; i++) {
            if (this.vectorComponents[i] != vector.getComponents()[i]) {
                return false;
            }

        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result;
        for (double vectorComponent : vectorComponents) {
            result += (int) (vectorComponent);
        }
        return result;
    }

    public static Vector vectorSum(Vector vector1, Vector vector2) {
        int maxSize = (vector1.getSize() > vector2.getSize()) ? vector1.getSize() : vector2.getSize();
        Vector resultVector = new Vector(maxSize);

        resultVector.addVector(vector1);
        resultVector.addVector(vector2);
        return resultVector;
    }

    public static Vector vectorDifference(Vector vector1, Vector vector2) {
        int maxSize = (vector1.getSize() > vector2.getSize()) ? vector1.getSize() : vector2.getSize();
        Vector resultVector = new Vector(maxSize);

        resultVector.addVector(vector1);
        resultVector.deductVector(vector2);
        return resultVector;
    }

    public static double scalarProductOfVectors(Vector vector1, Vector vector2) {
        double result = 0;
        int maxSize = (vector1.getSize() > vector2.getSize()) ? vector1.getSize() : vector2.getSize();
        for (int i = 0; i < maxSize; i++) {
            result += vector1.getComponents()[i] * vector2.getComponents()[i];
        }
        return result;
    }
}




