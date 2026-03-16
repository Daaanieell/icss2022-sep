package nl.han.ica.datastructures;

import java.util.Iterator;

public class HANLinkedList<T> implements IHANLinkedList {
        private Object[] array;

        @Override
        public void addFirst(Object value) {
//        Object[] previousArray = array;
            array[0] = value;

            for (int i = array.length-1; i > 0; i--) {
                array[i+1] = array[i];
            }
        }

        @Override
        public void clear() {
            array[0] = null;
//        first.setNext(null);
        }

        @Override
        public void insert(int index, Object value) {
//        Object[] previousArray = array;

            for (int i = array.length; i > index; i--) {
                array[i+1] = array[i];
            }

        }

        @Override
        public void delete(int pos) {

        }

        @Override
        public Object get(int pos) {
            return null;
        }

        @Override
        public void removeFirst() {
//        Old.MyListNode newFirst = first.getNext();
//        first.setNext(null);
//        first = newFirst;

        }

        @Override
        public Object getFirst() {
            return null;
        }

        @Override
        public int getSize() {
            return 0;
        }

//        @Override
//        public Iterator iterator() {
//            return null;
//        }
    }
