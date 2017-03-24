
;Main-Program---------------------------------------------------------------------------------------------------------------------
		AREA assignment5, CODE, READONLY
n		EQU	3
x		EQU 5
		ENTRY
Main	ADR	sp, stack			;Set up the stack pointer
		MOV r0, #x				;Load the variable X, in X^N.
		STR r0, [sp,#-4]!		;Store the first parameter X onto the stack.
		MOV r0, #n				;Load the variable N, in X^N
		STR r0, [sp,#-4]!		;Store the second parameter N onto the stack.
		SUB sp, sp, #4			;reserve a place in memory for the return value.
		BL Power				;go to the power function, using the previous parameters and return value.
		LDR	r0,[sp],#4			;retrieve the returned value from the stack pointer.
		ADR r1, result			;retrieve the address for the result.
		STR r0, [r1]			;store the result in the position in memory labeled by the word 'result'		
loop	b	loop				;End of the main program.



;Power-Function----------------------------------------------------------------------------------------------------------------
		AREA assignment5, CODE, READONLY
Power	STMFD sp!,{r0-r4, fp, lr}		;Store the current registers, so they are not overwritten.
		MOV fp, sp				;Set the frame pointer for this call.
		SUB sp, sp, #4			;Set up space for the local variable y.
		LDR r0, [fp, #0x24]		;grab the first parameter X from the stack
		LDR r1,[fp, #0x20]		;grab the second parameter N from the stack
		
								;If (n==0) {
		CMP r1, #0				;Check to see if n is equal to zero.
		MOVEQ r2, #1			;store 1 into a value to be returned.
		STREQ r2, [fp, #0x1C]	;store the value into the return address, pushing it onto the stack
		BLEQ	ret					;branch to the return value area
								;} else if (n is odd)
		TST r1, #1				;check to see if the value is odd or even
		SUBNE r4, r1, #1		;Setting up the n parameter that will be called recusively in the next call.
		STRNE r0, [sp, #-4]!	;store the x value into the stack, because we'll recursively access it next time.
		STRNE r4, [sp, #-4]!	;store n-1 into the stack, we'll recursively access it next time.
		SUBNE sp, sp, #4		;set up a place for values to be returned to.
		BLNE	Power				;invoke power(x, n-1)
		TST r1, #1				;is it still odd? Just in case the conditional flags were changed.
		LDRNE r2,[sp], #4		;load the result from the function and put it in r2. Pop the stack value.
		ADDNE sp, sp, #8		;pop the parameters from the stack as well
		MULNE r2, r0, r2		;multiple the result of power(x, n-1) * x
		STRNE r2, [fp, #0x1C]	;store the value into the stack, we are returning this value.
		BLNE ret					;We return from the method call
								;} else (n is even, but not zero)
		
		MOV r1, r1, LSR #1		;take n/2 and place it in the same position
		STR r0, [sp, #-4]!		;Store the x value into the stack, because we're going to recusively call this function using this parameter
		STR r1, [sp, #-4]!		;Store the n value into the stack, we're recursively calling this fucntion using this parameter.
		SUB sp, sp, #4			;set up a place for the value to be returned to
		BL Power					;invoke power(x, n/2)
		LDR r2,[sp], #4			;get the result of power (x, n/2)
		ADD sp, sp, #8			;get rid of the previous two parameters
		STR r2,[sp]				;Store the value into the local variable y
		MOV r3, r2	 			;Store the value y into r3
		MUL r2, r3, r2			;store r2*r2 in r2, basically y*y
		STR r2, [fp, #0x1C] 	;store the return value into the stack
		
ret		MOV sp, fp				;collapse the stack frame.
		LDMFD sp!, {r0-r4, fp, pc}	;load all of the registers back to their original state, and return to the method call

;Stack-and-other-data-Storing-Area----------------------------------------------------------------------------------------------------------------
		AREA assignment5, DATA, READWRITE
result	DCD	0x00				;The final result
		SPACE 0x200				;Space allocated for the stack
stack	DCD	0x00				;Pointer for the stack
	
;----------------------------------------------------------------------------------------------------------------------------
		END