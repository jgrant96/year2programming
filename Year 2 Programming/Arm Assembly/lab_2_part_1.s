		AREA lab_2_part_1, CODE, READONLY
		ENTRY
		MOV r0, pc ;I learned from this that 8 was placed in r0 instead of 0, even though this was the first statement.
loop 	b	loop
		END