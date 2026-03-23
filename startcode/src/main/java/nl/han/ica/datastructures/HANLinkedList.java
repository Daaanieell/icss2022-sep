package nl.han.ica.datastructures;

public class HANLinkedList<T> implements IHANLinkedList<T> {

    private static final int DEFAULT_CAPACITY = 16;

    private Object[] data;
    private int size;

    public HANLinkedList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void addFirst(T value) {
        System.arraycopy(data, 0, data, 1, size);
        data[0] = value;
        size++;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public void insert(int index, T value) {
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void delete(int pos) {
        System.arraycopy(data, pos + 1, data, pos, size - pos - 1);
        data[--size] = null;
    }

    @Override
    public T get(int pos) {
        return (T) data[pos];
    }

    @Override
    public void removeFirst() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        delete(0);
    }

    @Override
    public T getFirst() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return (T) data[0];
    }

    @Override
    public int getSize() {
        return size;
    }
}