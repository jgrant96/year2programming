#include<stdio.h>


int main(void)
{
	//Interest payment program
	//will take a loan, interest, payments and number of payments
	//will output the balance after each payment.

	//initializing varibles
	float loan;
	float interest;
	float payment;
	int numberOfPayments;
	int count;
	float currentPayment;

	//getting a loan size > 0
	printf("Enter loan size: ");
	scanf("%f", &loan);
	printf("\n");
	while (loan <= 0)
	{
		printf ("loan must be a number > 0: ");
		scanf ("%f", &loan);
		printf ("\n");
	}
	
	// Getting the yearly interest. > 0
	printf("Enter yearly interest amount (ex '12' for 12%): ");
	scanf("%f", &interest);
	printf("\n");
	while (interest < 0)
	{
		printf ("interest must be a number >= 0: ");
		scanf ("%f", &interest);
		printf ("\n");
	}
	//monthly interest is the same thing divided by 12
	interest = (interest / 100) / 12;

	//Getting the amount paid each month
	printf("Enter monthly payments: ");
	scanf("%f", &payment);
	printf("\n");
	while (payment <= 0)
	{
		printf ("Payment must be a number > 0: ");
		scanf ("%f", &payment);
		printf ("\n");
	}

	//Getting the number of payments made
	printf("Enter number of monthly payments ");
	scanf("%d", &numberOfPayments);
	printf("\n");
	while (numberOfPayments <= 0)
	{
		printf ("Number of payments must be an integer > 0: ");
		scanf ("%d", &numberOfPayments);
		printf ("\n");
	}

	//looping until we've made all the payments or the balance is empty.
	for (count = 1; count <= numberOfPayments && loan > 0; count++)
	{
		//check to see if we overpay the loan
		if (loan + (loan * interest) >= payment)
		{
			currentPayment = payment;
			loan = (loan + (loan * interest)) - payment;
		}
		else
		{
			currentPayment = loan + loan * interest;
			loan = 0;
		}
		//print statistics.
		printf("For payment number: %d \n", count);
		printf("The current balance is now: %.2f \n", loan);
		if (currentPayment != payment)
			printf("The final payment made is: %.2f \n", currentPayment);
	}
	return 1;
}
