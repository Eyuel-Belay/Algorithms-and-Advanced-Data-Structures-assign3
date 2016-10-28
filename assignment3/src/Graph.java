import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Array;
import java.util.*;

/**
 *
 * Created by hatem on 2016-10-24.
 */
public class Graph implements A3Graph {

    private ArrayList<Node> adjacencyList = new ArrayList<>();
    private ArrayDeque<Node> stack;

    /**
     * A method to parse a DOT file into a graph of nodes which is held in a List
     * @param fileName: the name of the DOT file
     */
    public void readDotFile(String fileName) {
        List fileData = new ArrayList();
        try {
            String currentLine;
            URL url = this.getClass().getResource(fileName);

            BufferedReader fileReader = new BufferedReader(new FileReader(new File(url.toURI())));

            int node1 = 0;
            int node2 = 0;
            int weight;

            while ((currentLine = fileReader.readLine()) != null) {
                //Ignores the line "digraph {" and so forth. Only takes the lines with nodes and their values
                if(currentLine.contains("[")) {
                    int nodeCounter = 0;
                    for (int i = 0; i < currentLine.length(); i++) {
                        char c = currentLine.charAt(i);
                        //Take the node labels
                        if (c == 'n') {
                            nodeCounter++;
                            if (nodeCounter == 1) {
                                node1 = Integer.parseInt(String.valueOf(currentLine.charAt(i + 1)));
                                addNode(node1);
                            } else if (nodeCounter == 2) {
                                node2 = Integer.parseInt(String.valueOf(currentLine.charAt(i + 1)));
                                addNode(node2);
                                break;
                            }
                        }
                    }
                    //Takes the weight value if it exists
                    if (currentLine.contains("weight=")) {
                        int index = currentLine.indexOf("weight=") + 8;
                        weight = Integer.parseInt(currentLine.substring(index, currentLine.lastIndexOf("\"")));
                    } else weight = 1;       //Default value of the weight
                    addEdge(node1, weight, node2);
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNode(int nodeItem){
        if (!hasNode(nodeItem))
            adjacencyList.add(new Node(nodeItem));
    }

    @Override
    public void addEdge(int srcNodeItem, int weight, int tgtNodeItem) {
        if (!hasNode(srcNodeItem) || !hasNode(tgtNodeItem)) {
            try {
                throw new Exception("Node does not exist!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Node node1 = findNode(new Node(srcNodeItem), adjacencyList).get();
        Node node2 = findNode(new Node(tgtNodeItem), adjacencyList).get();
        node1.addEdge(node2, weight);
    }

    @Override
    public boolean hasNode(int nodeItem) {
        return findNode(new Node(nodeItem), adjacencyList).isPresent();
    }

    @Override
    public boolean hasEdge(int srcNodeItem, int weigth, int tgtNodeItem) {
        if (!hasNode(srcNodeItem) || !hasNode(tgtNodeItem))
            return false;
        return new Node(srcNodeItem).hasEdge(new Node(tgtNodeItem));
    }

    @Override
    public void printAllNodes() {
        for (Node n : adjacencyList)
            System.out.println("Node " + n.getNodeValue());
    }

    @Override
    public void printAllEdges() {
        for (Node n : adjacencyList) {
            for (Edge e : n.getEdgesList())
                System.out.println("node " + e.fromNode().getNodeValue() + " --" + e.getWeight() + "--> " + "node " + e.toNode().getNodeValue());
        }
    }

    @Override
    public boolean isConnected() {
        for (Node n : adjacencyList) {
            if (n.getEdgesList().size() > 0)
                return false;
        }
        return true;
    }

    @Override
    public boolean isAcyclic() {
        stack = new ArrayDeque<>();  //stack of the sorted nodes
        Set<Node> visited = new HashSet<>();    //queue of the visited nodes
        ArrayList<Node> expanded = new ArrayList<>();   //a List of the completely explored nodes where all of its children are visited
        for (Node node : adjacencyList) {
            try {
                topSort(node, stack, visited, expanded);
            } catch (Exception e) { return false;}
        }
        return true;
    }

    /**
     * A helper method for performing the topological sort
     * @param node : the current node
     * @param stack : stack of the sorted nodes
     * @param visited : queue of the visited nodes
     * @param expanded: a List of the completely explored nodes where all of its children are visited
     */
    private void topSort(Node node, Deque<Node> stack, Set<Node> visited, ArrayList<Node> expanded) throws Exception {
        if (visited.contains(node)) {
            //If the node is already explored then no need to visit it again
            if (expanded.contains(node))
                return;
            //Else it is a cycle graph
            throw new Exception("Graph is cyclic");
        }
        visited.add(node);
        //Visiting the children of this node recursively
        for (Node childNode : node.getChildrenNodes()) {
            topSort(childNode, stack, visited, expanded);
        }
        stack.offerFirst(node);
        expanded.add(node);
    }

    @Override
    public Map<Integer, Integer> shortestPath(int srcNode) {
        Map<Integer, Integer> shortestPaths = new HashMap<>();

        if (isAcyclic()) {
            ArrayList<Node> sortedNodes = new ArrayList(Arrays.asList(stack.toArray()));    //Converting the stack to ArrayList
            Node sourceNode = findNode(new Node(srcNode), sortedNodes).get();
            sourceNode.setDistance(0);        //Distance to the source is 0

            //Setting distance to all other nodes is infinity
            for (Node node : sortedNodes) {
                if (!node.equals(sourceNode))
                    node.setDistance(9999);
            }

            //Visiting every node u and checking each of its child v
            for (Node node : sortedNodes) {
                for (Node n : node.getChildrenNodes()) {
                    //If the distance is infinite then assign it to the distance of the source node + the weight from the u node
                    if (n.getDistance() > node.getDistance() + node.getEdge(n).getWeight()) {
                        n.setDistance(node.getDistance() + node.getEdge(n).getWeight());
                        shortestPaths.put(n.getNodeValue(), n.getDistance());          //adding to the final node to the map with its distance from the source
                    }
                }
            }
        }
        return shortestPaths;
    }

    /**
     * A helper method to check  whether a node exists in the list of nodes
     * @param node: the node to be checked
     * @return an container of the similar nodes or null if non exists
     */
    private Optional<Node> findNode(Node node, ArrayList<Node> list) {
        //Checks for each node value with the node's value to be added
        return list.stream().filter(node1 -> node1.getNodeValue() == node.getNodeValue()).findFirst();
    }


}
