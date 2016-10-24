import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by hatem on 2016-10-24.
 */
public class Graph implements A3Graph {

    private Map<Object, Node> adjacencyMap = new HashMap<>();

    @Override
    public void addNode(int nodeItem) {
        if (!hasNode(nodeItem))
            adjacencyMap.put(nodeItem, new Node(nodeItem));
    }

    @Override
    public void addEdge(int srcNodeItem, int weight, int tgtNodeItem) {
        if (!hasNode(srcNodeItem) || !hasNode(tgtNodeItem))
            try {
                throw new Exception("Node does not exist!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        Node node1 = new Node(srcNodeItem);
        Node node2 = new Node(tgtNodeItem);
        node1.addEdge(node2, weight);
    }

    @Override
    public boolean hasNode(int nodeItem) {
        return adjacencyMap.containsKey(nodeItem);
    }

    @Override
    public boolean hasEdge(int srcNodeItem, int weigth, int tgtNodeItem) {
        if (!hasNode(srcNodeItem) || !hasNode(tgtNodeItem))
            return false;
        return new Node(srcNodeItem).hasEdge(new Node(tgtNodeItem));
    }

    @Override
    public void printAllNodes() {

    }

    @Override
    public void printAllEdges() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isAcyclic() {
        return false;
    }

    @Override
    public Map<Integer, Integer> shortestPath(int nodeItem) {
        return null;
    }
}
