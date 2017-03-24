		AREA lab_2_part_4b, CODE, READONLY
		ENTRY
		LDR r0, = 0x12345678 ;Instruction is R0,[PC,#0x0004] The assembler has stored the number 12345678 in position PC + 4 in memory. PC in this case is 8
loop	b	loop
X		DCD	0x70707070 ;Because this is part of the same code segment the assembler allocates space for this before creating a literal pool. 12345678 comes after 70707070 in memory this time.
		END 