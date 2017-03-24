public class DictEntry {
private String key;
private int code;
	//Constructor which returns a new DictEntry, with the specified key and code.
	public DictEntry (String key, int code){
	this.key = key;
	this.code = code;
	}
	

	//Returns the key in DictEntry
	public String getKey(){
		return key;
	}
	// Returns the code in DictEntry.
	public int getCode(){
		return code;
	}

}
