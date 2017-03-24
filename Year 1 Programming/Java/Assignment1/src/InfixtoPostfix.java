import java.util.*; //import statement

/**
 * @author: Vivian Lam
 * CS 1027 Assignment 1
 * class to convert a string from infix to postfix notation
 *
 * method to convert the string to postfix 
 * @param c to store the current value (character) of the input string
 * @return output variable (the postfix string)
 */
public class InfixtoPostfix extends Stack {

	public String infixToPostfix(String input) {
		String output = " ";						           //output string to return the end result

		for (int index = 0; index < input.length(); index++) { // scan the Infix string from left to right
			char c = input.charAt(index);           		   //creates a char variable c to store the current character of the input string

			// check parenthesis
		    if (c == '(') {                                    //if the current character is a left bracket
				push(c);                                       //push it to the stack
			}
			else if (c == ')'){                               		 //if the current character is a right bracket
				char topStack = (char) peek();                 		 //variable topStack to store the top of the stack
				while (topStack != '(' && !isEmpty()) {        		 //While topStack is not left brace AND stack is not empty
					output = output + topStack;          		//add topStack to postfix string
					pop();//pop the stack
					topStack = (char)peek();                    //setTopstack to store the new top of the stack
				}
				pop();//pop the stack
			}
			else if (c == '+' || c == '-') {				//if the current character is a + or -	
				if (isEmpty()) {							//if the stack is empty

					push(c);								//push it to the stack
				}
				else {
					char topStack = (char) peek();           						//char variable topStack used to store whatever's at the top of the stack
					while (!isEmpty() || topStack != '(' || topStack != ')') {		//while the stack is not empty and the top isn't a bracket
						pop();														//pop the stack
						output = output + topStack;                                 //at topStack to the output postfix string
					}
					push(c);                                                        //push the current character to the stack
				}
			}

			else if (c == '*' || c == '/') {                                        //if the current character is a * or /
				if (isEmpty()) {
					push(c);
				}
				else {
					char topStack = (char) peek();
					while (!isEmpty() && topStack != '+' && topStack != '-') {       //while the stack isn't empty and topStack is not + or -
						pop();														 //pop the stack
						output = output + topStack;									 //add topStack to the outpu string
					}
					push(c);														 //push the current character to the stack
				}
			}
			else {
				output = output + c;									//add the current character to the output
			}
		}//end of for loop
		
			while (!(isEmpty())) {										//while the stack is not empty
				char topStack = (char) peek();
				if (topStack != '(') {									//if topStack is not (
					pop();												//pop the stack
					output = output + topStack;							//add topstack to the output
				}
			}

		return output;													//returns the output postfix string
	}
	
	
	
	/**
	 * main method: prompts user to enter a string in infix notation, calls the infix to postfix method and outputs the result
	 */
	
	public static void main(String[] args) {		
	
		InfixtoPostfix mystack = new InfixtoPostfix();						//creates a new InfixtoPostfix object
        System.out.println("Type in an infix string followed by key");		//prints out a statement asking the user to enter a string
        Scanner scan = new Scanner(System.in);								//creates a new scanner
        String str = scan.next();											//creates a string variable str
        
        //prints out the string in infix form, and then prints out the string in postfix form (calls the method)
        System.out.println("The Expression you have typed in infix form :\n"+str);
        System.out.println("The Equivalent Postfix Expression is :\n"+mystack.infixToPostfix(str));
        
	}
		
	
}
