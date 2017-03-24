#include<stdio.h>

int main (void)
{
	//variables
	int size;
	int rowNum;
	int columnNum;
	int count;
	int matrix [13][13] = {0};
	int count1;
	int count2;

	

	//prompt for value that is between 1-13 inclusively
	printf("Enter size of magic square: ");
	scanf("%d", &size);
	while(size < 0 || size > 13 || size % 2 == 0)
	{
		printf("Invalid size, try again: ");
		scanf("%d", &size);
	}
	
	//initialize the starting position
	rowNum = 0;
	columnNum = size/2;
	count = 1;
	count1 = 0;
	count2 = 0;
	

	//We're looping until we've placed numbers in every position, so until count == size squared
	while (count <= size * size)
	{
		//if we can place a number in our present position we do so (the position in the matrix is 0)
		//We decrease our row count (going up) and increase our column count (going right)
		//We make sure to cycle to the opposite side of the matrix if we go past 0 or size
		if ((*((* (matrix + rowNum)) + columnNum)) == 0){
			(*((* (matrix + rowNum)) + columnNum)) = count;
			rowNum--;
			columnNum++;
			if (rowNum < 0)
				rowNum = size - 1;
			if (columnNum >= size)
				columnNum = 0;
			count++;
		}
		//If we can't place a number there we move downward
		//making sure we loop properly
		else
		{
			rowNum++;
			if (rowNum >= size)
				rowNum = 0;
			rowNum++;
			columnNum--;
			if (rowNum >= size)
				rowNum = 0;
			if (columnNum < 0)
				columnNum = size - 1;
		}
	}
		
	//We print the magic square.
	//loop the entire array and print every position
	while(count1 < size)
	{	
		while(count2 < size)
		{
			printf("%3d   ",*((*(matrix + count1)) + count2));
			count2++;
		}
		count2 = 0;
		printf("\n");
		count1++;
	}

	return 0;
}
