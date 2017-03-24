#include<stdio.h>
#include<math.h>
#include<time.h>
#include<stdlib.h>

int main (void)
{
	//Program to approximate pi
	//Assignment 4, program 1

	
	//variables
	int approximation;
	int inside;
	int devcount;
	long count;
	double x;
	double y;
	double ratio;
	double totalRatio = 0;
	double mean;
	double totalRatioSquared = 0;
	double deviation;

	srand((unsigned)time(NULL));

	//prompt the user
	printf("Enter the number of times to ");
	printf("generate values to approximate pi: ");
	scanf("%d", &approximation);
	while(approximation <= 0){
		printf("Wrong value, try again: ");
		scanf("%d", &approximation);
	}
		
	

	//We loop ten times around to calculate the mean and standard deviation of our results.
	for (devcount = 0; devcount < 10; devcount++)
	{
		inside = 0;
		
		//We loop as many times as the user input earlier to approximate the ratio.
		for (count = 0; count < approximation; count++)
		{	
			
			//we need a random number between 0 and 1 for x and y
			//we find the probability that it is inside a quarter circle.
			
			x = (double)((double)rand() / RAND_MAX);
			y = (double)((double)rand() / RAND_MAX);
			
			if ((x*x + y*y) <= 1.0)
				inside++;
		}
	
		//The ration uses the number of times we find a value inside of the circle of 1 unit radius.
		ratio =  ((double)inside / approximation);
		ratio = ratio * 4;
		printf("Ratio %d, is: %f\n", devcount, ratio);

		//totalRatio will be used to calculate mean and standard deviation
		totalRatio = totalRatio + ratio;
		totalRatioSquared = totalRatioSquared + ratio*ratio;
	}

	//Mean is 
	mean = totalRatio / 10;
	//deviation is
	totalRatio = ((totalRatioSquared/10 - (mean * mean)));
	if (totalRatio >= 0)
		deviation = sqrt(totalRatio);
	else
		deviation = sqrt(totalRatio * -1);
	
	printf("Mean is: %f\n", mean);
	printf("Standard Deviation is: %f\n", deviation);

	return 0;
}
	

