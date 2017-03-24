
public class Dictionary implements DictionaryADT {

	private DictNode[] table;
	private int MAXTABLEVALUE;
	private int numberStored = 0;

	//Inserts a dictionary entry into the dictionary
	//We return a 1 if we encounter a collisions
	// Collisions are dealt with using a linked list.
	// We use a hash function table
	public int insert (DictEntry pair) throws DictionaryException{
		Boolean anyCollision = false;
			int position = hashFunction(pair.getKey(), MAXTABLEVALUE);

			//We check if there's an element in the position. Every first element already stores a node.
			if (table[position] == null)
				table[position] = new DictNode(pair);
			//if there are elements into the array already then we set the first element
			//to the one we want to insert
			// we only insert if we there key does not appear in the array
			else if (find(pair.getKey()) == null)
			{
				DictNode check = new DictNode (pair);
				anyCollision = true;
				check.setNext(table[position]);
				table[position] = check;
			}
			else //If the key is in the array we throw an exception.
				throw (new DictionaryException ("Key is already in the array"));

			numberStored++;
			//Any collision becomes true in the even of a collision
		if (anyCollision == true)
			return 1;
		else
			return 0;
	}

	//We use this to remove an element form the array
	//We use a hash function to get the correct position.
	public void remove (String key) throws DictionaryException {

		//We use two nodes to run through the array
		int position = hashFunction(key,MAXTABLEVALUE);
		DictNode runner = table[position];
		DictNode previous = table[position];
		//If the first element is null then we don't need to continue
		//because it can't be in the table
		//so we just throw a dictionary execption.
		if (runner != null)
		{
			//if we find the key, then we remove it.
			//if the next element doesn't exist we simply set the node to null.
			//if there is an element after it we can't lose that node
			
			if (runner.getElement().getKey().equals(key)){
				if (runner.next() == null)
				{
					runner.setElement(null);
					return;
				}
				else
					table[position] = runner.next();
				numberStored--;
			}
			runner = runner.next();
			
			//The method changes when we are looking at the 
			//non-first node
			//we check if it's null and use the previous instead of table
			//position
			while (runner != null)	
			{
				if (runner.getElement().getKey().equals(key)){
					previous.setNext(runner.next());
					numberStored--;
					return;
				}
				else
				{
					runner = runner.next();
				}
			}
		}
		//If we don't return earlier in the program which is when the removal is successful
		//we throw an exception.
		throw new DictionaryException("Key Not found");
	}

	// we find an entry with a specific string in the table.
	// we use a hash function to find the correct table position
	public DictEntry find (String key){
		//we use a node reference to run down the linked list
		int position = hashFunction(key,MAXTABLEVALUE);
		DictNode runner = table[position];
		//We keep going until we either find the node or reach a null
		while (runner != null)	
		{
			if (runner.getElement().getKey().equals(key)){
				return runner.getElement(); // it's found
			}
			else
			{
				runner = runner.next();
			}
		}
		return null; // it wasn't found
	}

	//Returns the number of elements stored in the table
	public int numElements(){
		return numberStored;
	}

	//Dictionary constructor
	public Dictionary (int maxSize){
		table = new DictNode[maxSize];
		MAXTABLEVALUE = maxSize;
		for (int count = 0; count < table.length; count++)
			table[count]= null;
	}

	//Changes a string into a number from 0 to the max size of the table
	//It does so by changing the individual character in the string
	//to integers through casting, then we multiply them by values
	//to and add them to get a sum.
	private int hashFunction(String key, int tableSize){
		int hash = 0;
		int aModifier = 33;
		hash = ((int) key.charAt(0)) * aModifier^(key.length() - 1);
		for (int count = 1; count < key.length(); count++){
			hash = hash + ( (int)(key.charAt(count))*(aModifier^(key.length() - count)));
		}
		hash = hash % tableSize;
		return hash;	

	}


}
