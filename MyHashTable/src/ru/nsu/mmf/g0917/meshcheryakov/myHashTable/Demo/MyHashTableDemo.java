package ru.nsu.mmf.g0917.meshcheryakov.myHashTable.Demo;

import ru.nsu.mmf.g0917.meshcheryakov.myHashTable.MyHashTable.MyHashTable;

import java.util.Arrays;

public class MyHashTableDemo {
    public static void main(String[] args) {
        MyHashTable<String> items1 = new MyHashTable<>("a");
        System.out.println(Arrays.toString(items1.toArray()));

        System.out.printf("Проверка метода Contains: %s%n", items1.contains("a"));

        items1.add("b");
        items1.add("c");
        items1.add("d");
        items1.add("e");
        items1.add("b");

        System.out.printf("%nПроверка toArray:%n%s%n", Arrays.toString(items1.toArray()));

        System.out.println("Проверка Iterator:");
        for (Object anItems1 : items1) {
            System.out.printf("%s ", anItems1);
        }

        String[] a = new String[2];
        System.out.printf("%nПроверка toArray с параметром:%n%s%n", Arrays.toString(items1.toArray(a)));

        String deletingElement = "e";
        System.out.printf("%nПроверка remove элемента %s. Удалено: %s%n",
                deletingElement, items1.remove(deletingElement));
        System.out.println(Arrays.toString(items1.toArray()));

        MyHashTable<String> items2 = new MyHashTable<>("a");
        items2.add("b");
        items2.add("c");
        items2.add("d");
        items2.add("abcde");
        System.out.printf("%nПроверка containsAll: %s%n", items1.containsAll(items2));

        System.out.printf("%nПроверка addAll. Добавлено: %s%n", items1.addAll(items2));
        System.out.println(Arrays.toString(items1.toArray()));

        items1.add("f");
        System.out.printf("%nitems1: %s%n", Arrays.toString(items1.toArray()));
        System.out.printf("items2: %s%n", Arrays.toString(items2.toArray()));
        System.out.printf("Проверка removeAll. Удалено: %s%n", items1.removeAll(items2));
        System.out.println(Arrays.toString(items1.toArray()));

        items1.add("b");
        items1.add("c");
        items1.add("d");
        items1.add("e");
        items1.add("b");
        System.out.printf("%nitems1: %s%n", Arrays.toString(items1.toArray()));
        System.out.printf("items2: %s%n", Arrays.toString(items2.toArray()));
        System.out.printf("Проверка retainAll. Удалено: %s%n", items1.retainAll(items2));
        System.out.println(Arrays.toString(items1.toArray()));
    }
}
