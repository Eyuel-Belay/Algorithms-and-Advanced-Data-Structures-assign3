/**
 *
 * Created by hatem on 2016-10-25.
 */
public class test {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.readDotFile("/digraph.txt");
        graph.printAllEdges();
        System.out.println(graph.isAcyclic());
        System.out.println(graph.shortestPath(2));
    }
}
