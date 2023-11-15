public class hw08 {
   private Node [] test = new Node[10];
   public static void main(String [] args){
      hw08 arr = new hw08();
      arr.insertoa(183);
      arr.insertoa(520);
      arr.insertoa(862);
      arr.insertoa(67);
      arr.insertoa(52);
      arr.insertoa(197);
      arr.insertoa(604);
      arr.insertoa(388);
      arr.insertoa(680);
      arr.print();
   }

   public void print() {
      for(int i = 0; i < test.length; i++) {
         Node curr = test[i];
         while(curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
         }
         if(curr == null) {
            System.out.println("X");
         }
      }
   }

   public void insertoa(int n) {
      int index = hashAlg(n);
      while(test[index] !=  null) {
         index = (index + 1) %10;
      }
      test[index] = new Node(n);
   }
   
   public void insertsc(int n) {
      int index = hashAlg(n);
      if(test[index] ==  null) {
         test[index] = new Node(n);
      } else {
         Node curr = test[index];
         while(curr.next != null) {
            curr = curr.next;
         }
         curr.next = new Node(n);
      }
   }

   public int hashAlg(int n) {
      return (int)(5*Math.sin(13*(n)-7)+5);
   }

   private class Node {
      private int data;
      private Node next;
      public Node (int data) {
         this.data = data;
      }
   }
}
