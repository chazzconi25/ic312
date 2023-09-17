// FRANCESCONI 25806
// ANY SOURCES OR COLLABORATION YOU USED HERE
// NONE

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T>, Iterable<T> {
  private Node head;
  private int size;

  @Override
  public T get(int index) throws IndexOutOfBoundsException {
    if(index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      return get(index, 0, head);
    }
  }

  private T get(int index, int pos, Node curr) {
    if(pos == index) {
      return curr.data;
    } else {
      curr = curr.next;
      pos++;
      return get(index, pos, curr);
    }
  }

  public void set(int index, T data) throws IndexOutOfBoundsException {
    if(index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      set(index, 0, head, data);
    }
  }

  private void set(int index, int pos, Node curr, T data) {
    if(pos == index) {
      curr.data = data;
    } else {
      curr = curr.next;
      pos++;
      set(index, pos, curr, data);
    }
  }

  public void add(int index, T data) throws IndexOutOfBoundsException {
    if(index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    } else if(size == 0) { 
      head = new Node(data, null);
      size++;
      return;
    } else if(index == 0) { 
      Node tmp = new Node(data, head);
      head = tmp;
      size++;
      return;
    } else {
      add(index, 0, head, null, data);
    }
  }

  private void add(int index, int pos, Node curr, Node prev, T data) {
    if(pos == index) {
      Node tmp = new Node(data, curr);
      prev.next = tmp;
      size++;
      return;
    } else {
      prev = curr;
      curr = curr.next;
      pos++;
      add(index, pos, curr, prev, data);
    }
  }

  @Override
  public void remove(int index) throws IndexOutOfBoundsException {
    if(index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    } else if(index == 0) {
      Node tmp = head.next; 
      head.next = null;
      head = tmp;
      size--;
      return;
    } else {
      remove(index, 0, head, null);
    }
  }

  private void remove(int index, int pos, Node curr, Node prev) {
    if(pos == index) {
      prev.next = curr.next;
      curr.next = null;
      size--;
      return;
    } else {
      prev = curr;
      curr = curr.next;
      pos++;
      remove(index, pos, curr, prev);
    }
  }

  @Override
  public int size() {
    return size;
  }

  /** Removes ALL elements matching the given one using .equals().
   *
   * @param element The element that should be removed
   */
  public void removeAll(T element) {
    removeAll(element, head, null);
  }

  private void removeAll(T element, Node curr, Node prev) {
    if(curr == null) {
      return;
    } else if(curr.data.equals(element) && curr == head) {
      head = curr.next;
      curr.next = null;
      curr = head;
      size--;
      removeAll(element, curr, prev);
    } else if(curr.data.equals(element)) {
      prev.next = curr.next;
      curr.next = null;
      curr = prev.next;
      size--;
    } else {
      removeAll(element, curr.next, curr);
    }
  }

  /** Gets the 2nd-to-last element.
   *
   * @return The data in the second-to-last node in the list (if any)
   * @throws NoSuchElementException if the list size is less than 2
   */
  public T penultimate() throws NoSuchElementException {
      if(size < 2) {
        throw new NoSuchElementException();
      } else if(size == 2) {
        T tmp = head.data;
        Node curr = head; 
        head = head.next;
        curr.next = null;
        return tmp;
      } else{
        return penultimate(head, head.next, 1);
      }
  }

  private T penultimate(Node prev, Node curr, int pos) {
    if(pos == size - 2) {
      prev.next = curr.next;
      T tmp = curr.data;
      curr.next = null;
      return tmp;
    }
    return penultimate(curr, curr.next, pos +1);
  }

  public Iterator<T> iterator() {
    return new MyListIterator();
  }

  private class Node {
    private T data;
    private Node next;

    public Node(T data, Node next) {
      this.data = data;
      this.next = next;
    }
  }

  protected class MyListIterator implements Iterator<T> {
    private int index = 0;
    @Override
    public boolean hasNext() {
      return index < size - 1;
    }

    @Override
    public T next() {
      index++;
      return ;
    }
  }

  public static void main(String[] args) {
    LinkedList test = new LinkedList<String>(); 
    test.add(0,"a");
    test.add(1,"a");
    test.add(2,"b");
    test.add(3,"a");
    for(String s: test) {
      System.out.println(s);
    }
  }
}
