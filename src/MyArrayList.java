import java.util.Iterator;
import java.util.Comparator;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
    private Object[] array;
    private int size;
    private int length;
    private static final int DEFAULT_CAPACITY = 5;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
        length = 0;
    }

    public MyArrayList(int initialCapasity) {
        if (initialCapasity > 0) {
            array = new Object[initialCapasity];
            size = initialCapasity;
            length = 0;
        } else if (initialCapasity == 0) {
            new MyArrayList();
        } else {
            throw new IllegalArgumentException("Illegal Capasity: " + initialCapasity);
        }
    }

    @Override
    public void add(T item) {
        if (length >= size - 1) {
            arrayWidening();
        }
        array[length++] = item;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = Math.max(minCapacity, array.length * 2);
            Object[] newArray = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        array[index] = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) {
        if (length >= size - 1) {
            arrayWidening();
        }
        for (int i = length; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = item;
        length++;
    }

    @Override
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= array.length - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) array[index];
    }

    @Override
    public T getFirst() {
        return (T) array[0];
    }

    @Override
    public T getLast() {
        return (T) array[length - 1];
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= length - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(length - 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int lenght() {
        return length;
    }

    @Override
    public void sort() {
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0 ; j < length - i - 1; j++) {
                if (get(j).compareTo(get(j + 1)) > 0) {
                    Object temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < length; i++) {
            if (array[i] == object) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = length - 1; i >= 0; i--) {
            if (array[i] == object) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean exists(Object object) {
        for (int i = 0; i < length; i++) {
            if (array[i] == object) {
                return true;
            }
        }

        return false;
    }


    @Override
    public Object[] toArray() {
        return array;
    }


    private void arrayWidening() {
        size *= 2;
        Object[] newArray = new Object[size];
        for(int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        length = 0;
        size = DEFAULT_CAPACITY;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    public class MyIterator implements Iterator<T> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        public T next() {
            return get(currentIndex++);
        }
    }
}
