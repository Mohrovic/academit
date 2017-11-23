package ru.nsu.mmf.g0917.meshcheryakov.myList.MyLinkedListDemo;

import ru.nsu.mmf.g0917.meshcheryakov.myList.MyLinkedList.MyLinkedList;
import ru.nsu.mmf.g0917.meshcheryakov.myList.MyLinkedList.MyLinkedListItem;

import java.util.Arrays;

public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList<String> myList = new MyLinkedList<>("a");

        System.out.println("Проверка add:");
        myList.add("b");
        myList.add("c");
        myList.add("d");
        System.out.println(Arrays.toString(myList.toArray()));

        myList.insertTo("e", 0);

        System.out.printf("%nДлина списка составляет: %s элемнтов", myList.getSize());

        int index = 0;
        System.out.printf("%n%nПроверка getItem по индексу %d: %s", index, myList.getItem(index).getData());
        System.out.printf("%n%nПроверка get по индексу %d: %s", index, myList.get(index));

        index = 4;
        System.out.printf("%n%nПроверка set по индексу %d. Старое значение: %s%n", index, myList.set("f", index));
        System.out.println(Arrays.toString(myList.toArray()));

        index = 3;
        System.out.printf("%nПроверка remove по индексу %d. Удаленное значение: %s%n", index, myList.remove(index));
        System.out.println(Arrays.toString(myList.toArray()));

        String g = "g";
        System.out.printf("%nПроверка insert элемента \"%s\" в начало списка.%n", g);
        myList.insert(g);
        System.out.println(Arrays.toString(myList.toArray()));

        String e = "e";
        System.out.printf("%nПроверка remove первого встретившегося элемента, сответствующего данным \"%s\":%n", e);
        myList.remove(e);
        System.out.println(Arrays.toString(myList.toArray()));

        System.out.printf("%nПроверка remove первого элемента (%s):", myList.removeHead());
        System.out.println(Arrays.toString(myList.toArray()));

        index = 1;
        System.out.printf("%nПроверка insert после элемента с индексом %d:%n", index);
        myList.insert("h", index);
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
    }
}
