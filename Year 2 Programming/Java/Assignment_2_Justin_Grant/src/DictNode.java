
//DictNode class
//a psuedo implementation of linked list
public class DictNode {
	private DictNode next; //next element in the list
	private DictEntry element; //element stored in the node
	
	public DictNode (){ //constructor method
	}
	public DictNode(DictEntry entry){
		element = entry;
	}
	
	//We use this to change the next node of the current node
	public void setNext (DictNode newNode){
		next = newNode;
	}
	
	//We use this to change the element stored
	public void setElement(DictEntry entry){
		element = entry;
	}
	
	//We use this to get data from the node
	public DictEntry getElement (){
		return element;
	}
	
	//We use this to find the next element in the node.
	public DictNode next(){
		return next;
	}
}
