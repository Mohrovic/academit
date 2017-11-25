package ru.nsu.mmf.g0917.meshcheryakov.myList.MyLinkedList;

public class MyLinkedList<T> {
    private MyLinkedListItem<T> head;
    private int length;

    public MyLinkedList() {
        length = 0;
    }

    public MyLinkedList(T data) {
        head = new MyLinkedListItem<>(data, null);
        length = 1;
    }

    public T set(T data, int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        MyLinkedListItem<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.setData(data);
    }

    public void setLength(int length) {
        this.length = length;
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

        current = getItem(index - 1);

        T removedData = current.getNext().getData();
        current.setNext(current.getNext().getNext());

        return removedData;
    }

    public void insert(T data) {
        head = new MyLinkedListItem<>(data, head);
        length++;
    }

    public boolean remove(T data) {
        MyLinkedListItem<T> current = head;
        boolean deleted = false;

        for (int i = 0; i < length; i++) {
            if (data == current.getData() || current.getData().equals(data)) {
                remove(i);
                deleted = true;
                break;
            }
            current = current.getNext();
        }
        return deleted;
    }

    public T removeHead() {
         return remove(0);
    }

    public void insertAfter(T data, int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        MyLinkedListItem<T> current = getItem(index);

        MyLinkedListItem<T> next = current.getNext();

        current.setNext(new MyLinkedListItem<>(data, next));

        length++;
    }

    public MyLinkedListItem<T> insert(T data, int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        length++;

        MyLinkedListItem<T> current = head;

        if (index == 0) {
            head = new MyLinkedListItem<>(data, head);

            return current;
        }

        current = getItem(index - 1);

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
        MyLinkedListItem<T> currentCopy = listCopy.getHead();

        MyLinkedListItem<T> current = head;

        for (int i = 1; i < length; i++) {
            currentCopy.setData(current.getData());

            current = current.getNext();

            currentCopy.setNext(current);
            currentCopy = currentCopy.getNext();
        }

        listCopy.setLength(length);
        return listCopy;
    }

    public void add(T data) {
        insert(data, length);
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
