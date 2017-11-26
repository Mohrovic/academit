package ru.nsu.mmf.g0917.meshcheryakov.myList.MyLinkedListDemo;

import ru.nsu.mmf.g0917.meshcheryakov.myList.MyLinkedList.MyLinkedList;

import java.util.Arrays;

public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList<String> myList = new MyLinkedList<>("a");

        System.out.println("Проверка add:");
        myList.add("b");
        myList.add("c");
        myList.add("d");
        System.out.println(Arrays.toString(myList.toArray()));

        int index = 4;
        String e = "e";
        System.out.printf("%nПроверка insert \"%s\" на место с индексом %d:%n", e, index);
        myList.insert(e, index);
        System.out.println(Arrays.toString(myList.toArray()));

        System.out.printf("%nДлина списка составляет: %s элемнтов", myList.getSize());

        index = 0;
        System.out.printf("%n%nПроверка getItem по индексу %d: %s", index, myList.getItem(index).getData());
        System.out.printf("%n%nПроверка get по индексу %d: %s", index, myList.get(index));

        index = 4;
        System.out.printf("%n%nПроверка set по индексу %d. Старое значение: %s%n", index, myList.set(index, "f"));
        System.out.println(Arrays.toString(myList.toArray()));

        index = 4;
        System.out.printf("%nПроверка remove по индексу %d. Удаленное значение: %s%n", index, myList.remove(index));
        System.out.println(Arrays.toString(myList.toArray()));

        String g = "g";
        System.out.printf("%nПроверка insert элемента \"%s\" в начало списка.%n", g);
        myList.insert(g);
        System.out.println(Arrays.toString(myList.toArray()));

        String find = "d";
        myList.set(3, null);
        System.out.printf("%nПроверка remove первого встретившегося элемента, " +
                "сответствующего данным \"%s\":%n", find);
        System.out.println(Arrays.toString(myList.toArray()));
        System.out.printf("Удалено: %s%n", myList.remove(find));
        System.out.println(Arrays.toString(myList.toArray()));

        System.out.printf("%nПроверка remove первого элемента (%s):%n", myList.removeHead());
        System.out.println(Arrays.toString(myList.toArray()));

        System.out.println("\nПроверка invert:");
        MyLinkedList<String> myList2 = new MyLinkedList<>("a");
        myList2.add("b");
        myList2.add("c");
        myList2.add("d");
        myList2.add("e");
        myList2.add("f");
        System.out.print(Arrays.toString(myList2.toArray()));
        System.out.println();
        myList2.invert();
        System.out.println(Arrays.toString(myList2.toArray()));

        System.out.println("\nПроверка copy:");
        System.out.println(Arrays.toString(myList2.copy().toArray()));

        System.out.println("\nПроверка copy пустого списка:");
        MyLinkedList<String> emptyList = new MyLinkedList<>();
        System.out.println(Arrays.toString(emptyList.toArray()));
        System.out.println(Arrays.toString(emptyList.copy().toArray()));
    }
}
