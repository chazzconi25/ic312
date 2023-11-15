import java.util.NoSuchElementException;

/* YOUR NA FRANCESCONI ME HERE
 * This is the place to write any sources you used etc.
 * I used my brain for this assignment
 */
public class DoubleTree implements AddMax {
  private Node root = null;

  private class Node {
    public Node left  = null;
    public Node right = null;
    public double data;
    public int height;

    public Node(double d) {
      data = d;
      height = 0;
    }
  }

  @Override
  public void add(double x) {
    root = add(root, x);
  }

  private Node add(Node root, double x) {
    if(root == null) {
      return new Node(x);
    } else if (x < root.data) {
      root.left = add(root.left, x);
    } else if (x > root.data) {
      root.right = add(root.right, x);
    } 
    return rotate(root);
  }

  private int bal (Node root) {
    return height(root.right) - height(root.left);
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
    root.height = height(root);
    return root;
  }

  @Override
  public double removeMax() throws NoSuchElementException {
    double max = max(root);
    root = removeMax(root);
    return max;
  }

  private double max(Node root) throws NoSuchElementException {
    if(root == null) {
      throw new NoSuchElementException();
    }
    if(root.right == null) {
      double max = root.data;
      return max;
    }
    return max(root.right);
  }

  private Node removeMax(Node root) throws NoSuchElementException {
    if(root == null) {
      throw new NoSuchElementException();
    }
    if(root.right == null) {
      root = root.left;
      return root;
    } else {
      root.right = removeMax(root.right);
    }
    return rotate(root);
  }

  private Node lRotate(Node oldRoot) {    // oldRoot is node "x" in the image above.
    Node newRoot = oldRoot.right; // newRoot is node "y" in the image above.
    Node middle = newRoot.left;   // middle is the subtree B
    newRoot.left = oldRoot;
    oldRoot.right = middle;
    // FIX HEIGHTS BETA FEATURE
    newRoot.height++;
    oldRoot.height--;
    return newRoot;
  }

  private Node rRotate(Node oldRoot) {    // oldRoot is node "x" in the image above.
    Node newRoot = oldRoot.left; // newRoot is node "y" in the image above.
    Node middle = newRoot.right;   // middle is the subtree B
    newRoot.right = oldRoot;
    oldRoot.left = middle;
    // FIX HEIGHTS BETA FEATURE
    newRoot.height++;
    oldRoot.height--;
    return newRoot;
  }

  private int height(Node n) {
    if (n == null) return -1;
    int lh = -1;
    int rh = -1;
    if(n.left != null) {
      lh = n.left.height;
    }
    if(n.right != null) {
      rh = n.right.height;
    }
    return Math.max(lh, rh) + 1;
  }
}
