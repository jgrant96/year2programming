
public class BinarySearchTree implements BinarySearchTreeADT {

	//Each tree node contains data, and links to the children and parents.
		private DictEntry data;
		private BinarySearchTree left;
		private BinarySearchTree right;
		private BinarySearchTree parent;

		
	//Constructor for the root of the tree.
	//Also creates nodes for the leaves that contain no data.
	public BinarySearchTree(){
	left = new BinarySearchTree (this);
	right = new BinarySearchTree (this);
	parent = null;
	data = null;
	}
	
	//Constructor that creates a node while specifying a parent.
	//Does so that we can have children, but does not recursively call
	//constructor methods so we have an infinitely large tree on its creation.
	public BinarySearchTree(BinarySearchTree parent){
		this.parent = parent;
		left = null;
		right = null;
		data = null;
	}
	
	
	//the lame find method
	//returns a DictEntry based on a key given as parameter.
	public DictEntry find (Position key){
		//We create a reference to the tree we are in right now.
		//We make the runner instead reference the right or left nodes
		//We reference the right if the key is to the right, aka it's larger
		//We reference the left if the key is to the left, you guessed it. it's smaller.
		//We return null if we can't find the key at all.
		BinarySearchTree runner = this;
		while(true){
			if (runner.data == null)
				return runner.data;
			else if (runner.data.getPosition().compareTo(key) == 0)
				return runner.data;
			else if (runner.data.getPosition().compareTo(key) == 1)
				runner = runner.left;
			else 
				runner = runner.right;
		}
	}

	//See find method except this one returns the node/BinarySeartTree, instead of returning
	//the data entry
	private BinarySearchTree findNode (Position key){
		BinarySearchTree runner = this;
		while(true){
			if (runner.data == null)
				return runner;
			else if (runner.data.getPosition().compareTo(key) == 0)
				return runner;
			else if (runner.data.getPosition().compareTo(key) == 1)
				runner = runner.left;
			else 
				runner = runner.right;

		}
	}

	//We insert a node into the tree
	public void insert (DictEntry data)throws BSTException{
		//We use the findNode to find it should go, or we'll get a result saying
		//it's already there.
		BinarySearchTree runner = this.findNode(data.getPosition());
		//It's not there so we enter data into the node.
		//Then we create two new binary tree nodes, that are the left
		//and right nodes and store no data.
		if (runner.data == null){
			runner.data = data;
			runner.left = new BinarySearchTree(runner);
			runner.right = new BinarySearchTree(runner);
		}
		else //it's not there. we throw an exception.
		{
			throw new BSTException("insertion incomplete");
		}
	}

	

	//We are going to remove a node, ok?
	public void remove (Position key) throws BSTException {
		//We try to find the node that we wish to remove.
		BinarySearchTree runner = this.findNode(key);
		//if it's not there were freak out and throw and exception.
		if (runner.data == null)
			throw new BSTException("Remove failed");

		//If there's no node to the left
		//This means we can just move right tree upwards.
		//The right node is now the root if runner is a root.
		//runner is the node that we found and want to remove btw.
		if (runner.left.data == null)
		{
			//So if runner is the root.
			if (runner.parent == null)
			{
				//we just move the right node up.
				//The node's data is equal to the right data
				runner.data = runner.right.data;

					runner.right = runner.right.right;
			}
			//if it isn't the root we also need to work with the parent.
			else
			{
				//we consider whether runner is the right or left child.
				//then we set the runner's right side as the right or left child of the 
				//parent
				BinarySearchTree parent = runner.parent;
				if (parent.right == runner)
				{
					parent.right = runner.right;
					parent.right.parent = parent;
				}
				else
				{
					parent.left = runner.left;
					parent.left.parent = parent;
				}
			}

		}
		//Basically there was something to the left.
			//also worth noting is that if there was nothing to the left and also nothing to the right
			//then we would have just made either the root an empty node or the child of some node empty
		else if (runner.right.data == null)// same deal if there's no right child
		{
			if (runner.parent == null)
			{
				runner.data = runner.left.data;
					runner.left = runner.left.left;
			}
			//if it isn't the root we also need to work with the parent.
			else
			{
				//
				BinarySearchTree parent = runner.parent;
				if (parent.right == runner)
				{
					parent.right = runner.left;
					parent.right.parent = parent;
				}
				else
				{
					parent.left = runner.left;
					parent.left.parent = parent;
				}
			}
		}
		else //So like, this means that there are BOTH left and right internal children.
		{
			//so we get the smallest node from the right side of the tree
			//and that is the new subroot.
			BinarySearchTree moveUp = runner.right.smallestNode();
			runner.data = moveUp.data;
			
			
			//So we found the smallest and changed the node to remove into this.
			//but we need to get rid of the node we deleted.
			BinarySearchTree parent = moveUp.parent;
			if (parent.right == moveUp)
			{
				parent.right = moveUp.right;
				moveUp.parent = parent;
			}
			else
			{
				parent.left = moveUp.left;
				moveUp.parent = parent;
			}
			

		}
	}

	
	
	//This will give you the smallest DictEntry greater than one given with the position 'key'
	public DictEntry successor(Position key){
		BinarySearchTree runner = findNode(key);
		//if the node isn't in the tree, but we want the successive value
		//we go up the tree, and find the successive value for the one immediately above

		if (runner.data == null && runner.parent != null) // if the node is a leaf node
			runner = runner.parent;
		
		//If there is a right child to the node
		if (runner.right.data != null)
			return runner.right.smallest(); //We return the left most node of the right child
		else //if we can't do this we must go up the tree and find the parent greater than runner.
		{
			while (runner.parent != null)//we keep going up until we reach the root or something to return
			{
				//if we find a parent that is greater than the current node
				//we return it because it is the successor.
				//if not we continue up the tree until we find a parent that is larger.
				if (runner.parent.data.getPosition().compareTo(runner.data.getPosition()) == 1)
					return runner.parent.data;
				else
					runner = runner.parent;
			}
			//we couldn't find a parent that is larger so we return null.
			return null;
		}
		
		
		
		}
	
	//This will give you the largest DictEntry with position smaller than key
	public DictEntry predecessor(Position key){
		//very similar to the successor method obv.
		BinarySearchTree runner = findNode(key);
		if (runner.data == null && runner.parent != null)
			runner = runner.parent;
		
		//If there is a child to the left of the node
		//We return the largest node from that child
		if (runner.left.data != null)
			return runner.left.largest();
		else //else we go up the tree until we find a parent that is smaller
		{
			while (runner.parent != null) //we continue up the tree until we reach the root
			{
				//if we find a parent that's smaller, then it must be the predecessor
				if (runner.parent.data.getPosition().compareTo(runner.data.getPosition()) == -1)
					return runner.parent.data;
				else
					runner = runner.parent; //continue up the root
			}
			return null; //we couldn't find it so we return null
		}
		
	}
	
	//We search for the smallest
	public DictEntry smallest(){
		BinarySearchTree runner = this;
	 while(runner.left.data != null){
		 runner = runner.left;
	 }
	 return runner.data;
	}
	
	//We search for the smallest node
	private BinarySearchTree smallestNode(){
		BinarySearchTree runner = this;
	 while(runner.left.data != null){
		 runner = runner.left;
	 }
	 return runner;
	}
	
	//We search for the largest
	public DictEntry largest(){
		BinarySearchTree runner = this;
		while (runner.right.data != null){
			runner = runner.right;
		}
		return runner.data;
	}

	
}
