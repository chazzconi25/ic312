import java.util.ArrayList;
import java.util.List;

/** Collects elements and allows one-time selection of the k largest ones.
 *
 * Best implemented with a heap!
 * The current implementation (using a sorted list) should be replaced
 * with something more efficient via heap operations.
 */
public class TopK<T extends Comparable<T>> {
  /** The k value that will be used to select the top k. */
  private int k;
  /** A list to hold the inserted elements. */
  private List<T> elements = new ArrayList<>();

  /** Create a new, empty instance.
   *
   * @param k How many items to return from a later call to getTop().
   */
  public TopK(int k) {
    this.k = k;
  }

  /** Adds a new element to the collection.
   * Note that this method should not be called after getTop() has been called.
   */
  public void add(T element) {
    if (elements == null)
      throw new IllegalStateException("Can't make any other calls after the first call to getTop().");
    // NOTE: You may want to change how this works with a heap!
    elements.add(element);
  }

  private void swap(int index1, int index2) {
    T val1 = elements.get(index1);
    T val2 = elements.get(index2);
    elements.set(index1, val2);
    elements.set(index2, val1);
  }

  /** Retrieves the k largest elements that have been added, from largest to smallest.
   * Note that this method can only be called once.
   * If fewer than k items have been added, then all of them should be returned, sorted
   * from largest to smallest.
   */
  public List<T> getTop() {
    if (elements == null)
      throw new IllegalStateException("Can't make any other calls after the first call to getTop().");
    
    heapify();
    if(k > elements.size()) {
      k = elements.size();
    }
    List<T> ret = new ArrayList<>();
    for(int i = 0; i < k; i++) {
      ret.add(elements.get(0));
      if(elements.size() != 1) {
        elements.set(0, elements.remove(elements.size()-1));
        bubbleDown(0);
      }
    }
    elements = null;
    return ret;
  }

  private void heapify() {
    for(int i = elements.size()-1; i > -1; i--) {
      bubbleDown(i);
    }
  }

  private void bubbleDown(int index) {
    int lcindex = 2*index + 1;
    int rcindex = 2*index + 2;
    if(lcindex < elements.size() && rcindex < elements.size() &&
       (elements.get(index).compareTo(elements.get(lcindex)) < 0 ||
       elements.get(index).compareTo(elements.get(rcindex)) < 0)) {
      if(elements.get(rcindex).compareTo(elements.get(lcindex)) < 0) {
        swap(index, lcindex);
        bubbleDown(lcindex);
        return;
      }
      swap(index, rcindex);
      bubbleDown(rcindex);
      return;
    }
    if(lcindex < elements.size() &&
       elements.get(index).compareTo(elements.get(lcindex)) < 0) {
      swap(index, lcindex);
      bubbleDown(lcindex);
    }
  }
}