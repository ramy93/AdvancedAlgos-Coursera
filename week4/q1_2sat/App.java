public class App{


	public int[] getVariableValues(SCC[] SCCs, Graph graph){
		return new int[graph.getSize()];
	}

	boolean IsSatisfiable(SCC[] SCCs){
		return false;
	}

	void printVariables(int[] variables){

		return;
	}

	Graph buildGraph(){
		InputReader reader = new InputReader();
		int v = reader.nextInt();
		int c = reader.nextInt();
		Graph graph = new Graph();
		for (int i = 1 ; i < = v; i++){
			graph.addVertex(i);
			graph.addVertex(-i);

		}
		int v1;
		int v2;
		for (int i = 0 ; i < c ; i++){
			v1 = reader.nextInt();
			v2 = reader.nextInt();

			graph.addEdge(v1, v2);

		}
		return graph;

	}

	void topologicalSort(SCC[] SCCs , Graph graph){

	}
	
	public static void main(String[] args){
		Graph graph = buildGraph();
		SCC[] SCCs = findSCCs(graph);
		bollean satisfiable = IsSatisfiable(SCCs);
		if (!satisfiable){
			System.out.println("UNSATISFIABLE");
			return
		}
		int[] variableValues = getVariableValues(SCCs, graph)

		printVariables(int[] variables);
	}
}


class Graph{
	int size;
	int [] nodes;

	public Graph(int numNodes){
		this.size = 2*numNodes;
		this.nodes = new int[this.size];
	}
	int getSize(){
		return 0;
	}
	


}

static class InputReader {
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

// start get input
//input: first line contatins V C , where V is number of vars, C is number of clauses
// each of next C clauses contain two variables -> add implication edge (!v1 -> v2 ) and (!v2 -> v1). if v1 = v2, only add edge from !v1 -> v2 (or !v1 -> v2, same thing)
// find all SCCs of graph in topological order
// for all vertices in graph, if v and -v in same SCC, output UNSATISFIABLE
//for all SCCs in reverse topological order: for all variables in SCC, if variable has been marked, skip, else mark variable with 1 and !variable with 0
