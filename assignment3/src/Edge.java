/**
 *
 * Created by hatem on 2016-10-24.
 */
public class Edge {
    private Node node1;
    private Node node2;
    private int weight;

    public Edge(Node node1, int weight, Node node2) {
        this.node1 = node1;
        this.weight = weight;
        this.node2 = node2;
    }

    public Node fromNode() {
        return node1;
    }

    public Node toNode() {
        return node2;
    }

    public int getWeight() { return weight;  }
    public boolean isBetween(Node node1, Node node2) {
        //TODO: could be .equals() instead of ==
        return (this.node1 == node1 && this.node2 == node2);
    }
}
