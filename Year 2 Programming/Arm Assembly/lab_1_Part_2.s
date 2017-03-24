		AREA lab_1_Part_2, CODE, READONLY
		ENTRY
		MOV r0,#5	;Puts 5 in r0
		ADD r1,r0,#3	;Adds 3 and r0 and stores it in r1. It used to be r1,#3,r0
loop 	b	loop
		END