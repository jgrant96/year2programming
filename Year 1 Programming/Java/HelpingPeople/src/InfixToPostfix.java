
public class InfixToPostfix {

	public static void main(String[] args) {
		Boolean check;
		String infixEquation = "a + d * e ;
		String postfixEquation = ""; 
		ArrayStack<Character>  opStack = new ArrayStack<Character> (); 
		for (int count = 0; count < infixEquation.length(); count++)
		{
			check = true;
			if (infixEquation.charAt(count) == '*'
					|| infixEquation.charAt(count) == '+'
					|| infixEquation.charAt(count) == '/'
					|| infixEquation.charAt(count) == '-')

				if (opStack.isEmpty())
					opStack.push(infixEquation.charAt(count));
				else if (infixEquation.charAt(count) == '*' || infixEquation.charAt(count) == '/')
				{
					if (opStack.peek() == '*' || opStack.peek() == '/')
					{
						postfixEquation = postfixEquation + opStack.pop();
						opStack.push(infixEquation.charAt(count));
					}
					else
						opStack.push(infixEquation.charAt(count));
				}
				else if (opStack.peek() == '-' || opStack.peek() == '+')
					opStack.push(infixEquation.charAt(count));
				else
				{
					while(!opStack.isEmpty() && check)
						if (opStack.peek() == '*' || opStack.peek() == '/')
						{
							postfixEquation = postfixEquation + opStack.pop();
							opStack.push(infixEquation.charAt(count));
						}
						else 
							check = false;
				}
			else
				postfixEquation = postfixEquation + infixEquation.charAt(count);
		}
		while(!opStack.isEmpty())
			postfixEquation = postfixEquation + opStack.pop();
		System.out.println (postfixEquation);


	}

}
