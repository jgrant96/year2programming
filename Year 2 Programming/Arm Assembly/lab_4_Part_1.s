		AREA lab_4_Part_1, CODE, READONLY
		ENTRY
		MOV r0, #3
		MOV r1, #4
		CMP r0, r1
		BEQ loop
		BGT greaterThan
		SUB r1, r1, r0
		b loop	
greaterThan
		SUB r0, r0, r1	
loop	b	loop
		END