package ru.nsu.mmf.g0917.meshcheryakov.vector;

import java.util.Arrays;

public class DemoVector {
    public static void main(String[] args) {
        try {
            int vectorSize = 2;
            Vector vector1 = new Vector(vectorSize);
            System.out.printf("Создание вектора размерности %d (вектор No1): %s%n",
                    vectorSize, Arrays.toString(vector1.getComponents()));

            Vector vector2 = new Vector(vector1);
            System.out.printf("Копирование предыдущего вектора (вектор No2): %s%n", Arrays.toString(vector2.getComponents()));

            double[] array1 = {1, -1, 2.5};
            Vector vector3 = new Vector(array1);
            System.out.printf("Создание вектора на основе массива (вектор No3): %s%n", Arrays.toString(vector3.getComponents()));

            double[] array2 = {10, 17.1, 3.5};
            int vector4Size = 5;
            Vector vector4 = new Vector(vector4Size, array2);
            System.out.printf("Создание вектора размерности %d на основе массива (вектор No4): %s%n",
                    vector4Size, Arrays.toString(vector4.getComponents()));

            System.out.printf("Размерность вектораNo4 равна %d%n", vector4.getSize());

            System.out.println(vector4.toString());

            vector4.addVector(vector3);
            System.out.printf("Прибавление вектора No3 к вектору No4: %s%n",
                    Arrays.toString(vector4.getComponents()));

            vector4.deductVector(vector3);
            System.out.printf("Вычитание вектора No3 из вектора No4: %s%n",
                    Arrays.toString(vector4.getComponents()));


            double scalar = 5.9;
            vector4.multiply(scalar);
            System.out.printf("Умножение вектора No4 на скаляр (%.2f): %s%n", scalar,
                    Arrays.toString(vector4.getComponents()));

            vector4.reverse();
            System.out.printf("Разворот вектора No4: %s%n",
                    Arrays.toString(vector4.getComponents()));

            double[] array3 = {1, 1, 1};
            Vector vector5 = new Vector(array3);
            System.out.printf("Длина вектора No5: %.3f%n", vector5.getLength());

            double component = 3.8;
            vector4.setComponent(4, component);
            System.out.printf("Замена компоненты 4(счет от 0) вектора No4 на число %.3f: %s%n", component,
                    Arrays.toString(vector4.getComponents()));
            System.out.printf("Новая компонента 4 вектора No4: %.3f%n", vector4.getComponent(4));

            if (vector1.equals(vector2)) {
                System.out.println("Вектор No1 и вектор No2 равны");
            } else {
                System.out.println("Вектор No1 и вектор No2 не равны");
            }

            Vector vector6 = Vector.vectorSum(vector2, vector3);
            System.out.printf("Сложение векторов No2 и No3 статическим методом: %s%n",
                    Arrays.toString(vector6.getComponents()));

            Vector vector7 = Vector.vectorDifference(vector2, vector3);
            System.out.printf("Разность от вычитания вектора No3 из вектора No2 статическим методом: %s%n",
                    Arrays.toString(vector7.getComponents()));

            double scalarProduct = Vector.scalarProductOfVectors(vector6,vector7);
            System.out.printf("Скалярное произведение векторов No6 и No7: %.3f%n", scalarProduct);

        } catch (IllegalArgumentException e) {
            System.out.println("Задана некорректная размерность вектора");
            throw e;
        } catch (NullPointerException e) {
            System.out.println("Задан пустой вектор");
            throw e;

        }
    }
}
