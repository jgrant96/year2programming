//operation_functions.h
//Part a)

struct complex_tag
{
	double real;
	double imaginary;
};

//Part b)
typedef struct{
	double real;
	double imaginary;
} Complex_type;

Complex_type complexMultiplication(struct complex_tag number1, struct complex_tag number2);

int imaginaryDivision(struct complex_tag *complexNum1, struct complex_tag *complexNum2, struct complex_tag *complexNum3);

int sumAndDifference (struct complex_tag operator1, struct complex_tag operator2, struct complex_tag **sumNumber, struct complex_tag **diffNumber);
