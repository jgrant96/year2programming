import java.io.*;

public class RecursionLab{
    
    public static void reversePrint (String inString){
       
		if (inString.length() > 0)		// if string is not empty
		{
		System.out.print(inString.charAt(inString.length() - 1));
		reversePrint(inString.substring(0, inString.length() - 1));// your code goes here
		}
    }

    public static String reverseString (String inString){
    	String resultString = "";
    	if (inString.equals(""))
    		return inString;
    	else
    	{
    	 resultString = (inString.charAt(inString.length() - 1) + "").concat(reverseString(inString.substring(0, inString.length() - 1)));;
    	}
    		return resultString;
    }
    
    public static boolean isPalindrome(String inString)
    {
    	String newString = reverseString(inString);
    	if (newString.equals(inString))
    		return true;
    	else
    		return false;
    }
	    
    public static void main(String[] args){
        String inString = "abcde";

		// test reversePrint
		reversePrint(inString);	
		
		// test reverseString
		String revString = reverseString(inString);
		System.out.println("\n" + revString);
		
		String palindrome = "racecar";
		String nonPalindrome = "driver";
		
		System.out.println(isPalindrome(palindrome));
		System.out.println(isPalindrome(nonPalindrome));
    }
}
