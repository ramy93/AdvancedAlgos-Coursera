import java.io.*;
import java.util.*;

public class App{


	static int[] getVariableValues(SCC[] SCCs, Graph graph){
		return new int[graph.getSize()];
	}

	static boolean isSatisfiable(SCC[] SCCs){
		return false;
	}

	static void printVariables(int[] variables){

		return;
	}

	static Graph buildGraph(){
		InputReader reader = new InputReader(System.in);
		int v = reader.nextInt();
		int c = reader.nextInt();
		Graph graph = new Graph(v);
		int v1, v2;
		for (int i = 0 ; i < c ; i++){
			v1 = reader.nextInt();
			v2 = reader.nextInt();

			graph.addImplicationEdges(v1, v2);

		}
		return graph;

	}

	static void topologicalSort(SCC[] SCCs , Graph graph){
		GraphNode node;
	}
	
	static SCC[] findSCCs(Graph graph){
		return new SCC[1];
	}	
	public static void main(String[] args){
		Graph graph = buildGraph();
		SCC[] SCCs = findSCCs(graph);
		boolean satisfiable = isSatisfiable(SCCs);
		if (!satisfiable){
			System.out.println("UNSATISFIABLE");
			return;
		}
		int[] variableValues = getVariableValues(SCCs, graph);

		printVariables(variableValues);
	}
}

// size must be explicitly given during construction
class Graph{
	int size;
	int offset;
	ArrayList<GraphNode> nodes;

	public Graph(int numNodes){
		this.size = 2*numNodes;
		this.offset = numNodes;
		this.nodes = new ArrayList<GraphNode>();
		for (int i = 1 ; i <= this.size; i++){
			int nodeId = (i > offset)? -(i - offset) : i; 
			this.nodes.add(new GraphNode(nodeId));
		}
	}

	int getSize(){
		return this.size;
	}
	
	public void addImplicationEdges(int v1 , int v2){
		if (v1 == v2){
			this.addEdge(-v1, v1);
			return;
		}
		this.addEdge( -v1 , v2);
		this.addEdge( -v2, v1);
	}

	private void addEdge(int v1, int v2){
		this.nodes.get(getIndex(v1)).add(v2);	
	}
	
	private int getIndex(int index){
		if (index>0){
			return index;	
		}
		return this.offset + Math.abs(index); 
	}
	public Set<Integer> getNeighborSet(int v1){
		return nodes.get(getIndex(v1)).getNeighbors();	
	}
	
}

class GraphNode{
		private int id;
		private HashSet<Integer> neighbors;
		public GraphNode(int id){
			this.id = id;	
			this.neighbors = new HashSet<Integer>();
		}
		public void addNeighbor(int neighbor){
			this.neighbors.add(neighbor);
		}
		
		public void add(int neighbor){
			this.addNeighbor(neighbor);
		}
		
		public Set<Integer> getNeighbors(){
			return this.neighbors;
		}

		public int getId(){
			return this.id;
		}
}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
	    reader = new BufferedReader(new InputStreamReader(stream), 32768);
	    tokenizer = null;
	}

	public String next() {
	    while (tokenizer == null || !tokenizer.hasMoreTokens()) {
		try {
		    tokenizer = new StringTokenizer(reader.readLine());
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
	    }
	    return tokenizer.nextToken();
	}

	public int nextInt() {
	    return Integer.parseInt(next());
	}

	public double nextDouble() {
	    return Double.parseDouble(next());
	}

	public long nextLong() {
	    return Long.parseLong(next());
	}
}


class SCC{

}
// start get input
//input: first line contatins V C , where V is number of vars, C is number of clauses
// each of next C clauses contain two variables -> add implication edge (!v1 -> v2 ) and (!v2 -> v1). if v1 = v2, only add edge from !v1 -> v2 (or !v1 -> v2, same thing)
// find all SCCs of graph in topological order
// for all vertices in graph, if v and -v in same SCC, output UNSATISFIABLE
//for all SCCs in reverse topological order: for all variables in SCC, if variable has been marked, skip, else mark variable with 1 and !variable with 0
