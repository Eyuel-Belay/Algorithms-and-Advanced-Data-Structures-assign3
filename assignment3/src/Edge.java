/**
 *
 * Created by hatem on 2016-10-24.
 */
public class Edge {
    private Node node1;
    private Node node2;
    private int weight;

    public Edge(Node node1, Node node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public Node fromNode() {
        return node1;
    }

    public Node toNode() {
        return node2;
    }

    public boolean isBetween(Node node1, Node node2) {
        //TODO: could be .equals() instead of ==
        return (this.node1 == node1 && this.node2 == node2);
    }
}