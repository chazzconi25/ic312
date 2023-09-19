import java.util.NoSuchElementException;

public class MyBoundedStack<T> implements BoundedStack {
    private T[] elements;
    private int index = 0;
    private int size = 0;

    @Override
    public void push(Object item) {
        if(size == elements.length) {
            @SuppressWarnings("unchecked")
            T[] tmp = (T[])(new Object[elements.length*2]);
            for(int i = 0; i < elements.length; i++) {
                tmp[i] = elements[i];
            }
            elements = tmp;
        }
    }

    @Override
    public Object pop() throws NoSuchElementException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

    @Override
    public void setCapacity(int capacity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCapacity'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }
    
}
