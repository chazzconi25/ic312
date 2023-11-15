import java.util.ArrayList;

public class TreeMap<K extends Comparable<K>, V> implements Map<K,V> {
  // private classes and fields here
  private Node root = null;
  private int size = 0;

  private class Node {
    private K key;
    private V value;
    private Node left;
    private Node right;
    private int height;
    public Node(K key, V value) {
      this.key = key;
      this.value = value;
      height = 0;
    }
    public K getKey() { return key; }
    public V getValue() { return value; }
    public int getHeight() { return height; }
    
    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
    public void setHeight(int height) { this.height = height; }
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
      return get(key, curr.right);
    }
    return get(key, curr.left);
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
      return containsKey(key, curr.right);
    }
    return containsKey(key, curr.left);
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
      curr.right = put(key, value, curr.right);
    } else if(curr.getKey().compareTo(key) > 0){
      curr.left = put(key, value, curr.left);
    }
    return rotate(curr);
  }

  private Node rotate(Node root) {
    if(bal(root) == -2) {
      if(bal(root.left) == 1) {
        root.left = lRotate(root.left);
      }
      root = rRotate(root);
    } else if(bal(root) == 2) {
      if(bal(root.right) == -1) {
        root.right = rRotate(root.right);
      }
      root = lRotate(root);
    }
    root.setHeight(height(root));
    return root;
  }

  private int bal (Node root) {
    return height(root.right) - height(root.left);
  }

  private int height(Node n) {
    if (n == null) return -1;
    int lh = -1;
    int rh = -1;
    if(n.left != null) {
      lh = n.left.getHeight();
    }
    if(n.right != null) {
      rh = n.right.getHeight();
    }
    return Math.max(lh, rh) + 1;
  }

  private Node lRotate(Node oldRoot) {    // oldRoot is node "x" in the image above.
  Node newRoot = oldRoot.right; // newRoot is node "y" in the image above.
  Node middle = newRoot.left;   // middle is the subtree B
  newRoot.left = oldRoot;
  oldRoot.right = middle;
  newRoot.setHeight(height(newRoot));
  oldRoot.setHeight(height(oldRoot));
  return newRoot;
}

private Node rRotate(Node oldRoot) {    // oldRoot is node "x" in the image above.
  Node newRoot = oldRoot.left; // newRoot is node "y" in the image above.
  Node middle = newRoot.right;   // middle is the subtree B
  newRoot.right = oldRoot;
  oldRoot.left = middle;
  newRoot.setHeight(height(newRoot));
  oldRoot.setHeight(height(oldRoot));
  return newRoot;
}

  @Override
  public int size() {
    return size;
  }

  
  public ArrayList<K> keys() {
    ArrayList<K> ret = new ArrayList<K>();
    traverse(root, ret);
    return ret;
  }

  private void traverse(Node curr, ArrayList<K> ret) {
    if(curr == null) {
      return;
    }
    traverse(curr.left, ret);
    ret.add(curr.getKey());
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
      cur.left = remove(cur.right, key);
    else if (cur.getKey().compareTo(key) > 0)
      cur.right = remove(cur.left, key);
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
