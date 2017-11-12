package ru.nsu.mmf.g0917.meshcheryakov.myArrayList;

import java.util.*;

final class MyArrayList<T> implements List<T> {
    private T[] items;
    private int length;
    private Class<T> type;

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[2];
    }

    public MyArrayList(T[] items) {
        //noinspection unchecked
        items = (T[]) new Object[2];
        this.items = items;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    private void increaseCapacity(int additionalLength) {
        items = Arrays.copyOf(items, items.length + additionalLength);
    }

    @Override
    public boolean add(T item) {
        if (length >= items.length) {
            increaseCapacity();
        }

        items[length] = item;
        ++length;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        if (length >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = element;
        ++length;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Передаваемая коллекция пуста");
        }

        //noinspection unchecked
        T[] addingItems = (T[]) c.toArray();
        int addingItemsLength = addingItems.length;

        if (c.isEmpty()) {
            return false;
        }

        if (length + addingItemsLength >= this.items.length) {
            increaseCapacity(addingItemsLength);
        }

        System.arraycopy(addingItems, 0, this.items, length, addingItemsLength);
        length += addingItemsLength;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Передаваемая коллекция пуста");
        }

        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        //noinspection unchecked
        T[] addingItems = (T[]) c.toArray();
        int addingItemsLength = addingItems.length;

        if (c.isEmpty()) {
            return false;
        }

        if (length + addingItemsLength > this.items.length) {
            increaseCapacity(addingItemsLength);
        }

        T[] old = Arrays.copyOf(this.items, length);

        System.arraycopy(addingItems, 0, this.items, index, addingItemsLength);
        System.arraycopy(old, index, this.items, index + addingItemsLength, length - index);

        length += addingItemsLength;
        return true;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public boolean contains(Object obj) {
        return this.indexOf(obj) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> elements) {
        Iterator iterator = elements.iterator();

        while (iterator.hasNext()) {
            Object element = iterator.next();
            boolean contains = false;

            for (int i = 0; i < this.length; i++) {
                if (items[i].equals(element)) {
                    contains = true;
                }
            }

            if (!contains) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyArrayList) {
            //noinspection MismatchedReadAndWriteOfArray
            T[] myArray = Arrays.copyOf(this.items, length);

            //noinspection ArrayEquals
            return (myArray.equals(((MyArrayList) obj).toArray()));
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }
        return items[index];
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 17;
        hash = prime * hash * length + Arrays.hashCode(items);
        return hash;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public Iterator<T> iterator() {
        //noinspection Convert2Diamond
        return new Iterator<T>() {
            private int index = -1;

            @Override
            public boolean hasNext() {
                return index < length - 1;
            }

            @Override
            public T next() {
                if (index >= length) {
                    throw new NoSuchElementException("Конец списка");
                }

                ++index;
                return items[index];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Удаление через итератор не поддерживается");
            }
        };
    }


    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(-1);
    }

    public ListIterator<T> listIterator(int index) {
        if (index < -1 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        //noinspection Convert2Diamond
        return new ListIterator<T>() {
            private int currentIndex = index;

            @Override
            public boolean hasNext() {
                return currentIndex < length - 1;
            }

            @Override
            public T next() {
                if (currentIndex < length - 1) {
                    ++currentIndex;
                    return items[currentIndex];
                }
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public T previous() {
                if (currentIndex > 0) {
                    --currentIndex;
                    return items[currentIndex];
                }
                return null;
            }

            @Override
            public int nextIndex() {
                if (currentIndex >= length) {
                    return length;
                }
                return currentIndex + 1;
            }

            @Override
            public int previousIndex() {
                if (currentIndex <= -1) {
                    return -1;
                }
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }


            @Override
            public void set(T t) {
                {
                    throw new UnsupportedOperationException("set");
                }
            }

            @Override
            public void add(T t) {
                {
                    throw new UnsupportedOperationException("add");
                }
            }
        };
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        T removingItem = items[index];
        System.arraycopy(items, index + 1, items, index, length - index - 1);

        --length;

        return removingItem;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        boolean removed = false;

        for (int i = 0; i < this.length; i++) {
            if (c.contains(items[i])) {
                this.remove(i);
                i--;
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null || c.getClass() != type) {
            return false;
        }

        //noinspection unchecked
        T[] retainingArray = (T[]) c.toArray();

        boolean changed = false;
        boolean contains = false;

        for (int i = 0; i < length; i++) {
            for (T e : retainingArray) {
                if (items[i].equals(e)) {
                    contains = true;
                }
            }

            if (!contains) {
                System.arraycopy(this.items, i + 1, this.items, i, length - i - 1);
                length--;
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public T set(int index, T item) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        T previousItem = items[index];
        items[index] = item;
        return previousItem;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Object[] toArray() {
        //noinspection unchecked
        T[] array = (T[]) new Object[length];
        System.arraycopy(items, 0, array, 0, length);
        return array;
    }

    @Override
    public <S> S[] toArray(S[] a) {
        //noinspection unchecked
        S[] array = (S[]) new Object[a.length];
        System.arraycopy(a, 0, a, 0, length);
        return array;
    }
}