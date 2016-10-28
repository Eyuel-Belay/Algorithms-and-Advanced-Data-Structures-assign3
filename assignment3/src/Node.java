import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *A class that represents the node in a graph of a kind 'int'
 * Created by hatem on 2016-10-24.
 */
public class Node {
    private int nodeValue;
    private List<Edge> edgesList;
    private List<Node> childrenNodes;
    private boolean isVisited;

    /**
     * Each node is an object of the vertex and contains a list of the edges
     * to keep track of the connected nodes
     * @param nodeValue: the value of the node
     */
    public Node(int nodeValue) {
        this.nodeValue = nodeValue;
        edgesList = new ArrayList<>();
        childrenNodes = new ArrayList<>();
    }

    public void addEdge(Node node, int weight) {
        if (!hasEdge(node)) {
            edgesList.add(new Edge(this, weight, node));
            childrenNodes.add(node);
        }
        else System.out.println("Edge from node " + nodeValue + " to node "
                + node.getNodeValue() + " already exists!");
    }

    public boolean hasEdge(Node node) {
        return findEdge(node).isPresent();
    }

    public void removeEdge(Node node) {
        Optional<Edge> optional = findEdge(node);
        if(optional.isPresent())
            edgesList.remove(optional.get());
        else System.out.println("Edge does not exist");
    }

    private Optional<Edge> findEdge(Node node) {
        return edgesList.stream().filter(edge -> edge.isBetween(this, node)).findFirst();
    }

    public boolean isSource() {
        return edgesList.size() > 0;
    }

    public boolean isSink() {
        return edgesList.size() == 0;
    }
    //Getters and setters
    public int getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(int nodeValue) {
        this.nodeValue = nodeValue;
    }

    public List<Edge> getEdgesList() {
        return edgesList;
    }

    public void setEdgesList(List<Edge> edgesList) {
        this.edgesList = edgesList;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public List<Node> getChildrenNodes() {
        return childrenNodes;
    }
}


