import java.util.NoSuchElementException;
@SuppressWarnings("unchecked")
public class MyBoundedStack<T> implements BoundedStack {
    private T[] elements;
    private int size = 0;

    @Override
    public void push(Object item) {
        if(size == elements.length) {
            T[] tmp = (T[])(new Object[elements.length*2]);
            for(int i = 0; i < elements.length; i++) {
                tmp[i] = elements[i];
            }
            elements = tmp;
        }
        elements[size] = (T)item;
        size++;
    }

    @Override
    public Object pop() throws NoSuchElementException {
        if(size == 0) {
            throw new NoSuchElementException();
        }
        return elements[size--];
    }

    @Override
    public void setCapacity(int capacity) {
        if(capacity < size) {
            for(int i = capacity; i < size; i++) {
                elements[i] = null;
            }
        }
        size = capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        T[] tmp = (T[])(new Object[size]);
        elements = tmp;
    }
    
}
