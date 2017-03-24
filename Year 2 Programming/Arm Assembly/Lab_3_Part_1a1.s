		AREA Lab_3_Part_1a1, CODE, READONLY
		ENTRY
Start	ADR r0,List -4 ;register r0 points to List
		MOV r1,#5 ;initialize loop counter in r1 to 5
		MOV r2,#0 ;clear the sum in r2
Loop	LDR r3,[r0,#4]! ;copy the element pointed at by r0 to r3
		ADD r2,r2,r3 ;add the element to the running total
		SUBS r1,r1,#1 ;decrement to the loop counter
		BNE Loop ;repeat until all elements added
Endless	B Endless ;infinite loop

List	DCD 3,4,3,6,7  ;the numbers to be added together
					;each one is 4 bytes (20 bytes in total)
		END 