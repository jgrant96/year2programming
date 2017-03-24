/**
 * Class that represents a magazine
 * contains the name, format and price of the magazine and getter methods to access them.
 * @author Justin Grant 250787131
 *
 */
public class Magazine {

	/**
	 * Name of the Magazine
	 */
	private String magazineName;

	/** 
	 * Format of magazine. Hard cover or soft cover
	 */
	private String magazineFormat;

	/**
	 * Price of magazine.
	 */
	private double magazinePrice;

	//Accessor methods

	/**
	 * Accessor method to get name of magazine
	 * @return Name of Magazine
	 */
	public String getMagazineName(){
		if (this.magazineName != null)
			return this.magazineName;
		else
			return "";
	}

	/**
	 * Accessor method to get the format of magazine
	 * @return format of magazine in String form
	 */
	public String getMagazineFormat(){
		if (this.magazineFormat != null)
			return this.magazineFormat;
		else
			return"";
	}

	/**
	 * Accessor method to get the price of magazine
	 * @return price in double format
	 */
	public double getMagazinePrice(){
		return magazinePrice;
	}

	/**
	 * Constructor method to create Magazine class with name, format and price
	 * @param name Name of magazine
	 * @param format Format of magazine
	 * @param price Price of magazine
	 */
	public Magazine(String name, String format, double price)
	{
		this.magazineName = name;
		this.magazineFormat = format;
		this.magazinePrice = price;
	}
}
