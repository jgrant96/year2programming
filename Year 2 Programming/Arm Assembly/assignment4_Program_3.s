		AREA assignment4_Program_3, CODE, READWRITE
		ENTRY
; ----START OF MAIN-----		
		MOV r0, #5 ;Store any value in r0
		ADR r13, stackP
		BL equationSubroutine ;Activate the subroutine
		MOV r1, r0, LSL #1
loop	b	loop
; ----END OF MAIN-----

; ----START OF SUBROUTINE-----
equationSubroutine
		STMFA r13!, {r1-r4}
		
		LDR r1, A; initialize A
		LDR r2, B; initialize B
		LDR r3, C; initialize C
		LDR r4, D; initialize D
		MUL r1, r2, r1 ;(A times x)
		ADD r1, r1, r2 ; (A*X + B)
		MUL r0, r1, r0 ;X(A*X + B)
		ADD r0, r0, r3 ; (A*X^2 + B*X) + C
		CMP r0, r4 ;check if r0 is greater than d
		MOVLT r0, r4 ;There is clipping so move D into r0
		LDMFA r13!, {r1-r4} ;restore the other registers to their original values
		MOV PC, LR ;go back to the program
; ----END OF SUBROUTINE------


; ----START OF DATA------
A		DCD 5
B		DCD 7
C		DCD 12
D		DCD 200
	
		SPACE 0x64
stackP 	DCD	0x00
; ----END OF DATA-----
		
		END
; ----END OF PROGRAM------