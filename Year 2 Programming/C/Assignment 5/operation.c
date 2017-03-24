#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#include"operation_functions.h"


//include things.
int main (int argc, char *argv[]){


double input1;
double input2;
double input3;
double input4;
struct complex_tag complexNum1;
struct complex_tag complexNum2;
struct complex_tag functionTwo;
struct complex_tag *functionThreeSum;
struct complex_tag *functionThreeDiff;
Complex_type functionOne;

//store command line arguments
if (argc != 5){
	printf("Please only use 4 arguments\n");
	return -1;
}

input1 = atof(argv[1]);

input2 = atof(argv[2]);

input3 = atof(argv[3]);

input4 = atof(argv[4]);

//declare two variables of type complex_tag

complexNum1.real = input1;
complexNum1.imaginary = input2;
complexNum2.real = input3;
complexNum2.imaginary = input4;

//Use the variables in function 1

functionOne = complexMultiplication(complexNum1, complexNum2);

//Use the variables in function 2

imaginaryDivision(&complexNum1, &complexNum2, &functionTwo);

//Use the variables in function 3

sumAndDifference(complexNum1, complexNum2, &functionThreeSum, &functionThreeDiff);
//Print the complex numbers that the user entered.

printf("The entered complex numbers... \n");
printf("Input 1, real: %f, imaginary: %f\n", complexNum1.real , complexNum1.imaginary);
printf("Input 2, real: %f, imaginary: %f\n", complexNum2.real , complexNum2.imaginary);
printf("\n");




//Print the results of the first function
printf("The result of multiplication of complex numbers...\n");
printf("real: %f, imaginary: %f\n", functionOne.real, functionOne.imaginary);
printf("\n");

//Print the reuslts of the second function
printf("The result of the division of two complex numbers...\n");
printf("real: %f, imaginary: %f\n", functionTwo.real , functionTwo.imaginary);
printf("\n");

//Print the results of the third function
printf("The result of the sum and difference function...\n");
printf("The sum of the two numbers: real: %f, imaginary: %f\n", (*functionThreeSum).real, (*functionThreeSum).imaginary);
printf("the difference of the two numbers: real: %f, imaginary: %f\n", (*functionThreeDiff).real, (*functionThreeDiff).imaginary);

//free the malloc 
free(functionThreeSum);
free(functionThreeDiff);


return 0;
}

