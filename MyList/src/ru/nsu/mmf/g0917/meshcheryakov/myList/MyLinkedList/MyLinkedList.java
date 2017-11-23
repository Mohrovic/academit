package ru.nsu.mmf.g0917.meshcheryakov.myList.MyLinkedList;

public class MyLinkedList<T> {
    private MyLinkedListItem<T> head;
    private int length;

    public MyLinkedList(T data) {
        this.head = new MyLinkedListItem<>(data, null);
        length = 1;
    }

    public int getSize() {
        return length;
    }

    public MyLinkedListItem<T> getHead() {
        return head;
    }

    public MyLinkedListItem<T> getItem(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        MyLinkedListItem<T> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.getNext();
        }
        return current;
    }

    public T get(int index) {
        return getItem(index).getData();
    }

    public T set(T data, int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        MyLinkedListItem<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        T previousData = current.getData();
        current.setData(data);

        return previousData;
    }

    public T remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }
        length--;

        MyLinkedListItem<T> current = head;

        if (index == 0) {
            head = head.getNext();
            return current.getData();
        }

        for (int i = 1; i < index; i++) {
            current = current.getNext();
        }

        T removedData = current.getNext().getData();
        current.setNext(current.getNext().getNext());


        return removedData;
    }

    public void insert(T data) {
        head = new MyLinkedListItem<>(data, head);
        length++;
    }

    public void remove(T data) {
        MyLinkedListItem<T> current = head;
        for (int i = 0; i < length; i++) {
            if (current.getData().equals(data)) {
                remove(i);
            }
            current = current.getNext();
        }
    }

    public T removeHead() {
        T current = head.getData();

        remove(0);

        return current;
    }

    public void insert(T data, int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        MyLinkedListItem<T> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.getNext();
        }
        MyLinkedListItem<T> next = current.getNext();
        current.setNext(new MyLinkedListItem<>(data, next));
        length++;
    }

    public MyLinkedListItem<T> insertTo(T data, int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        length++;

        MyLinkedListItem<T> current = head;

        if (index == 0) {
            head = new MyLinkedListItem<>(data, head);

            return current;
        }

        for (int i = 1; i < index; i++) {
            current = current.getNext();
        }

        MyLinkedListItem<T> next = current.getNext();

        MyLinkedListItem<T> insertingItem = new MyLinkedListItem<>(data, next);
        current.setNext(insertingItem);

        return next;
    }

    public void invert() {
        if (length == 1) {
            return;
        }

        MyLinkedListItem<T> current = head;

        MyLinkedListItem<T> next = current.getNext();

        for (int i = 1; i < length - 1; i++) {
            MyLinkedListItem<T> previous = current;

            current = next;

            next = current.getNext();

            current.setNext(previous);
        }
        next.setNext(current);
        head = next;
    }

    public MyLinkedList<T> copy() {
        MyLinkedList<T> listCopy = new MyLinkedList<>(head.getData());
        MyLinkedListItem<T> current = head;
        for (int i = 1; i < length; i++) {
            current = current.getNext();
            listCopy.add(current.getData());
        }
        return listCopy;
    }

    public void add(T data) {
        insertTo(data, length);
    }

    public T[] toArray() {
        //noinspection unchecked
        T[] array = (T[]) new Object[length];

        MyLinkedListItem<T> current = head;

        for (int i = 0; i < length; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        return array;
    }
}
