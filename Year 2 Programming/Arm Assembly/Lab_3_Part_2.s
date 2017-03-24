		AREA Lab_3_Part_2, CODE, READONLY
		ENTRY
		MOV r0, #2
		MOV r1, #2
		MOV r2, #1
		MOV r3, #3
		
		TST r0, #1
		BEQ Yes
		
		MOV r1, r1, LSL #8
		MOV r2, r2, LSR #8
		b Done
		
Yes		MOV	r1, r1, LSL r0
		MOV r2, r2, LSR r0

Done
ii		ADD r4, r1, r2
		ADD r4, r4, r3, LSR #4
		

loop	b	loop
		END