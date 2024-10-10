package com.wavemaker.collections.customarraylist;
import java.util.*;

public class CustomList<E> implements List<E> {
    private int size;
    private int modCount = 0;
    private static final int DEFAULT_CAPACITY = 3;
    transient Object[] elementData;


    public CustomList() {
        size = 0;
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();}

    @Override
    public Object[] toArray() {
        Object[] copyArray = new Object[size];
        for (int i = 0; i < size; i++) {
            copyArray[i] = elementData[i];
        }
        return copyArray;
    }


    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    @Override
    public boolean add(E e) {
        modCount++;
        if (size == elementData.length)
            grow(size + 1);
        elementData[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        modCount++;
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        /*boolean contains = false;
        int j = 0;

        for (int i = 0; i < c.size(); i++) {
            CustomList d = (CustomList) c;
            Object o = d.get(i);
            boolean isFound = false;
            for (j = 0; j < size; j++) {
                isFound = elementData[j].equals(o);
                if (isFound) {
                    break;
                }
            }
            if (!isFound) {
                return false;
            }

            if (j == size) {
                return false;
            }

        }*/
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        modCount++;
        int newElementsLength = c.size();
        if (newElementsLength == 0) {
            return false;
        }
        int toFitLength = elementData.length - size;
        if (newElementsLength > toFitLength) {
            grow(size + newElementsLength);
        }
        for (E element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        modCount++;

        int newElementsLength = c.size();
        if (newElementsLength == 0) {
            return false;
        }
        int toFitLength = elementData.length - size;
        if (newElementsLength > toFitLength) {
            grow(size + newElementsLength);
        }

        for (int i = size - 1; i >= index; i--) {
            elementData[i + newElementsLength] = elementData[i];
        }
        int i = 0;
        for (E element : c) {
            elementData[index + i] = element;
            i++;
        }

        size += newElementsLength;
        /*for (int i = index, j = 0; i < size + newElementsLength && j < newElementsLength; i++, j++) {
            add(i, (E) newElements[j]);
        }*/

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        modCount++;
        boolean isRemovedAll = true;
        int removeElementsLength = c.size();
        if (removeElementsLength == 0) {
            return false;
        }
        for (Object element : c) {
            boolean present = remove(element);
            if (!present) {
                isRemovedAll = false;
            }
        }
        return isRemovedAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int removeElementsLength = c.size();
        if (removeElementsLength == 0) {
            return false;
        }

        List<Object> toRemove = new CustomList<>();

        for (int j = 0; j < size; j++) {
            boolean isFound = false;
            for (Object o : c) {
                if (elementData[j].equals(o)) {
                    isFound = true;
                    break;
                }

            }
            if (!isFound) {
                toRemove.add(elementData[j]);
            }
        }

        removeAll(toRemove);
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }

    }

    @Override
    public E get(int index) {
        validateIndexRange(index);
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        validateIndexRange(index);
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        grow(size + 1);
        int dec = 0;
        for (int i = size - 1; i >= index; i--) {
            elementData[size - dec] = elementData[i];
            dec++;
        }
        // System.arraycopy(elementData, index, elementData, index + 1, size - index);//TODO
        elementData[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        validateIndexRange(index);
        E removeElement = (E) elementData[index];

        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[--size] = null;
        return removeElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elementData[i], o)) {
                return i;
            }

        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);


    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListItr(index);

    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {

        subListRangeCheck(fromIndex, toIndex, size);
        List<E> subListObject = new CustomList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subListObject.add((E) elementData[i]);
        }

        return subListObject;
    }

    private Object[] grow(int requiredCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity < requiredCapacity) {
            int newCapacity;
            int increment = Math.min(oldCapacity / 2, 50);
            newCapacity = oldCapacity + increment;
            newCapacity = Math.max(newCapacity, requiredCapacity);
            Object[] newArray = new Object[newCapacity];
            for (int i = 0; i < elementData.length; i++) {
                newArray[i] = elementData[i];
            }

            // return elementData = Arrays.copyOf(elementData, newCapacity);
            return elementData = newArray;
        }
        return elementData;
    }

    private void validateIndexRange(int index) {
        if (index < 0 || index >= size) {

            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
    }

    private class CustomIterator implements Iterator<E> {

        int cursor = 0;
        int lastReturnIndex = -1;
        int expectedModCount = modCount;
       ;



        public CustomIterator() {

        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            checkForComodification();
            int cursorIndex = cursor;
            if (cursorIndex >= size)
                throw new NoSuchElementException();
            Object[] elementData = CustomList.this.elementData;
            if (cursorIndex >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = cursorIndex + 1;
            return (E) elementData[lastReturnIndex = cursorIndex];
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private class ListItr extends CustomIterator implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public E previous() {
            checkForComodification();
            int prevoiusIndex = cursor - 1;
            if (prevoiusIndex < 0)
                throw new NoSuchElementException();
            Object[] elementData = CustomList.this.elementData;
            if (prevoiusIndex >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = prevoiusIndex;
            return (E) elementData[lastReturnIndex = prevoiusIndex];

        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (lastReturnIndex < 0) {
                if (lastReturnIndex < 0)
                    throw new IllegalStateException();
                checkForComodification();

                try {
                    CustomList.this.remove(lastReturnIndex);
                    cursor = lastReturnIndex;
                    lastReturnIndex = -1;
                    expectedModCount = modCount;
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }

            }

        }

        @Override
        public void set(E e) {
            if (lastReturnIndex < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                CustomList.this.set(lastReturnIndex, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }


        }

        @Override
        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                CustomList.this.add(i, e);
                cursor = i + 1;
                lastReturnIndex = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }

        }


    }


}
