import java.util.Iterator;

/** A List implementation with fixed, bounded capacity.
 *
 * When an instance of this class is constructed, the chosen capacity
 * represents the maximum size the list can ever grow to.
 */
public class BoundedList<T> implements List<T>, Iterable<T> {
  private T[] elements;
  private int size;
  /** Create a new BoundedList with the given maximum capacity.
   */
  public BoundedList(int capacity) {
    @SuppressWarnings("unchecked")
    T[] elements = (T[]) new Object[capacity];
    this.elements = elements;
  }

  public T get(int index) throws IndexOutOfBoundsException {
    if(index > size -1) {
      throw new IndexOutOfBoundsException();
    }
    return elements[index];
  }

  
  public void set(int index, T data) throws IndexOutOfBoundsException {
    if(index > size - 1) {
      throw new IndexOutOfBoundsException();
    }
    elements[index] = data;
  }

  public void add(int index, T data) throws IndexOutOfBoundsException, IllegalStateException {
    if(index > size) {
      throw new IndexOutOfBoundsException();
    }
    if(size + 1 > elements.length) {
      throw new IllegalStateException();
    }
    for(int i = size; i > index; i--) {
      elements[i] = elements[i-1];
    }
    elements[index] = data;
    size++;
  }

  public void remove(int index) throws IndexOutOfBoundsException {
    if(index > size - 1) {
      throw new IndexOutOfBoundsException();
    }
    for(int i = index; i < size-1; i++) {
      elements[i] = elements[i+1];
    }
    elements[size-1] = null;
    size--;
  }

  public int size() {
    return size;
  }

  // this produces a string like "[ 1 2 3 4 ]"
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (int i = 0; i < size(); ++i) {
      sb.append(get(i).toString());
      sb.append(' ');
    }
    sb.append(']');
    return sb.toString();
  }

  public Iterator<T> iterator() {
    return new BoundedIterator(elements);
  }



  public static void main(String[] args) {
  }

  private class BoundedIterator implements Iterator<T> {
    private T[] elements;
    int index = 0;
    
    BoundedIterator(T[] elements) {
      this.elements = elements;
    }

    public boolean hasNext() {
      return index < elements.length;
    }

    public T next() {
      T nxt = elements[index];
      index++;
      return nxt;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
