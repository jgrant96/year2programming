		AREA lab_2_part_4a, CODE, READONLY
		ENTRY
		LDR r0, = 0x12345678 ;The program changes this psuedo instruction into LDR R0,[PC]. This is the first instruction so PC will be 8 by the time it is executed. The literal 12345678 waws stored in memory locaiton 8
loop	b	loop
		AREA lab_2_part_4a, READONLY ;This is not written in the program at all
X		DCD	0x70707070 ;Because we have a new segment of code, the DCD data will be placed after all literal/literal pools created by the previous segment. This is actually stored in location C. while the 12345678 is in 8.
		END
