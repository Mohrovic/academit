package ru.nsu.mmf.g0917.meshcheryakov.myHashTable.MyHashTable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashTable<T> implements Collection<T> {
    private int[] key;
    private T[] value;
    private int length;

    public MyHashTable() {
        length = 0;
    }

    public MyHashTable(int tableSize) {
        key = new int[tableSize];
        //noinspection unchecked
        value = (T[]) new Object[tableSize];
        length = 0;
    }

    public MyHashTable(T value) {
        length = 1;
        this.key = new int[2];
        //noinspection unchecked
        this.value = (T[]) new Object[2];

        this.key[0] = value.hashCode();
        this.value[0] = value;
    }

    private void increaseCapacity() {
        key = Arrays.copyOf(key, key.length * 2);
        value = Arrays.copyOf(value, value.length * 2);
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return (length == 0);
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < length; i++) {
            if (o.hashCode() == key[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {

        //noinspection Convert2Diamond
        return new Iterator<T>() {
            private int position = -1;

            @Override
            public boolean hasNext() {
                return position < length - 1;
            }

            @Override
            public T next() {
                position++;
                if (position > length - 1) {
                    throw new NoSuchElementException("Достигнут конец коллекции");
                }

                return value[position];
            }
        };

    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(value, length);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < length) {
            a = Arrays.copyOf(a, length);
        }

        System.arraycopy(value, 0, a, 0, length);
        return a;
    }

    @Override
    public boolean add(T t) {
        if (!contains(t)) {
            if (length >= value.length) {
                increaseCapacity();
            }
            key[length] = t.hashCode();
            value[length] = t;
            length++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        int oHashCode = o.hashCode();

        for (int i = 0; i < length; i++) {
            if (oHashCode == key[i]) {
                if (i < length - 1) {
                    System.arraycopy(key, i + 1, key, i, length - 1);
                    System.arraycopy(value, i + 1, value, i, length - 1);
                }
                length--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        for (Object cElement : c) {
            int cHashCode = cElement.hashCode();
            boolean contains = false;

            for (int i = 0; i < length; i++) {
                if (key[i] == (cHashCode)) {
                    contains = true;
                    break;
                }
            }

            if (!contains) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Iterator iterator = c.iterator();

        boolean changed = false;
        while (iterator.hasNext()) {
            //noinspection unchecked
            changed = add((T) iterator.next());
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;

        for (int i = 0; i < length; i++) {
            if (c.contains(value[i])) {
                if (i < length - 1) {
                    System.arraycopy(key, i + 1, key, i, length - 1);
                    System.arraycopy(value, i + 1, value, i, length - 1);
                }
                length--;
                i--;

                removed = true;
            }
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean removed = false;

        for (int i = 0; i < length; i++) {
            if (!c.contains(value[i])) {

                System.arraycopy(key, i + 1, key, i, length - 1);
                System.arraycopy(value, i + 1, value, i, length - 1);

                length--;
                i--;

                removed = true;
            }
        }
        return removed;
    }

    @Override
    public void clear() {
        length = 0;
    }
}
