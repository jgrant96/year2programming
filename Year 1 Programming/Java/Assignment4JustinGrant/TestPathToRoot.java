import java.io.*;
import java.util.Iterator;

public class TestPathToRoot {

	public static void main(String[] args) {
		//Create a binary tree
		LinkedBinaryTree<String> theTree = null;
		TreeBuilder<String> theTreeBuilder = null;

		//Catch errors
		try{
			theTreeBuilder = new TreeBuilder<String>(args[0]);
			theTree = theTreeBuilder.buildTree();
		}
		catch( MalformedTreeFileException e) {
			System.out.println( e.getMessage());
		}
		catch( IOException e){
			System.out.println("Error reading file: " + args[0] + "\n" + e.getMessage());
		}

		//create an iterator to iterate through every element in the tree
		Iterator<String> treeElements= theTree.iteratorInOrder();
		String current;

		//Look at each element in the tree
		while(treeElements.hasNext())
		{
			current = treeElements.next();
			// Here we call pathToRoot
			try{
				Iterator<String> pathFromCurrent = theTree.pathToRoot(current); 
				//Output the path to route for each element in the tree
				while (pathFromCurrent.hasNext()){
					System.out.print("For element: " + current + " - the path to the root is " + pathFromCurrent.next());
				}
				System.out.println("");
			}
			//Catch the problem that there isn't the element found in the tree, even though that makes no sense anyway
			//since we are only looking at elements in the tree.
			catch (ElementNotFoundException e)
			{
				System.out.println("Element: " + current + "was not found in tree");
			}
		}

	}

}


