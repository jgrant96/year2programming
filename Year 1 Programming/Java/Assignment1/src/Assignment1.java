import java.util.Stack;
import java.util.*;


public class Assignment1 {


	public static void main(String[] args) {

		
		
		
		InfixtoPostfix mystack = new InfixtoPostfix();
        System.out.println("Type in an infix string followed by key");
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        System.out.println("The Expression you have typed in infix form :\n"+str);
        System.out.println("The Equivalent Postfix Expression is :\n"+mystack.infixToPostfix(str));

        
        
/*		
				
		//testing this gives an error: a-b/c

		System.out.println("Please enter an infix string");

//change to get user input 					num = SimpleInput.getIntNumber("Enter number: ");   } 
		String in = " 6+(4+5)*6";
		//String in = "a+b*c-d";
		String out;
		InfixtoPostfix inToPo = new InfixtoPostfix(in);
		out = inToPo.infixToPostfix();
		System.out.println("This is the infix: " + in);
		System.out.println("This is the postfix: " + out);
		
*/
	}
		
		
}