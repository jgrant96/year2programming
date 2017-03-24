import java.io.*;

public class FindCommonAncestor {
	public static void main(String[] args){
		// To read from the console
		BufferedReader consoleReader  = new BufferedReader(new InputStreamReader(System.in));

		LinkedBinaryTree<String> linkedBinaryTree = null;
		TreeBuilder<String> theTreeBuilder = null;

		try{
			// We build the tree from a file in passed in as an argument
			theTreeBuilder = new TreeBuilder<String>(args[0]);
			linkedBinaryTree = theTreeBuilder.buildTree();
			//Output the contents of the tree
			System.out.println("The tree contains: " +  linkedBinaryTree.toString());

			//Example prompt for user
			System.out.print("Enter first element: ");
			String firstElement = consoleReader.readLine();
			System.out.print("Enter second element: ");
			String secondElement = consoleReader.readLine();
			//Find and output the lowest common ancestor		
			System.out.println("The lowest common ancestor is: " + 
					linkedBinaryTree.lowestCommonAncestor(firstElement,secondElement));
		}
		//catch errors
		catch(MalformedTreeFileException e){
			System.out.println(e.getMessage());
		}
		catch (ElementNotFoundException e){
			System.out.println("Element was not found in tree");
		}
		catch(IOException e ){
			System.out.println("Error reading file: " + args[0] + "\n" + e.getMessage());
		} 	

	}
}
