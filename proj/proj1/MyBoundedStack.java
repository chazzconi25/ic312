import java.util.NoSuchElementException;
@SuppressWarnings("unchecked")
public class MyBoundedStack<T> implements BoundedStack<T> {
    private T[] elements;
    private int head = 0;
    private int size = 0;
    private int cap;

    public MyBoundedStack(int cap) {
        elements = (T[])new Object[cap];
        this.cap = cap;
    }

    @Override
    public void push(T item) {
        elements[(head + size) % cap] = item;
        if(size == cap) {
            head++;
            head = (head + size) % cap;
        } else {
            size++;
        }
    }

    @Override
    public T pop() throws NoSuchElementException {
        if(elements[head] == null) {
            throw new NoSuchElementException();
        }
        //size--;
        T tmp = elements[(head + --size) % cap];
        elements[(head + size) % cap] = null;
        return tmp;
    }

    @Override
    public void setCapacity(int capacity) {//O(n)
        int fsize = size;
        for(int i = 0; i < (fsize - capacity); i++) {
            elements[head] = null;
            head = (++head%cap);
            size--;
        }
        T[] tmp = (T[]) new Object[capacity];
        for(int i = 0; i < size; i++){
            tmp[i] = elements[(head+i)% cap];
        }
        elements = tmp;
        head = 0;
        cap = capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        T[] tmp = (T[]) new Object[cap];
        elements = tmp;
        size = 0;
        head = 0;
    }

    public static void main(String [] args) {
        BoundedStack stk = new MyBoundedStack<>(3);
    }
}
