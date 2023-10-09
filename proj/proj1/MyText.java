import java.util.NoSuchElementException;

public class MyText implements Text {
    private Node head;
    private Node cur;
    private Node pcur;
    private int size = 0;

    @Override
    public char get() throws NoSuchElementException {
        if(cur == null) {
            throw new NoSuchElementException();
        }
        return cur.data;
    }

    @Override
    public void insert(char c) {
        if(head == null || pcur == null) {
            head = new Node(c, null, null);
            pcur = head;
            if(cur != null) {
                head.next = cur;
                cur.prev = head;
            }
            cur = head.next;
        } else {
            pcur.next = new Node(c, pcur, cur);
            pcur = pcur.next;
            if(cur != null) {
                cur.prev = pcur;
            }
        }
        size++;
    }

    @Override
    public void delete() throws NoSuchElementException {
        if(cur == null) {
            throw new NoSuchElementException();
        }
        if(cur.prev == null) {
            head = cur.next;
            cur = cur.next;
            if(cur != null) {
                cur.prev = null;
            }
        } else {
            cur = cur.next;
            pcur.next = cur;
            if(cur != null) {
                cur.prev = pcur;
            }
        }
        size--;
    }

    @Override
    public boolean canMoveLeft() {
        return pcur != null;
    }

    @Override
    public void moveLeft() throws NoSuchElementException {
        if(pcur == null) {
            throw new NoSuchElementException();
        }
        cur = pcur;
        pcur = pcur.prev;
    }

    @Override
    public boolean canMoveRight() {
        return cur != null;
    }

    @Override
    public void moveRight() throws NoSuchElementException {
        if(cur == null) {
            throw new NoSuchElementException();
        }
        pcur = cur;
        cur = cur.next;
    }

    @Override
    public void print() {
        Node pos = head;
        for(int i = 0; i < size; i++) {
            System.out.print(pos.data);
            pos = pos.next;
        }
        System.out.println();
        pos = head;
        for(int i = 0; i < size; i++) {
            if(pos != cur) {
                System.out.print(" ");
            } else {
                System.out.println("^");
                return;
            }
            pos = pos.next;
        }
        System.out.println("^");
    }

    private class Node {
        private char data;
        private Node next;
        private Node prev;
    
        public Node(char data, Node prev, Node next) {
          this.data = data;
          this.next = next;
          this.prev = prev;
        }
      }
}
