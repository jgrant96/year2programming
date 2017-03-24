//Justin Grant
//250787131
public class LargeInteger {
String integerToStore;

public LargeInteger(String interS){
	integerToStore = interS;
}

public LargeInteger(){
	integerToStore = "";
}
public String getInteger(){
	return integerToStore;
}

public boolean isEmpty(){
	if (integerToStore == null)
		return true;
	else if(integerToStore.equals(""))
		return true;
	else
		return false;
}

public void setInteger(String inter){
	this.integerToStore = inter;
}

public static LargeInteger sumInt (LargeInteger int1, LargeInteger int2){
	int size1 = int1.getInteger().length();
	int size2 = int2.getInteger().length();
	int max;
	String bigInt;
	String smallInt;
	int diff;
	if (size1 >= size2){
		max = size1;
		bigInt = int1.getInteger();
		smallInt = int2.getInteger();
		diff = size1 - size2;
	}
	else{
		max = size2;
		diff = size2 - size1;
		bigInt = int2.getInteger();
		smallInt = int2.getInteger();
	}
	LargeInteger sum = new LargeInteger();
	
	int carryover = 0;
	int number1;
	int number2;
	int midnumber;
	for (int count = max - 1; count >= diff; count--){
			number1 = Character.getNumericValue(bigInt.charAt(count));
			number2 = Character.getNumericValue(smallInt.charAt(count - diff));
			
		midnumber = number1 + number2 + carryover;
		if (midnumber >= 10){
			carryover = 1;
			midnumber = midnumber - 10;
		}
		else
			carryover = 0;
		
		sum.setInteger((("" + midnumber) + sum.getInteger()));
	}
	if (diff - 1 < 0 && carryover >= 1)
		sum.setInteger((carryover + sum.getInteger()));
	if (carryover == 1)
	for (int count = diff - 1; count >= 0; count--){
		int numberToAddOn = Character.getNumericValue(bigInt.charAt(count));
		numberToAddOn = numberToAddOn + carryover;
		if (numberToAddOn >= 10){
			carryover = 1;
			if (count != 0)
			sum.setInteger(("0" + sum.getInteger()));
			else
				sum.setInteger(("10" + sum.getInteger()));
		}
		else{
			carryover = 0;
			sum.setInteger(("" + numberToAddOn + sum.getInteger()));
		}
	}
	
	return sum;
}

public static void main (String args[]){
	LargeInteger int1 = new LargeInteger("209");
	LargeInteger int2 = new LargeInteger("99");
	LargeInteger int3 = LargeInteger.sumInt(int1, int2);
	System.out.println(int3.getInteger());
}
}

