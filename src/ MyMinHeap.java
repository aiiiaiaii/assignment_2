import java.util.NoSuchElementException;
public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;

    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    public T get(int index) {
        return heap.get(index);
    }

    public boolean empty() {
        return heap.length() == 0;
    }

    public int size() {
        return heap.length();
    }

    public void insert(int item) {
        heap.add(item);
        heapifyUp(heap.indexOf(item));
    }

    public int extractMin() {
        T min = getMin();
        heap.remove(heap.indexof(min));
        int startIdx = (heap.lenght() / 2) - 1;
        for (int i = startIdx; i > 0; i--) {
            heapifyUp(i);
        }

        return min;
    }

    public T getMin() {
        if (empty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = heap.get(0);

        for (int i = 0; i < heap.length(); i++) {
            if (min.compareTo(heap.get(i)) > 0) {
                min = heap.get(i);
            }
        }

        return min;
    }

    private T leftChildOf(int index) {
        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex < heap.length()) {
            return heap.get(leftChildIndex);
        } else {
            throw new IndexOutOfBoundsException("no heap for that");
        }
    }

    private T rightChildOf(int index) {
        int rightChildIndex = 2 * index + 2;
        if (rightChildIndex < heap.length()) {
            return heap.get(rightChildIndex);
        } else {
            throw new IndexOutOfBoundsException("no heap for that");
        }
    }

    private int parentOf(int index) {
        if (index < 0 || index > heap.length()) {
            throw new IndexOutOfBoundsException("There is no parent of that heap");
        }

        return index / 2;
    }

    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap.get(index) < heap.get(parent)) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int leftChild, rightChild, minIndex;
        while (true) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            minIndex = index;
            if (leftChild < heap.size() && heap.get(leftChild) < heap.get(minIndex)) {
                minIndex = leftChild;
            }
            if (rightChild < heap.size() && heap.get(rightChild) < heap.get(minIndex)) {
                minIndex = rightChild;
            }
            if (minIndex != index) {
                swap(index, minIndex);
                index = minIndex;
            } else {
                break;
            }
        }
    }
    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    private void traverseUp(int index) {
        int parentIndex = parentOf(index);

        while (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = parentOf(index);
        }
    }
}
