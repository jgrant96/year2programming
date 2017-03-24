import java.io.*;

public class ExceptionExample11 {

   public static void main (String[] args) throws Exception {

      /* 
         - this handles the NumberFormatException

      */
Boolean legalInput = false;
      BufferedReader keyboard=
         new BufferedReader (new InputStreamReader(System.in),1);

      while (!legalInput){
    	  legalInput = true;
      System.out.print("Enter an integer: ");
      String userTyped = keyboard.readLine();

      try {
         int value = Integer.parseInt(userTyped);
      }
      catch (NumberFormatException e) {
    	  legalInput = false;
         System.out.println("Hey, " + e.getMessage() + " is not an integer!");
      }
      }
   }
}