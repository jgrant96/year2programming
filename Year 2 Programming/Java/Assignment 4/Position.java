
public class Position {
	private int x, y;

	public Position (int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int compareTo(Position p){

		// this > p

		if (y > p.getY() || (y == p.getY() && x > p.getX())){
			return 1;
		} // this == p
		else if (y == p.getY() && x == p.getX()){
			return 0;
		}
		// this = p
		else{//this < p

			return -1;
		}
	}
}