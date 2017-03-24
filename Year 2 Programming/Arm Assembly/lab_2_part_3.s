		AREA lab_2_part_3, CODE, READONLY
		ENTRY
		LDR r3, X ;stores the contents of X inside of r3. It does this by using LDR R3,[PC,#0x0008] Which takes the information at memory location of the program counter + 8. The value of X was stored there by the assembler, By the time the command is executed the program counter is already at 8, thus the data is retrieved from position 16.
		LDR r4, =X ;stores the address of X inside of r4. Bascially the same as above using instead LDR R4,[PC,#0x0008] instead. The difference is that the address of X was stored one word after the contents of X in memory. So we move 4 bytes ahead first. We get position 20,
		ADR r5, X ;This instruction is changed to ADD R5,PC,#0x00000000. As it always produces an address by adding or subtracting. Because the program counter is already 20, which is the address of X. Remember the program counter is always 8 in front of what one would think it is, because it advances before the statement is executed.
loop 	B	loop
X		DCD	0x70707070
		END