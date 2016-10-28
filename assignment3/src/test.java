/**
 *
 * Created by hatem on 2016-10-25.
 */
public class test {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.readDotFile("/digraph.txt");
        graph.printAllNodes();
        graph.printAllEdges();
        System.out.println(graph.isAcyclic());
    }
}
