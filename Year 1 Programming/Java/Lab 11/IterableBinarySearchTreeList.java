import java.util.Iterator;


public class IterableBinarySearchTreeList extends BinarySearchTreeList {

	public Iterator<T> iteratorInOrderDescending()
	{
		ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
		inorderDescending (root, tempList);

		return tempList.iterator();
	}
	
	protected void inorderDescending (BinaryTreeNode<T> node, 
			ArrayUnorderedList<T> tempList) 
	{
		if (node != null)
		{
			inorder (node.getRight(), tempList);
			tempList.addToRear(node.getElement());			
			inorder (node.getLeft(), tempList);
		}
	}
	
}
