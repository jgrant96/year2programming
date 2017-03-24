		AREA lab_4_Part_3, CODE, READONLY
		ENTRY
		ADDPL r2, r1, r0, LSL #5
		RSBHI r4, r3, #0x990
		BICS r6, r5, #0xFF00
loop	b	loop
		END