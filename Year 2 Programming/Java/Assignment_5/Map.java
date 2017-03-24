import java.io.*;
import java.util.*;

public class Map {
	
	static BufferedReader in;
	private Graph matrix;
	private int allowedChanges;
	private int width;
	private int height;
	private int start;
	private int goal;
	
	public Map (String inputFile) throws MapException, GraphException {
		try{// in case we have trouble reading
			
		//variables
		
		String currentLine = "";
		in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
		
		//Getting the first line and discarding it.
		currentLine = in.readLine();
		if (currentLine == null)
			throw new MapException("Map formatted incorrectly");
		
		//Get the second line that contains the width of the graph and store it.
		currentLine = in.readLine();
		if(currentLine == null)
			throw new MapException("Map formatted incorrectly");
		try{
		width = Integer.parseInt(currentLine);
		}
	    catch(NumberFormatException e) { 
		        throw new MapException("Map formatted incorrectly");
		} 
		catch(NullPointerException e) {
		        throw new MapException("Map formatted incorrectly");
		}
		//So the line was correctly stored in width
		
		//READING AGAIN
		//Get the third line which contains the height of the graph and store it.
		currentLine = in.readLine();
		if(currentLine == null)
			throw new MapException("Map formatted incorrectly");
		try{
		height = Integer.parseInt(currentLine);
		}
	    catch(NumberFormatException e) { 
		        throw new MapException("Map formatted incorrectly");
		} 
		catch(NullPointerException e) {
		        throw new MapException("Map formatted incorrectly");
		}
		// Height has been correctly stored.
		
		
		//Reading again
		//This time we read a line containing the number of allowed bus route changes and store it
		currentLine = in.readLine();
		if(currentLine == null)
			throw new MapException("Map formatted incorrectly");
		try{
		allowedChanges = Integer.parseInt(currentLine);
		}
	    catch(NumberFormatException e) { 
		        throw new MapException("Map formatted incorrectly");
		} 
		catch(NullPointerException e) {
		        throw new MapException("Map formatted incorrectly");
		}
		//We've finally stored all the variables we need to.
		//Now we must start reading the bulk of the graph
		
		//We'll create a graph now that we have information on it.
		
		//Graph is the matrix storing information on edges.
		//mapping is a representation of the graph as string array
		matrix = new Graph((height * width));
		String [] mapping = new String [height *2 - 1];
		
		//we will put the map in an array of strings
		//this way we can easily access any character in the map
		//without having to re-read from the file
		//in doing this, we also check if the map is correctly formatted
		for (int count = 0; count < (height * 2 - 1); count++)
		{
			//read the first row in the map
			mapping[count] = in.readLine();	
			System.out.println(mapping[count]);
			//make sure that we've actually read something
			if (mapping[count] == null)
				throw new MapException("Map formatted incorrectly");
			//make sure the length of the string is correct
			if (mapping[count].length() != (width*2 -1))
				throw new MapException("Map formatted incorrectly");
			
		}
		
		
		//We will loop through the array
		//Each time we find what can be a node
		//we will look at it's neighbors
		
		//An important point. whenever we look at a node to find out it's name
		//the name is count2 + width*count
		//We'll use a character to store the current edge we're looking at
		char edgeCharacter;
		for (int count = 0; count < (height * 2 - 1); count+=2)
		{
			for (int count2 = 0; count2 < (width * 2 - 1); count2+=2){
				
				//If the position we are at right now has the start point
				//we list it
				if(mapping[count].charAt(count2) == '0')
					start = count2 + count*width;
				
				//If we found the goal we list it.
				if (mapping[count].charAt(count2)=='1')
					goal = (count2/2) +(count/2)*width;
				
				//check if there's a node to the right
				if (count2 + 2 < (width*2-1)){
					edgeCharacter = mapping[count].charAt(count2+1);
					if (edgeCharacter != ' '){
						matrix.insertEdge(matrix.getNode((count2/2) + width*(count/2)), 
								matrix.getNode((count2/2) + 1 + width*(count/2)), 
								""+edgeCharacter);
					}
				}
				//check if there's a node to the left
				if(count2 - 2 >= 0){
					edgeCharacter = mapping[count].charAt(count2-1);
					if(edgeCharacter != ' '){
						matrix.insertEdge(matrix.getNode((count2/2) + width*(count/2)), 
								matrix.getNode((count2/2) - 1 + width*(count/2)),
								""+edgeCharacter);
					}
				}
				//check if there's a node above
				if(count - 2 >= 0){
					edgeCharacter = mapping[count-1].charAt(count2);
					if(edgeCharacter != ' '){
						matrix.insertEdge(matrix.getNode((count2/2) + width*(count/2)), 
								matrix.getNode((count2/2) + width*(count/2 - 1)), 
								""+edgeCharacter);
					}
				}
				//check if there's a node below
				if(count + 2 < (height*2 - 1)){
					edgeCharacter = mapping[count+1].charAt(count2);
					if(edgeCharacter != ' '){
						matrix.insertEdge(matrix.getNode(count2/2 + width*(count/2)), 
								matrix.getNode(count2/2 + width*(count/2 + 1)), 
								""+edgeCharacter);
					}
					
				}
			}	
		}
		
		
		
		}
		catch(IOException e){
			System.out.println("error reading file");
			throw new MapException("Could not read file");
		}
		finally{
			try{
				in.close();
			}
			catch(IOException e){
				System.out.println("error closing file");
			}
		}
		
	}
	
	
	//Will return the graph object
	Graph getGraph(){
	return this.matrix;
	}
	
	//Will return an iterator to the correct path
	Iterator findPath() throws GraphException{
		
		boolean found;
		System.out.println("Allowed busline changes: " + this.allowedChanges);
	    Stack<Node> thePath = new Stack<Node>();
	    //call to helper method
		found = findPath (thePath, matrix.getNode(start), matrix.getNode(goal), this.allowedChanges, null);
		if(found == false)
		{
			System.out.println("Path could not be found");
			return null;
		}
		else
			System.out.println("Path found");
		
		return thePath.iterator();
		
	}
	
	//recursively finds the path from a starting point to a gal
	boolean findPath(Stack<Node> thePath, Node beginning, Node end, int busChangesLeft, String currentBusline) throws GraphException{
		//We mark every place we've been and push it onto the stack
		beginning.setMark(true);
		thePath.push(beginning);
		if (beginning.getName() == end.getName())
		{
			return true;
		}
			
		//We look at all of the edges to a node
		//it's a DFS so we'll explore where each ledge leads
		Iterator<Edge> iterator = matrix.incidentEdges(beginning);
		while(iterator.hasNext())
		{
			//If we haven't been there before, we'll follow the edge
			Edge currentEdge = iterator.next();
			if (currentEdge.firstEndpoint() == beginning){
				if (currentEdge.secondEndpoint().getMark() == false)
				{
					//We'll freely move to the next node if we don't have to switch buslines
					if (currentBusline == null || currentBusline.equals(currentEdge.getBusLine()))
					{
						if (findPath(thePath, currentEdge.secondEndpoint(),end, busChangesLeft, currentEdge.getBusLine()) == true)
							return true;
					}
					else if(busChangesLeft > 0) //We switch buslines, but we will have less allowed switches for later calls
					{
						if (findPath(thePath, currentEdge.secondEndpoint(),end, busChangesLeft - 1, currentEdge.getBusLine()) == true)
							return true;
					}
				}
			}
			
		}
		//allowed buslines will recursively undo themselves when we explore more
		//paths
		//We unset the nodes so we can recross them from new angles
		beginning.setMark(false);
		thePath.pop();
		//we reach here when we are at a dead end.
		//it takes us back to a previous call, so we can look at more
		//incident edges.
		return false;
		
	}
	
}
