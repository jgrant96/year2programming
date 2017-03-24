#include<stdio.h>

void pay_amount(int dollars, int *twenties, int *tens, int *fives, int *toonies, int *loonies);

int main (void)
{
	//A4Q3PayWithCoins

	//initializing
	int dollarEntry;
	int a, b, c, d, e;
	int *twenties, *tens, *fives, *toonies, *loonies;
	twenties = &a;
	tens = &b;
	fives = &c;
	toonies = &d;
	loonies = &e;
	
	//prompt
	printf("Enter a dollar which amount: ");
	scanf("%d", &dollarEntry);
	while (dollarEntry <0){
		printf("Wrong entry, Try again: ");
		scanf("%d", &dollarEntry);
	}

	//calculate the values
	pay_amount(dollarEntry, twenties, tens, fives, toonies, loonies);


	//print the values
	printf("The number of twenties is %d\n", *twenties);
	printf("The number of tens is %d\n", *tens);
	printf("The number of fives is %d\n", *fives);
	printf("The number of toonies is %d\n", *toonies);
	printf("The number of loonies %d\n", *loonies);

	//end
	return 0;
}
/* pay amount function
works by finding how much a bill (ie 20) will fit into how much is left to
pay. We subtract how much can fit in evenly from how much we need to pay
then we move to the smaller one. The order we try is 20 > 10 > 5 > etc

We take pointers as input and change the value of their contents
returns nothing
*/
void pay_amount(int dollars, int *twenties, int *tens, int *fives, int *toonies, int *loonies)
{
	int multiple;
	multiple = dollars / 20;
	*twenties = multiple;
	if (multiple > 0)
		dollars = dollars - multiple*20;
	
	multiple = dollars / 10;
	*tens = multiple;
	if (multiple > 0)
		dollars = dollars - multiple*10;

	multiple = dollars / 5;
	*fives = multiple;
	if (multiple > 0)
		dollars = dollars - multiple*5;
	
	multiple = dollars / 2;
	*toonies = multiple;
	if (multiple > 0)
		dollars = dollars - multiple*2;

	multiple = dollars;
	*loonies = multiple;
	if (multiple > 0)
		dollars = dollars - multiple;
}
