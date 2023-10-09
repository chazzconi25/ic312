import java.util.Deque;
import java.util.LinkedList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K,V> {
  // private classes and fields here
  private Node root = null;
  private int size = 0;

  private class Node {
    private K key;
    private V value;
    private Node left;
    private Node right;
    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
    public K getKey() { return key; }
    public V getValue() { return value; }
  
    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
  }

  @Override
  public V get(K key) {
    return get(key, root);
  }

  private V get(K key, Node curr){
    if(curr == null) {
      return null;
    }
    if(curr.getKey().equals(key)) {
      return curr.getValue();
    }
    if(curr.getKey().compareTo(key) < 0) {
      return get(key, curr.left);
    }
    return get(key, curr.right);
  }

  @Override
  public boolean containsKey(K key) {
    return containsKey(key, root);
  }

  private boolean containsKey(K key, Node curr) {
    if(curr == null) {
      return false;
    }
    if(curr.getKey().equals(key)) {
      return true;
    }
    if(curr.getKey().compareTo(key) < 0) {
      return containsKey(key, curr.left);
    }
    return containsKey(key, curr.right);
  }

  @Override
  public void put(K key, V value) {
    if(!containsKey(key)) {
      size++;
    }
    root = put(key, value, root);
  }

  private Node put(K key, V value, Node curr) {
    if(curr == null) {
      return new Node(key, value);
    }
    if(curr.getKey().equals(key)) {
      curr.setValue(value);
    } else if(curr.getKey().compareTo(key) < 0) {
      curr.left = put(key, value, curr.left);
    } else if(curr.getKey().compareTo(key) > 0){
      curr.right = put(key, value, curr.right);
    }
    return curr;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Deque<K> traverse() {
    Deque<K> ret = new LinkedList<K>();
    traverse(root, ret);
    return ret;
  }

  private void traverse(Node curr, Deque<K> ret) {
    if(curr == null) {
      return;
    }
    traverse(curr.left, ret);
    ret.push(curr.getKey());
    traverse(curr.right, ret);
  }

  @Override
  public void remove(K key) {
    if(containsKey(key)) {
      size--;
    }
    root = remove(root, key);
  }

    private Node remove(Node cur, K key) {
    if (cur == null){
      return cur;
    }
    else if (cur.getKey().compareTo(key) < 0)
      cur.left = remove(cur.left, key);
    else if (cur.getKey().compareTo(key) > 0)
      cur.right = remove(cur.right, key);
    else {
      if (cur.left == null)
        return cur.right;
      else if (cur.right == null)
        return cur.left;
      else {
        // hard case: cur.left and cur.right are both not null
        // find the successor and remove it
        K successor = getMin(cur.right);
        cur.key = successor;
        cur.right = remove(cur.right, successor);
      }
    }
    return cur;
  }

  private K getMin(Node cur) {
    if (cur.left == null)
      return cur.key;
    else
      return getMin(cur.left);
  }
}
