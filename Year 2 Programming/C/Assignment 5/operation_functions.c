#include"operation_functions.h"

//operation functions program


//Multiplies two complex numbers together.
//returns a Complex_type representation
Complex_type complexMultiplication(struct complex_tag number1, struct complex_tag number2)
{
	//create real and imaginary parts
	double newReal;
	double newImaginary;
	Complex_type returnNum;

	//Evaluating the real and imaginary parts
	newReal = ((number1.real * number2.real) - (number1.imaginary * number2.imaginary));
	newImaginary = ((number2.real * number1.imaginary) + (number1.real * number2.imaginary));

	//Assigning the real and imaginary parts to numbers.
	returnNum.real = newReal;
	returnNum.imaginary = newImaginary;

	//return the complex type.
	return returnNum;
}

//Divides one complex number by another.
//the values are pointed to by complexNum3
int imaginaryDivision(struct complex_tag *complexNum1, struct complex_tag *complexNum2, struct complex_tag *complexNum3)
{

	double newReal;
	double newImaginary;
	if (((*complexNum2).real * (*complexNum2).real) + ((*complexNum2).imaginary * (*complexNum2).imaginary) == 0)
		return -2;
	else{
		newReal = (((*complexNum1).real) * ((*complexNum2).real)) + (((*complexNum1).imaginary) * ((*complexNum2).imaginary));
		newReal = (newReal) / (  (((*complexNum2).real) * ((*complexNum2).real))  + (((*complexNum2).imaginary) * ((*complexNum2).imaginary)));
	
		newImaginary = (((*complexNum2).real) * ((*complexNum1).imaginary) - ((*complexNum1).real) * ((*complexNum2).imaginary));
		newImaginary = newImaginary / ( (((*complexNum2).real) * ((*complexNum2).real)) + (((*complexNum2).imaginary) *  ((*complexNum2).imaginary)));
		
		(*complexNum3).real = newReal;
		(*complexNum3).imaginary = newImaginary;
		return 0;
	}

}

//Sums and finds the difference of two complex numbers
//they are placed in two pointers
int sumAndDifference (struct complex_tag operator1, struct complex_tag operator2, struct complex_tag **sumNumber, struct complex_tag **diffNumber){

	double sumReal;
	double sumImaginary;
	double diffReal;
	double diffImaginary;
	struct complex_tag *sum;
	struct complex_tag *diff;

	sumReal = operator1.real + operator2.real;
	sumImaginary = operator1.imaginary + operator2.imaginary;
	diffReal = operator1.real - operator2.real;
	diffImaginary = operator1.imaginary - operator2.imaginary;

	if ( (sum = malloc(sizeof(struct complex_tag))) && (diff = malloc(sizeof(struct complex_tag)))){
		(*sum).real = sumReal;
		(*sum).imaginary = sumImaginary;
		(*diff).real = diffReal;
		(*diff).imaginary = diffImaginary;
	
		*sumNumber = sum;
		*diffNumber = diff;

	}
	else{
		printf("unable to allocate memory for one or more pointers");
		return -1;
	}
	return 0;
}
