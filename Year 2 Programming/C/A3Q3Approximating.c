#include<stdio.h>

int main(void)
{
	//A3Q3FindingE Program
	//Finds the value of e, adding terms until a specific value
	//is reached. The terms will never be smaller than
	//the value input by the user

	//initializing variables
	double term;
	double denominator;
	double minTerm;
	double e;
	int count;
	int count2;

	//getting the smallest term number that could be added from user.
	printf("Enter max term number: ");
	scanf("%lf", &minTerm);
	printf("\n");
	
	//Making sure the input is a positive number. > 0
	while (minTerm <= 0)
	{
		printf ("The number must be greater than 0 try again: ");
		scanf ("%lf", &minTerm);
		printf ("\n");
	}
	
	//e's initial value is 1
	e = 1;
	count = 1;

	//looping
	do
	{
		//will count up to evaluate a factorial
		count2 = 1;
		denominator = 1.0;
	
		//Will keep going until we completely evaluate the factorial.
		//The current term number is the factorial we need to evaluate.
		while (count2 <= count)
		{
			denominator = denominator * count2;
			count2++;
		}
		term = 1/denominator;

		//Checking to see if we even add this term. We stop if it's too small
		//This is approximating e
		if (term >= minTerm)
		{
			e = e + term;
			count++;
		}
	//we stop once the term we add is smaller than the one given by the user.
	} while (term >= minTerm);
	
	//print it.
	printf("e was approximated to: %.15f \n", e);
	printf("The number of terms used was: %d: ", count);
	printf("\n");
	return 1;
}
