import java.io.*;
import java.util.NoSuchElementException;
/**
 * This class takes a text file which represents a specific hexagon maze as a command line argument
 * The class will solve the maze and output the number of steps it took to find the end, and if it is found
 * The class uses a stack, and will output how many hexagons are on the stack, or in other words,
 * how many possible paths there could be left to search.
 * @author Justin Grant 250787131
 *
 */
public class MazeSolver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			if(args.length<1){
				throw new IllegalArgumentException("Please provide a Maze file as a command line argument");
			}
			Boolean found = false; //Checks whether the end of the maze is found
			int steps = 0; //Number of steps taken till the end of the maze
			String mazeFileName = "";
			mazeFileName = args[0];
			Maze maze = new Maze(mazeFileName); //Creating the maze from a file string
			Hexagon current = maze.getStart();
			ArrayStack<Hexagon> hexStack = new ArrayStack<Hexagon>(100); //Array implementation of a stack
			hexStack.push(current); //pushes the first hexgon onto the stack

			//The while loop will...
			//look at one hexagon, and push it's neighbors onto the stack.
			//it then pops one hexgon from the stack, and looks at its neighbors.
			//this continues until the hexagon popped is the end hexagon.
			while (!hexStack.isEmpty() && found  == false)
			{
				current = hexStack.pop();
				steps++;
				if (current.isEnd())
					found = true;
				else
				{
					for (int count = 0; count<6; count++ )
						if (current.getNeighbour(count)!= null)
						{
							if (current.getNeighbour(count).isUnvisited() && !current.getNeighbour(count).isWall())
							{
								hexStack.push(current.getNeighbour(count));
								current.getNeighbour(count).setPushed();

							}

						}
				}
				current.setProcessed();
				maze.repaint();
			}
			maze.repaint();
			//Output statistics
			System.out.println("The number of steps: " + steps);
			System.out.println("The number of hexagons on the stack: " + hexStack.size());
			System.out.println("Is the end found: " + found);
		}
		catch(IOException e)
		{
			System.out.println("Wrong file name. Cannot open file");
		}
		catch (UnknownMazeCharacterException e)
		{
			System.out.println("The maze file is invalid. Illegal character was used in file");
		}
		catch (NoSuchElementException e)
		{
			System.out.println("The maze file is invalid. Format is invalid");

		}

	}

}
