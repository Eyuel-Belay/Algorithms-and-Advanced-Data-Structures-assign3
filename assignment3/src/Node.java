import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * Created by hatem on 2016-10-24.
 */
public class Node {
    private int vertex;
    private List<Edge> edges;
    private Node parent;
    private boolean isVisited;

    public Node(int vertex) {
        this.vertex = vertex;
        this.edges = new ArrayList<Edge>();
    }

    public int vertex() {
        return vertex;
    }

    public boolean addEdge(Node node, int weight) {
        if (hasEdge(node))
            return false;

        Edge newEdge = new Edge(this, node, weight);
        return edges.add(newEdge);
    }

    public boolean hasEdge(Node node) {
        return findEdge(node).isPresent();
    }

    public boolean removeEdge(Node node) {
        Optional<Edge> optional = findEdge(node);
        return  (optional.isPresent() && edges.remove(optional.get()));
    }

    private Optional<Edge> findEdge(Node node) {
        return edges.stream().filter(edge -> edge.isBetween(this, node)).findFirst();
    }


    public List<Edge> edges() {
        return edges;
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public Node parent() {
        return parent;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
