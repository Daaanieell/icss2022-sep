package nl.han.ica.datastructures;

import java.util.ArrayList;
import java.util.List;

public class HANStack<T> implements IHANStack<T> {
    List<T> list = new ArrayList<>();

    @Override
    public void push(T value) {
        list.add(value);
    }

    @Override
    public T pop() {
        T value = list.getLast();
        list.removeLast();
        return value;
    }

    @Override
    public T peek() {
        return list.getLast();
    }
}
