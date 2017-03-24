
public class Edge {
	private Node firstNode;
	private Node secondNode;
	private String busLine;
	
	//Constructor method
	public Edge(Node u, Node v, String busLine){
		this.firstNode = u;
		this.secondNode = v;
		this.busLine = busLine;
	}
	
	//One vertex in the graph
	Node firstEndpoint(){
		return this.firstNode;
	}
	
	//The opposite vertex in the graph
	Node secondEndpoint(){
		return this.secondNode;
	}
	
	//Gets the string busline the edge belongs to
	String getBusLine(){
		return this.busLine;
	}
	
}
