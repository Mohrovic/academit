package ru.nsu.mmf.g0917.meshcheryakov.myArrayList;

import java.util.Arrays;

public class DemoArray {
    public static void main(String[] args) {

        MyArrayList<String> names = new MyArrayList<>();

        names.add("a-b-c");
        names.add("1,2,3");
        System.out.println("Заполнили исходный массив:");
        System.out.println(Arrays.toString(names.toArray()));

        MyArrayList<String> names2 = new MyArrayList<>();
        names2.add("d-e-f");
        names2.add("4,5,6");
        names.addAll(names2);
        System.out.println("присоединили names2");
        System.out.println(Arrays.toString(names.toArray()));
        System.out.printf("Размер созданного списка = %d%n", names.size());

        names.remove(1);
        System.out.println("удалили элемент номер1");
        System.out.println(Arrays.toString(names.toArray()));
        System.out.printf("Размер созданного списка = %d", names.size());

        String insertingItem = "g-h-i";
        names.add(2, insertingItem);
        System.out.println("добавили String элемент");
        System.out.println(Arrays.toString(names.toArray()));

        System.out.printf("Содержит \"%s\": ", insertingItem);
        System.out.println(names.contains(insertingItem));

        MyArrayList<String> names3 = new MyArrayList<>();
        names3.addAll(names);

        System.out.printf("Список %s содержит все элементы %s: %s%n",
                Arrays.toString(names.toArray()), Arrays.toString(names3.toArray()), names.containsAll(names3));

        names3.remove(1);
        System.out.printf("Список %s содержит все элементы %s: %s%n",
                Arrays.toString(names.toArray()), Arrays.toString(names3.toArray()), names.containsAll(names3));

        names3.add("new string");
        System.out.printf("Список %s содержит все элементы %s: %s%n",
                Arrays.toString(names.toArray()), Arrays.toString(names3.toArray()), names.containsAll(names3));

        System.out.println("Метод addAll по индексу:");
        System.out.println(Arrays.toString(names.toArray()));
        System.out.println(Arrays.toString(names3.toArray()));
        names.addAll(3, names3);
        System.out.println(Arrays.toString(names.toArray()));

        String arr[] = new String[3];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "@" + i;
        }
        System.out.println(Arrays.toString(arr));

        String arr2[] = new String[3];
        arr2 = Arrays.copyOf(arr, 2);
        System.out.println(Arrays.toString(arr2));

        System.out.println(Arrays.toString(names.toArray()));
        String ghi = "g-h-i";
        System.out.printf("Элемент %s находится под индексом %d%n", ghi, names.lastIndexOf(ghi));

        //проверка containsAll
        names3.add("новый элемент");
        System.out.println(Arrays.toString(names.toArray()));
        System.out.println(Arrays.toString(names3.toArray()));
        System.out.println(names.containsAll(names3));
    }
}