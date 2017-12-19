package ru.nsu.mmf.g0917.meshcheryakov.myHashTable.MyHashTable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private ArrayList<T>[] table;
    private int length = 100;

    public MyHashTable() {
        //noinspection unchecked
        table = (ArrayList<T>[]) new ArrayList[length];
    }

    public MyHashTable(T item) {
        //noinspection unchecked
        table = (ArrayList<T>[]) new ArrayList[length];
        int itemHashCode = Math.abs(item.hashCode() % length);
        table[itemHashCode] = new ArrayList<>();
        table[itemHashCode].add(item);
    }

    @Override
    public int size() {
        int size = 0;

        for (ArrayList<T> e : table) {
            size += e.size();
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public boolean contains(Object o) {
        int index = Math.abs(o.hashCode() % length);

        return table[index] != null && table[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        //noinspection Convert2Diamond
        return new Iterator<T>() {
            private int index = 0;
            private int position = -1;

            @Override
            public boolean hasNext() {
                int oldIndex = index;
                int oldPosition = position;
                while (index < length) {
                    if (table[index] == null) {
                        index++;
                        continue;
                    }

                    if (position < table[index].size() - 1) {
                        index = oldIndex;
                        position = oldPosition;
                        return true;
                    }
                    index++;
                    position = -1;
                }
                index = oldIndex;
                position = oldPosition;
                return false;
            }

            @Override
            public T next() {
                while (index < length) {
                    if (table[index] == null) {
                        index++;
                        continue;
                    }

                    if (position < table[index].size() - 1) {
                        position++;
                        return table[index].get(position);
                    }
                    index++;
                    position = -1;
                }

                throw new NoSuchElementException("Достигнут конец коллекции");
            }
        };

    }

    @Override
    public Object[] toArray() {
        //noinspection unchecked
        T[] array = (T[]) new Object[length];
        int arrayLength = 0;
        for (ArrayList<T> e : table) {
            if (e != null) {

                //noinspection unchecked
                T[] eArray = (T[]) e.toArray();
                int eArrayLength = eArray.length;

                System.arraycopy(eArray, 0, array, arrayLength, eArrayLength);

                arrayLength += eArrayLength;
            }
        }
        return Arrays.copyOf(array, arrayLength);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Массив пуст");
        }

        if (a.length < length) {
            a = Arrays.copyOf(a, length);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, length);
        return a;
    }

    @Override
    public boolean add(T t) {
        int index = Math.abs(t.hashCode() % length);

        if (table[index] == null) {
            table[index] = new ArrayList<>();
            table[index].add(t);
            return true;
        }

        if (!table[index].contains(t)) {
            table[index].add(t);
            return true;
        }
        return false;
    }


    @Override
    public boolean remove(Object o) {
        int index = Math.abs(o.hashCode() % length);

        return table[index] != null && table[index].remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object cElement : c) {
            int index = Math.abs(cElement.hashCode() % length);

            //noinspection SuspiciousMethodCalls
            if (table[index] == null || !table[index].contains(cElement)) {
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

        for (Object e : c) {
            int index = Math.abs(e.hashCode() % length);

            //noinspection SuspiciousMethodCalls
            if (table[index].remove(e)) {
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean removed = false;

        for (int i = 0; i < length; i++) {
            if (table[i] != null && table[i].retainAll(c)) {
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            table[i] = null;
        }
    }
}