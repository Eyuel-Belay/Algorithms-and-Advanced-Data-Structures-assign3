import java.util.Map;

public interface A3Graph {
	public void addNode(int nodeItem);
	public void addEdge(int srcNodeItem, int weigth, int tgtNodeItem);
	
	public boolean hasNode(int nodeItem);
	public boolean hasEdge(int srcNodeItem, int weigth, int tgtNodeItem);
	
	public void printAllNodes();
	public void printAllEdges();
	
	public boolean isConnected();
    public boolean isAcyclic();	
	
	public Map<Integer, Integer> shortestPath(int nodeItem);
	
}
