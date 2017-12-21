package ru.nsu.mmf.g0917.meshcheryakov.myHashTable.Demo;

import ru.nsu.mmf.g0917.meshcheryakov.myHashTable.MyHashTable.MyHashTable;

import java.util.Arrays;

public class MyHashTableDemo {
    public static void main(String[] args) {
        MyHashTable<String> items1 = new MyHashTable<>();
        items1.add("a");
        System.out.printf("Проверка метода Contains: %s%n", items1.contains("a"));

        items1.add("b");
        items1.add("c");
        items1.add("d");
        items1.add(null);
        items1.add(null);

        System.out.printf("%nПроверка toArray:%n%s%n%n", Arrays.toString(items1.toArray()));

        System.out.println("Проверка Iterator:");
        for (String anItems1 : items1) {
            System.out.printf("%s, ", anItems1);
        }

        String deletingElement = "d";
        System.out.printf("%n%nПроверка remove элемента %s. Удалено: %s%n",
                deletingElement, items1.remove(deletingElement));
        System.out.println(Arrays.toString(items1.toArray()));

        MyHashTable<String> items2 = new MyHashTable<>();
        items2.add("a");
        items2.add("b");
        items2.add("c");
        items2.add(null);
        items2.add("abcde");
        System.out.printf("%nitems1: %s%n", Arrays.toString(items1.toArray()));
        System.out.printf("items2: %s%n", Arrays.toString(items2.toArray()));
        System.out.printf("Проверка containsAll: %s%n%n", items1.containsAll(items2));

        System.out.printf("Проверка addAll. Добавлено: %s%n", items1.addAll(items2));
        System.out.println(Arrays.toString(items1.toArray()));

        items1.add("f");
        System.out.printf("%nitems1: %s%n", Arrays.toString(items1.toArray()));
        System.out.printf("items2: %s%n", Arrays.toString(items2.toArray()));
        System.out.printf("Проверка removeAll. Удалено: %s%n", items1.removeAll(items2));
        System.out.printf("items1: %s%n", Arrays.toString(items1.toArray()));

        items1.add("b");
        items1.add("c");
        items1.add("d");
        items1.add("e");
        items1.add("b");
        System.out.printf("%nitems1: %s%n", Arrays.toString(items1.toArray()));
        System.out.printf("items2: %s%n", Arrays.toString(items2.toArray()));
        System.out.printf("Проверка retainAll. Удалено: %s%n", items1.retainAll(items2));
        System.out.printf("items1: %s%n", Arrays.toString(items1.toArray()));
    }
}
