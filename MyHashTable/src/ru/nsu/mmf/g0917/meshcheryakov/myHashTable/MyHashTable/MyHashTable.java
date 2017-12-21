package ru.nsu.mmf.g0917.meshcheryakov.myHashTable.MyHashTable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private ArrayList<T>[] table;
    private int size = 0;
    private int modificationCount = 0;

    public MyHashTable() {
        int length = 100;
        //noinspection unchecked
        table = (ArrayList<T>[]) new ArrayList[length];
    }

    public MyHashTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Размер массива не может быть меньше 1");
        }

        //noinspection unchecked
        table = (ArrayList<T>[]) new ArrayList[length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        int oHash = 0;

        if (o != null) {
            oHash = o.hashCode();
        }

        int index = Math.abs(oHash % table.length);

        return table[index] != null && table[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        //noinspection Convert2Diamond
        return new Iterator<T>() {
            private int index = 0;
            private int position = -1;
            private final int CURRENT_MODIFICATION_COUNT = modificationCount;

            @Override
            public boolean hasNext() {
                int oldIndex = index;
                int oldPosition = position;
                while (index < table.length) {
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
                if (modificationCount > CURRENT_MODIFICATION_COUNT) {
                    throw new ConcurrentModificationException(
                            "Во время работы итератора произошло изменение коллекции");
                }

                while (index < table.length) {
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
        T[] array = (T[]) new Object[size];

        int i = 0;
        for (T element : this) {
            array[i] = element;
            i++;
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("Массив пуст");
        }

        int length = table.length;
        if (a.length < length) {
            a = Arrays.copyOf(a, length);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, length);
        return a;
    }

    @Override
    public boolean add(T t) {
        int tHash = 0;

        if (t != null) {
            tHash = t.hashCode();
        }

        int index = Math.abs(tHash % table.length);

        if (table[index] == null) {
            table[index] = new ArrayList<>();
        }

        table[index].add(t);
        size++;
        modificationCount++;

        return true;
    }


    @Override
    public boolean remove(Object o) {
        int oHash = 0;

        if (o != null) {
            oHash = o.hashCode();
        }

        int index = Math.abs(oHash % table.length);
        boolean removed = false;

        if (table[index] != null && table[index].remove(o)) {
            size--;
            modificationCount++;
            removed = true;
        }

        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object cElement : c) {
            int cElementHash = 0;

            if (cElement != null) {
                cElementHash = cElement.hashCode();
            }

            int index = Math.abs(cElementHash % table.length);

            //noinspection SuspiciousMethodCalls
            if (table[index] == null || !table[index].contains(cElement)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;

        for (T element : c) {
            if (add(element)) {
                modificationCount++;
                changed = true;
            }
        }

        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;

        for (Object e : c) {
            int eHash = 0;

            if (e != null) {
                eHash = e.hashCode();
            }

            int index = Math.abs(eHash % table.length);

            //noinspection SuspiciousMethodCalls
            while (table[index].remove(e)) {
                size--;
                modificationCount++;
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean removed = false;

        for (ArrayList<T> list : table) {
            if (list != null) {
                int oldSize = list.size();

                if (list.retainAll(c)) {
                    size -= oldSize - list.size();
                    modificationCount++;
                    removed = true;
                }
            }
        }
        return removed;
    }

    @Override
    public void clear() {
        for (ArrayList<T> list : table) {
            if (list != null) {
                list.clear();
                modificationCount++;
            }
        }
    }
}