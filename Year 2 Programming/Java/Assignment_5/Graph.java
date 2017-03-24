import java.util.*;
import java.util.Vector;

public class Graph {
	private Edge[][] matrix;
	private Node[] nodeList;
	private int matrixSize;

	//private class storing the existence and data of an edge.
	

	//Graph constructor method
	//creates list of nodes, and matrix of edges
	//initilizes things
	public Graph(int n){
		this.matrixSize = n;
		this.matrix = new Edge[n][n];
		this.nodeList = new Node[n];
		for (int count1 = 0; count1 < n; count1++)
		{
			nodeList[count1]= new Node(count1);
			for (int count2 = 0; count2 < n; count2++)
				this.matrix[count1][count2] = null;

		}
	}
	
	//Inserts an edge between two nodes
	//will throw graph exception if we trying for nodes that
	//are outside bounds
	//inserts on both sides of the adjacency matrix.
	public void insertEdge(Node u, Node v, String busLine) throws GraphException{
		if (u.getName() > this.getSize()-1|| v.getName() > this.getSize()-1)
			throw new GraphException("Node not found in graph");
		Edge insertion = new Edge(u, v, busLine);
		Edge insertion2 = new Edge (v, u, busLine);
		this.matrix[u.getName()][v.getName()] = (insertion);
		this.matrix[v.getName()][u.getName()] = (insertion2);	
	}

	
	//Returns the node with the same name in the parameter
	public Node getNode(int name) throws GraphException{
		if (name >= this.getSize())
		{
			throw new GraphException("Node not found in the graph");
		}
		return nodeList[name];
	}

	//Iterator
	//throws graph exception if you try to get 
	//a node outside the graph
	Iterator incidentEdges(Node u) throws GraphException{
		if (u.getName() >= this.getSize())
			throw new GraphException("accessessing invalid node");
		
		//uses a vector to store all of the edges incident to a node
		Vector<Edge> listing = new Vector<Edge>();
		int matrixSize = getSize();
		int thisName = u.getName();
		//Goes through the matrix looking at the nodes row/column
		//adds all edges that hte node belongs to into the list
		for (int count = 0; count < matrixSize; count++){
			if (matrix[u.getName()][count] != null){
				listing.addElement(this.matrix[thisName][count]);
			}
			else if (matrix[count][u.getName()] != null){
				listing.addElement(this.matrix[count][thisName]);
			}
		}
		return listing.iterator();
		}
		


	//Gets the edge between two nodes
	//throws an exception if it doesn't exist
	public Edge getEdge(Node u, Node v) throws GraphException{
		if (u.getName() >= this.getSize() || v.getName() >= this.getSize())
			throw new GraphException("Node not found in graph");
		return this.matrix[u.getName()][v.getName()];
	}

	//Finds out if two nodes are adjacent
	public boolean areAdjacent(Node u, Node v) throws GraphException{
		if (u.getName() >= this.getSize() || v.getName() >= this.getSize())
			throw new GraphException("Invalid node, not found in graph");
	    else if (this.matrix[u.getName()][v.getName()] == null)
			return false;
		else
			return true;
	}

	//Gets the size of the matrix
	private int getSize()
	{
		return this.matrixSize;
	}
}

