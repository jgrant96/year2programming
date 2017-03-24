//Justin Grant
//250787131
public class Question7b{
	
	
	 static String ntimefib (int n)
	    {
	    LargeInteger arr[] = new LargeInteger[n+1];
	    int i;
	    if (n == 0)
	    	return "0";
	    if (n == 1)
	    	return "1";
	    for (int count = 0; count < arr.length; count++){
			arr[count] = new LargeInteger();
		}

	    arr[0].setInteger("0");
	    arr[1].setInteger("1");
	     
	    for (i = 2; i <= n; i++)
	    {
	        arr[i] = LargeInteger.sumInt(arr[i-1], arr[i-2]);
	    }
	    return arr[n].getInteger();
	  }
	public static void main (String args[]){
		for (int count = 0; count <= 30; count++){
			System.out.println("F("+ count + "*10): = " + ntimefib(count * 10));
		}
	}
}
