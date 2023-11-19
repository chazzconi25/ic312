import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class MyGraph implements Graph {
    private HashMap<String,List<String>> graph = new HashMap<>();
    
    /** Returns a list of all vertices in this graph. */
    @Override
    public List<String> vertices() {
        List<String> verticies = new ArrayList<>(graph.keySet());
        return verticies;
    }

    /** Returns a list of all outgoing edges from the given source node.
     * @throws NoSuchElementException if source does not exist.
     */
    @Override
    public List<String> neighbors(String source) throws NoSuchElementException {
        if(!graph.containsKey(source)){
            throw new NoSuchElementException();
        } else {
            return graph.get(source);
        }
    }

    /** Returns true if an edge from source to dest exists.
     * @throws NoSuchElementException if source or dest nodes do not exist.
     */
    @Override
    public boolean getEdge(String source, String dest) throws NoSuchElementException {
        if(graph.containsKey(source) && graph.containsKey(dest)) {
            for(String t : graph.get(source)) {
                if(t.equals(dest)) {
                    return true;
                }
            }
            return false;
        } else {
            throw new NoSuchElementException();
        }
    }

    /** Adds a new vertex to the graph, if it doesn't already exist.
     * No error if a vertex with that name exists already.
     */
    public void addVertex(String label) {
        if(!graph.containsKey(label)) {
            graph.put(label, new ArrayList<String>());
        }
    }


    /** Adds or removes an edge from the graph.
    *
    * If weight is true, the edge should be added if it doesn't already exist.
    * If weight is false, the edge should be removed if it does exist.
    *
    * @throws NoSuchElementException if source or dest vertex names don't exist.
    */
    public void putEdge(String source, String dest, boolean weight) throws NoSuchElementException {
        if(graph.containsKey(source) && graph.containsKey(dest)) {
            List<String> sourceEdges = graph.get(source);
            if(weight) {
                if(!graph.get(source).contains(dest)) {
                    graph.get(source).add(dest);
                }
            } else {
                graph.get(source).remove(dest);
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
