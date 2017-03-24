/**
 * Thrown when access is attempted outside valid hexagon neighbor index range.
 * Since it is a Hexagon, can only have 6 neighbors (0-5 inclusive)
 * @author CS1027
 *
 */
public class InvalidNeighborIndex extends ArrayIndexOutOfBoundsException{

	public InvalidNeighborIndex(int i){
		super("Invalid index for hexagon neighbour:" + i + "  Must be 0-5 inclusive");
	}
}
