package nl.han.ica.datastructures;

import java.util.ArrayList;
import java.util.List;

public class HANQueue<T> implements IHANQueue<T> {
    private List<T> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enqueue(T value) {
        list.add(value);
    }

    public T dequeue() {
        T value = list.getFirst();
        list.removeFirst();
        return value;
    }

    @Override
    public T peek() {
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.size();
    }
}
