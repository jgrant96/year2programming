		AREA lab_1_Part_4, CODE, READONLY
		ENTRY
		LDR r0, A	;store number for A
		LDR r1, B	;store number for B
		LDR r2, C	;store number for C
		LDR r3, D	;store number for D
		LDR r4, E	;store number for E
		LDR r5, Z
		MUL r5, r3, r4	;stores in Z, D times E
		SUB r5, r0, r5	;stores in Z, A - D times E
		ADD r5, r1, r5	;stores in Z, A-DxE + B
		ADD r5, r2, r5	;stores in Z, A-DxE + B + C
		STR r5, Z
loop	b	loop
		
			
		AREA lab_1_Part_4, DATA, READWRITE
A 		DCD  4	;store 4 in A
B 		DCD 12	;store 12 in B
C		DCD -2	;store -2 in C
D 		DCD -5	;store -5 in D
E 		DCD -9	;store -9 in E
Z		DCD 0 	;store 0 in Z
		END