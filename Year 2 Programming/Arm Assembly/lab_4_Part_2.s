		AREA lab_4_Part_2, CODE, READONLY
		ENTRY
		MOV r0, #5
		MOV r1, #4
		CMP r0, r1
		SUBLT r1, r1, r0
		SUBGT r0, r0, r1	
loop	b	loop
		END