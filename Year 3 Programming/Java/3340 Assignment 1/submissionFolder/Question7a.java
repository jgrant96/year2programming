//Justin Grant
//250787131
public class Question7a {

	public static int fibonacciPrint(int i){
		if(i == 0)
			return 0;
		else if(i == 1)
			return 1;
		else
			return (fibonacciPrint(i - 1) + fibonacciPrint(i - 2));
	}
	
	public static void main(String args[]){
		for (int count = 0; count <= 7; count++){
			System.out.println ("F("+ count + "*5): = " + fibonacciPrint(count *5 ));
		}
		
	}
}

