
public class Node {
	
	private int name;
	private boolean mark;
	
	//Constructor method
	public Node(int name){
		this.name = name;
		mark = false;
	}
	
	//Sets a boolean mark onto the node
	public void setMark(Boolean mark){
		this.mark = mark;
	}
	
	//Gets a boolean mark from the node
	public boolean getMark(){
		return this.mark;
	}
	
	//Gets the name of the node.
	public int getName(){
		return this.name;
	}
	
}
