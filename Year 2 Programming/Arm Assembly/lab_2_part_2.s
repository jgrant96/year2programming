		AREA lab_2_part_2, CODE, READONLY
		ENTRY
		LDR r1, [r0] ; This one specifies that the operand is in the address dictated by the contents of r0. The inside of r0 is 0, thus it looks at position 0 in the program which has the value E5901000 stored in it, because at that position we had our LDR command, and that is the command in machine language.
		LDR r2, = 0x12345678 ; This one is a bit different because of the psuedo operation. Instead of just storing the number in r2, the assembler stores 0x12345678 in memory, in a literal pool at the end of the code. Then it generates the R2,[PC,#0x0004] which is a relative position from the program counter, where it finds the number in the literal pool and uses it.
		LDR r3, = 0x12 ; This one instead uses the MOV command because the number is small. It just uses MOV R3,#0x00000012
loop 	b	loop
		END