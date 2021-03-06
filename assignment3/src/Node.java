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
    private int distance;

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

    public Edge getEdge(Node targetNode) {
        Optional optional = findEdge(targetNode);
        if (optional.isPresent())
            return (Edge) optional.get();
        return null;
    }

    public void setEdgesList(List<Edge> edgesList) {
        this.edgesList = edgesList;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Node> getChildrenNodes() {
        return childrenNodes;
    }
}


