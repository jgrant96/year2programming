/**
 * Class which represents a collection of the magazine class
 * Has functions to add magazines and find their prices.
 * @author Justin Grant 250787131
 *
 */
public class MagazineCollection {

	/**
	 * Collection of magazines
	 */
	private Magazine[] magazCollection;

	/**
	 * Number of magazine in collection
	 */
	private int numberOfMagazine;

	/**
	 * Constructor method to create an instance of MagazineCollection
	 * Default size is 5
	 */
	public MagazineCollection(){
		this.magazCollection = new Magazine[5];
		this.numberOfMagazine = 0;
	}

	/**
	 * Constructor method to create an instance of MagazineCollection
	 * @param sizeOfCollection The maximum number of magazines in the collection
	 * The maximum number of magazines will be less than required to test expandCapacity method
	 */
	public MagazineCollection(int sizeOfCollection){
		this.magazCollection = new Magazine[sizeOfCollection - 1];
		this.numberOfMagazine = 0;
	}
/**
 * Method to add a magazine to the collection
 * @param magazine The magazine to add
 */
	public void addMagazine (Magazine magazine){
		if (this.magazCollection.length == this.numberOfMagazine)
			this.expandCapacity();
		magazCollection[numberOfMagazine] = magazine;
		numberOfMagazine++;
	}

	/**
	 * Method to expand capacity of magazine collection array
	 */
	public void expandCapacity (){
		Magazine[] newArr = new Magazine[this.magazCollection.length*2];
		for (int count = 0; count < magazCollection.length; count++)
			newArr[count] = magazCollection[count];
		magazCollection = newArr;
	}

	/**
	 * Method to find the price of a specified magazine in the collection
	 * @param name The name of the magazine to be found
	 * @param format The format of the magazine to be found
	 * @return The price of a magazine as a double number
	 */
	public double searchMagazinePrice(String name, String format){
		for (int count = 0; count < this.numberOfMagazine; count++)
			if (name.equals(magazCollection[count].getMagazineName()) && format.equals(magazCollection[count].getMagazineFormat()))
				return magazCollection[count].getMagazinePrice();
		return 0;
	}

	/**
	 * Converts magazine collection into a string.
	 */
	public String toString (){
		String list = "";
		for (int count = 0; count < this.numberOfMagazine; count++)
			list += (magazCollection[count].getMagazineName() + ", " +
					magazCollection[count].getMagazineFormat() + ", " +
					magazCollection[count].getMagazinePrice() + "\n");
		return list;
		
		
	}
	/**
	 * Returns the number of magazines in the collection
	 * @return the number of magazines.
	 */
	public int getNumberMagazines(){
		return this.numberOfMagazine;
	}
}
