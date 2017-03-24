
public class DictEntry {
	private Position position;
	private int color;
		
	public DictEntry (Position p, int color){
	this.position = p;
	this.color = color;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public int getColor(){
		return color;
	}
}
