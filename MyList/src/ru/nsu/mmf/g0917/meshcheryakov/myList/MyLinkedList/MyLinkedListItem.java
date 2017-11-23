package ru.nsu.mmf.g0917.meshcheryakov.myList.MyLinkedList;

public class MyLinkedListItem<T> {
    private T data;
    private MyLinkedListItem<T> next;

    public MyLinkedListItem(T data, MyLinkedListItem<T> next) {
        this.data = data;
        this.next = next;
    }

    public T setData(T data) {
        T previousData = this.data;

        this.data = data;
        return previousData;
    }

    public T getData() {
        return data;
    }

    public MyLinkedListItem<T> getNext() {
        return next;
    }

    public void setNext(MyLinkedListItem<T> next) {
        this.next = next;
    }
}
