
public class fail {

	public static void main(String[] args) {

		String infixEquation = "a + d * e / c + 2";
		String postfixEquation = ""; 
		ArrayStack<Character>  opStack = new ArrayStack<Character> (); 
		for (int count = 0; count < infixEquation.length(); count++)
		{
			if (infixEquation.charAt(count) == '*'
					|| infixEquation.charAt(count) == '+'
					|| infixEquation.charAt(count) == '/'
					|| infixEquation.charAt(count) == '-')

				if (opStack.isEmpty())
					opStack.push(infixEquation.charAt(count));
				else if (infixEquation.charAt(count) == '*' || infixEquation.charAt(count) == '/')
				{
					if (opStack.peek() == '*' || opStack.peek() == '/')
					
						opStack.push(infixEquation.charAt(count));
				}
				else if (opStack.peek() == '-' || opStack.peek() == '+')
					opStack.push(infixEquation.charAt(count));
				else
				{
					while(!opStack.isEmpty())
						postfixEquation = postfixEquation + opStack.pop();
					opStack.push(infixEquation.charAt(count));
				}
			else
				postfixEquation = postfixEquation + infixEquation.charAt(count);
		}
		while(!opStack.isEmpty())
			postfixEquation = postfixEquation + opStack.pop();
		System.out.println (postfixEquation);


	}

}
