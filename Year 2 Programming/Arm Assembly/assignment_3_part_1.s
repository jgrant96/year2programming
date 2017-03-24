		AREA assignment_3_part_1, CODE, READONLY
		ENTRY
		ADR r1, UPC ;r1 is pointing to the first digit in the UPC
Sumloop	
		ADD r2, #1; Loop counter increases. We start at 1
		LDRB r3, [r1], #1 ;r3 takes the value pointed at by r1
		SUB r3, r3, #0x30 ;Since the numbers are in ascii we need to convert into integers. 0x30 is 0 in hex
		ADD r4, r3 ;The first number is added to the first sum, aka r4
		LDRB r3, [r1], #1 ;r3 takes the second value pointed at by r1
		SUB r3, r3, #0x30 ;The numbers are in ascii code and we need to convert again
		ADD r5, r3 ; The even number is summed to the second sum. aka r5
		CMP r2, #5 ; We check if we're at the 6th run through the loop
		BNE	Sumloop ;If we aren't we go to the beginning
		
		LDRB r3, [r1], #1 ;store the second last UPC digit
		SUB r3, r3, #0x30 ;add it to the r3 as an integer instead of ascii
		ADD r4, r3 ;add the digit to the first sum
		LDRB r6, [r1] ;Store the final checksum digit
		SUB r6, r6, #0x30 ;Convert the final checksum digit to an integer
		
		ADD r4, r4, LSL #1 ;Multiply the first number by 3
		ADD r5, r4; Add the first sum to the second sum
		SUB r5, #1; Subtract 1 from the sum to prepare the checksum

LessTen
		SUB r5, #10; Keep subtracting r4 by 10 until it is less than 10
		CMP r5, #10; Compare r4 and 10
		BGE LessTen; Go back and subtract 10 again, until it's less than 10
		
		RSB r5, r5, #9; Finally subtract the checksum from 9
		CMP r5, r6; Is it the same value as we had in r6, for the checksum
		BNE Failed; If they are not equal then the program failed so branch to the fail spot
		MOV r0, #1; We just continue if they were, equal. r0 is changed to 1
		b loop ;We succeeded so we move to the end of the program

Failed
		MOV r0, #2; The checksum failed so 2 is placed in r1
		
loop	b	loop
UPC		DCB	"013800150738"	;UPC string
		END