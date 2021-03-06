import java.io.*;
import java.util.Iterator;
/**
 * PhoneTest.java:
 * This class creates an Ordered List of Phone objects.
 * @author CS1027 for Lab
 */

public class PhoneTest {

	public static void main (String[] args) throws Exception {

		// get the filename from the user

		BufferedReader keyboard = new BufferedReader
				(new InputStreamReader(System.in),1);       
		System.out.println("Enter name of the input file: ");
		String filename= keyboard.readLine();

		// create object that controls file reading and opens the file

		InStringFile reader = new InStringFile(filename);
		System.out.println("\nReading from file " + filename + "\n");

		// your code to create (empty) ordered list here
		ArrayOrderedList<Phone> phoneList = new ArrayOrderedList<Phone>();


		// read data from file two lines at a time (name and phone number)

		String name, phone;
		do {
			name = (reader.read());
			phone = (reader.read());

			phoneList.add (new Phone(name,phone));
			// your code to add the entry to your ordered list here

			Iterator<Phone> iter = phoneList.iterator();
			while (iter.hasNext()){
				System.out.println(iter.next().getName());
			}
			iter = phoneList.iterator();
			while (iter.hasNext()){
				System.out.println(iter.next().getPhone());
			}

		} while (!reader.endOfFile()); 

		System.out.println("Here is my phone book:");

		// your code to print the ordered list here

		System.out.println(phoneList.toString());
		System.out.println("We'll use the new toString method");
		System.out.println(phoneList.toString2());


		// close file

		reader.close();
		System.out.println("\nFile " + filename + " is closed.");      

	}
}
