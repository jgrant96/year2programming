
public class Figure implements FigureADT {
	private int id, width, height, type;
	private Position pos;
	private BinarySearchTree figure;

	public Figure (int id, int width, int height, int type, Position pos){
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		this.figure = new BinarySearchTree();
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public int getWidth(){
		return this.width;	
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getType(){
		return this.type;
	}
	
	public int getId(){
		return this.id;
	}
	
	public Position getOffset(){
		return this.pos;
	}
	
	public void setOffset(Position value)
	{
		this.pos = value;
	}
	
	public void addPixel(int x, int y, int color) throws BSTException {
		figure.insert(new DictEntry((new Position(x,y)), color));
	}
	
	//returns true if they do in fact intersect
	public boolean intersects (Figure fig){
		//first we'll check to see if the 
		//figure intersect the collision box around the sprite.
		//true when they intersect
		
		//Let's look at the cases when they won't overlap
		//No overlap when the right side of one square is more left than the left side of the other.
		//No overlap when the bottom side of one square is higher than the top of another.
		//There's also the opposite
		//the other squares right side is more left than the first
		//The other square's bottom side is higher than the top of the first
		
		//if the right side of object 1 is more left than the left side of object 2
		if ((this.getOffset().getX() + this.getWidth() < fig.getOffset().getX()))
			return false;
		//if the bottom side of object 1 is more up than the top side of object 2
		if ((this.getOffset().getY() + this.getHeight() < fig.getOffset().getY()))
			return false;
		
		//if the right side of object 2 is more left than the left side of object 1
		if ((fig.getOffset().getX() + fig.getWidth() < this.getOffset().getX()))
			return false;
		
		//if the bottom side of object 2 is more up than the top side of object 1
		if ((fig.getOffset().getY() + fig.getHeight() < this.getOffset().getY()))
			return false;
		
		//In the case that the boxes do overlap, but we don't know
		//if it's the case that the pixels themselves overlap
		//we'll check every pixel in one figure
		//and see if the same position exists in the other figure
		DictEntry compare = this.figure.smallest();
		while (compare != null)
		{
			//the position of the pixel relative to the entire window.
			int absoluteX = compare.getPosition().getX() + this.getOffset().getX();
			int absoluteY = compare.getPosition().getY() + this.getOffset().getY();
			//the position of the pixel relative to the other figure.
		    int relativeX = absoluteX - fig.getOffset().getX();
		    int relativeY = absoluteY - fig.getOffset().getY();
			if (fig.figure.find(new Position(relativeX,relativeY)) != null)
				return true;
			else
				compare = this.figure.successor(compare.getPosition());
		}
		return false;
	}

}
