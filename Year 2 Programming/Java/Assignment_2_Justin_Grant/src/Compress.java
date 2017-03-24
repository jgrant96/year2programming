import java.io.*;

public class Compress {

	static BufferedOutputStream out;
	static BufferedInputStream in;

	public static void main (String [] args) throws DictionaryException{
		//variable initialization
		String inputFile = args[0];
		String outputFile = inputFile + ".zzz";
		String currentString;
		int numberOfCollisions = 0;
		char currentCharacter;
		DictEntry currentEntry;
		int minimumCharNumber;
		Dictionary dictionary = new Dictionary (4099);
		
		//We initialize the 255 entries of char
		//We add all of them to the dictionary
		for (int count = 0; count <= 255; count++){
			numberOfCollisions = dictionary.insert(new DictEntry("" +(char) count, count));
		}

		//try because we'll do input commands that give errors in the case the files don't exist.
		try
		{
			//setup the input and output streams
			out = new BufferedOutputStream(new FileOutputStream(outputFile));
			in = new BufferedInputStream(new FileInputStream(inputFile));
			MyOutput outputting = new MyOutput();
			
			
			minimumCharNumber = 256;
			//We read and store the first two values
			currentCharacter = (char) in.read();
			currentString = "" + currentCharacter;
			currentCharacter = (char) in.read();
			
			//We keep going until we reach the end of the input file
			while (currentCharacter != (char) -1){
				//We keep looking until we find a character not in the table
				//The current string will store the string that exists in the table
				//the current character will eventually have the first character than
				//when combined with the first no longer exists in the table.
				while (dictionary.find(currentString+currentCharacter) != null){
					currentString = currentString + (currentCharacter);
					currentCharacter = (char) in.read();
				}
				
				//We create an entry from the current string to output it's code
				currentEntry = dictionary.find(currentString);
				outputting.output(currentEntry.getCode(),out);
				
				//Our new string is the one that was the largest not found in the table
				//We insert it into the table if we can still do insertions
				currentString = currentString + currentCharacter;
				if (dictionary.numElements() < 4096)
				{
				numberOfCollisions = numberOfCollisions + dictionary.insert(new DictEntry(currentString,minimumCharNumber));
				minimumCharNumber++;
				}
				//Now we start again, looking first at the character that was not found in the table.
				//We get the next character
				currentString = ""+ currentCharacter; 
				currentCharacter = (char) in.read();

			}
			//If we stil have any data left we output it.
			currentEntry = dictionary.find(currentString);
			if (currentEntry != null)
			outputting.output(currentEntry.getCode(),out);
			//flushing the rest of the data and closing files
			outputting.flush(out);
			in.close();
			out.close();
			
			//We output how many collisons we get and how often.
			System.out.println("The number of collisions is: " + numberOfCollisions);
			System.out.println("The percentage of insertions that created collisions is: " + (numberOfCollisions * 100.0/dictionary.numElements()) + "%");
			//end of loop;
		}
		//We catch any errors that occur from trying to open and close files.
		catch (IOException e){
			System.out.println("File not found. Quiting program");
		}
		finally {
			try{
				in.close();
				out.close();
			}
			catch (IOException e){
				System.out.println("File not closed properly, quiting program");

			}
			catch (NullPointerException e)
			{
				
			}
		}


	}
}
