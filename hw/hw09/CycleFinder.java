import java.util.ArrayList;
import java.util.Stack;

public class CycleFinder {
  public static boolean hasCycle(Graph graph, String start) {
    ArrayList<String> visited = new ArrayList<String>();
    Stack<String> fringe = new Stack<String>();

    fringe.push(start);
    while(!fringe.empty()) {
      String uu = fringe.pop();

      if(!visited.contains(uu)) {
        visited.add(uu);
        for(String vv : graph.neighbors(uu)) {
          if(graph.neighbors(vv).contains(start)) {
            return true;
          }
          fringe.push(vv);
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    // XXX you shouldn't need to change anything here
    String dotf = Stdin.input("graph file: ");
    Graph graph = DotReader.readFrom(dotf);

    while (true) {
      String start = Stdin.input("starting node or 'quit': ");
      if (start == null || start.equals("quit")) break;
      if (hasCycle(graph, start))
        System.out.format("%s is in a cycle.\n", start);
      else
        System.out.format("%s is NOT in a cycle.\n", start);
    }
  }
}
