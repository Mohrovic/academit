import java.util.*;

final class MyArray<T> implements List<T> {
    private T[] items = (T[]) new Object[2];
    private int length;
    private Class<T> type;

    public MyArray() {
    }

    public MyArray(T[] items) {
        this.items = items;
    }

    private void increaseCapacity() {
        T[] old = items;
        items = (T[]) new Object[old.length * 2];
        System.arraycopy(old, 0, items, 0, old.length);
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

    public void add(MyArray<T> items) {
        T[] myArrayItems = (T[]) items.toArray();
        int itemsLength = myArrayItems.length;

        while (length + itemsLength > this.items.length) {
            increaseCapacity();
        }

        System.arraycopy(myArrayItems, 0, this.items, length, itemsLength);
        length += itemsLength;
    }

    @Override
    public void add(int index, T element) {
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
            return false;
        }

        T[] myArrayItems = (T[]) c.toArray();
        int itemsLength = myArrayItems.length;

        while (length + itemsLength > this.items.length) {
            increaseCapacity();
        }

        System.arraycopy(myArrayItems, 0, this.items, length, itemsLength);
        length += itemsLength;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            return false;
        }

        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        T[] addingItems = (T[]) c.toArray();
        int addingItemsLength = addingItems.length;

        while (length + addingItemsLength > this.items.length) {
            increaseCapacity();
        }

        T[] old = (T[]) new Object[length];
        old = items;

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
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        T comparingItem = (T) obj;
        for (T e : this.items) {
            if (e == comparingItem) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> elements) {
        if (elements == null || elements.getClass() != this.getClass()) {
            return false;
        }

        if (items.length < length) {
            return false;
        }

        MyArray comparingCollection = (MyArray) elements;

        for (int i = 0; i < elements.size(); i++) {
            boolean contains = false;

            for (int j = 0; j < length; j++) {
                if (items[j].equals(comparingCollection.get(i))) {
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
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        MyArray comparingMyArray = (MyArray) obj;

        if (comparingMyArray.size() != length) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (comparingMyArray.get(i) != items[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }
        return items[index];
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 17;
        hash = prime * hash * length + items.hashCode();
        return hash;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null || o.getClass() != type) {
            return -1;
        }

        for (int i = 0; i < length; i++) {
            if (items[i] == o) {
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
        return new Iterator<T>() {
            private int index = -1;

            @Override
            public boolean hasNext() {
                if (index < length - 1) {
                    ++index;
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                if (index < length - 1) {
                    ++index;
                    return items[index];
                }
                return null;
            }
        };
    }


    @Override
    public int lastIndexOf(Object o) {
        if (o == null || o.getClass() != type) {
            return -1;
        }

        for (int i = length; i >= 0; i--) {
            if (items[i] == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            private int index = -1;

            @Override
            public boolean hasNext() {
                if (index < length - 1) {
                    ++index;
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                if (index < length - 1) {
                    ++index;
                    return items[index];
                }
                return null;
            }

            @Override
            public boolean hasPrevious() {
                if (index > 0) {
                    --index;
                    return true;
                }
                return false;
            }

            @Override
            public T previous() {
                if (index > 0) {
                    --index;
                    return items[index];
                }
                return null;
            }

            @Override
            public int nextIndex() {
                if (index == length - 1) {
                    return length;
                }
                return index;
            }

            @Override
            public int previousIndex() {
                if (index <= -1) {
                    return -1;
                }
                return index - 1;
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

    public ListIterator<T> listIterator(int index) {
        if (index < -1 || index > length - 1) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        return new ListIterator<T>() {
            private int currentIndex = index;

            @Override
            public boolean hasNext() {
                if (currentIndex < length - 1) {
                    ++currentIndex;
                    return true;
                }
                return false;
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
                if (currentIndex > 0) {
                    --currentIndex;
                    return true;
                }
                return false;
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
                return 0;
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
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Заданный индекс находится вне диапазона списка");
        }

        T removedItem = items[index];
        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
            --length;
        }
        return removedItem;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null || o.getClass() != type) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (items[i] == o) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null || c.getClass() != type) {
            return false;
        }

        T[] removingArray = (T[]) c.toArray();

        boolean contains = false;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < removingArray.length; j++) {
                if (items[i].equals(removingArray[j])) {
                    System.arraycopy(this.items, i + 1, this.items, i, length - i - 1);
                    length--;
                    contains = true;
                }
            }
        }
        return contains;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null || c.getClass() != type) {
            return false;
        }

        T[] retainingArray = (T[]) c.toArray();
        int retainingArrayLength = retainingArray.length;

        boolean changed = false;
        boolean contains = false;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < retainingArrayLength; j++) {
                if (items[i].equals(retainingArray[j])) {
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
        if (index < 0 || index > length) {
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
        T[] array = (T[]) new Object[length];
        System.arraycopy(items, fromIndex, array, 0, toIndex - fromIndex);

        List list = (List) new Object();
        list.addAll(Arrays.asList(array));

        return list;
    }

    @Override
    public Object[] toArray() {
        T[] array = (T[]) new Object[length];
        System.arraycopy(items, 0, array, 0, length);
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] array = (T[]) new Object[a.length];
        System.arraycopy(a, 0, a, 0, length);
        return array;
    }
}