import java.util.*;

/**
 * Reads magazine and customrer purchase information from as text file
 * Outputs and formats the information while adding it to a magazine collection object.
 * @author Justin Grant 250787131
 * 
 */
public class Main {


public static void main(String[] args) {
// Local variables
StringTokenizer tokenizer;
Double magazinePrice;
String magazineName;
String magazineFormat;
Integer customerNumber;
String  customerFirstName;
String  customerLastName;
Magazine currentMag;

Integer numberOfMagazines;

// Directories of customer information and magazine information
InStringFile magazineReader=new InStringFile("C:/Users/Justin/Documents/Comsci/CS1027/magazine.txt");
InStringFile customerReader=new InStringFile("/C:/Users/Justin/Documents/Comsci/CS1027/customer.txt");


MagazineCollection magCollect = new MagazineCollection();

System.out.format("%n%n%40s%n-----------------------------------------%n",
                  "PooPoo Magazines Subscription Information");
Integer magCt=0;
Integer custCt=0;
Integer numTokens;
Double customerBill=0.0;

//Reads the magazine information and adds magazines to MagazineCollection object, magCollect
do {
magCt++;
String magazineLine = magazineReader.read();
tokenizer=new StringTokenizer(magazineLine);
magazineName=tokenizer.nextToken();
magazineFormat=tokenizer.nextToken();
magazinePrice=Double.parseDouble(tokenizer.nextToken());


currentMag = new Magazine (magazineName,magazineFormat,magazinePrice);
magCollect.addMagazine(currentMag);

//Output Magazine information
System.out.format("%3d%27s %10s %7.2f %n",magCt,
                  currentMag.getMagazineName(),
                  currentMag.getMagazineFormat(),
                  currentMag.getMagazinePrice());

} while (!magazineReader.endOfFile());

// Uses magazineCollection's getter method to get the number of Magazines and outputs the number of magzines
numberOfMagazines = magCollect.getNumberMagazines();

System.out.format("%n%d magazines added to Magazine object%n",numberOfMagazines);
magazineReader.close();

// Uses magazineCollection's toString to print out the magazineCollection   
System.out.println( "\n" + magCollect.toString());

//reads the customer's information and outputs their bills
System.out.format("%n%nPooPoo Customer Bills%n%n");
do {
String customerLine = customerReader.read();
tokenizer=new StringTokenizer(customerLine);
numTokens=tokenizer.countTokens();

if(numTokens==3) {
custCt++;
customerFirstName=tokenizer.nextToken();
customerLastName=tokenizer.nextToken();
customerNumber=Integer.parseInt(tokenizer.nextToken());
System.out.format("---------------------------------------%n");
System.out.format("%n%3d%10s %10s %d %n",custCt,customerFirstName,customerLastName,customerNumber);
}
else
if(numTokens==2) {
magazineName=tokenizer.nextToken();
magazineFormat=tokenizer.nextToken();
// Given the magazineName and magazineFormat uses magazineCollection's
// searchMagazinePrice method to get magazinePrice.
magazinePrice = magCollect.searchMagazinePrice(magazineName, magazineFormat);

customerBill+=magazinePrice;
System.out.format("%20s %10s %7.2f %n",magazineName,magazineFormat,magazinePrice);
} 
else
if(numTokens==0) {
System.out.format("Total bill: %7.2f%n%n",customerBill);
// To prepares for next customer
customerBill=0.0;
}

} while (!customerReader.endOfFile());
customerReader.close();
System.out.format("%d customers processed%n",custCt);
}

}
