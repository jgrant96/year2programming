		AREA lab_1_Part_1, CODE, READONLY
		ENTRY
		MOV r1,#2	;Put 2 in register r1
		MOV r2,#3	;Put 3 in register r2
		MOV r3,#4	;Put 4 in register r3
		MOV r4,#5	;Put 5 in register r4
		MOV r5,#6	;Put 6 in register r5
		ADD r0,r1,r2	;Add r1 to r2 and put the result in r0
		ADD r0,r0,r3	;Add r0 to r3 and put the result in r0
		ADD r0,r0,r4	;Add r0 to r4 and put the result in r0
		ADD r0,r0,r5	;Add r0 to r5 and put the result in r0
loop	b	loop
		END