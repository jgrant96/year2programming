		AREA lab_1_Part_3, CODE, READONLY
		ENTRY
		MOV r0,#4	;store number for A
		MOV r1,#7	;store number for B
		MOV r2,#5	;store number for C
		MOV r3,#8	;store number for D
		MOV r4,#9	;store number for E
		MUL r5,r3,r4	;stores in Z, D times E
		SUB r5,r0,r5	;stores in Z, A - D times E
		ADD r5,r1,r5	;stores in Z, A-DxE + B
		ADD r5,r2,r5	;stores in Z, A-DxE + B + C
loop	b	loop
		END