		AREA assignment_3_part_2, CODE, READONLY
		ENTRY
		ADR r1, String ;Points to the beginning of the string
		ADR r2, EoS ;Points to the end of the string
		SUB r3, r2, r1 ;Creates a counter that shows the characters the string contains
		LDRB r5, [r2], #-1	;Make r5 point to the actual last character in the string
NextChar
		SUB r3, r3, #1 ;Decrease the characters left to read counter
		CMP r3, #-1 ;This is here because sometimes the last character isn't an ascii code and it gets stuck in an endless loop
		BEQ Success ;This will branch once we've check every single ascii. And returns success.
		LDRB r4, [r1], #1 ;Load a character at the beginning of the string and go to the next character
		
Recheck			;Here we loop until we get an ascii code that works, for the first character
		CMP r4, #65 ;Compare the character got to the letter 'A' ascii
		BLT NextChar ;The number is too small to be a letter, so we get the next one.
		CMP r4, #90 ;Compare the character got to the letter 'Z' ascii
		BLE Continue ;The number is fine to use, we'll continue.
		SUB r4, r4, #32; Try to convert a lowercase letter to upper case
		b Recheck
Continue

		LDRB r5, [r2], #-1 ;Load a character at the end of the string and go to the previous character
Recheck2		;Here we loop until we get an ascii code that works, for the second character
		CMP r5, #65	;Compare the character got to the letter 'A' ascii
		BLT	Continue ;The number is too small to be a letter, so we get the next one.
		CMP r5, #90	;Compare the character got to the letter 'Z' ascii
		BLE Continue2	;The number is fine to use so we'll continue.
		SUB	r5, r5, #32	;Try to convert a lowercase letter to upper case
		b Recheck2
Continue2				;The characters got are both legal ascii characters
		
		CMP r4, r5 ;Compare the two characters
		BNE	Fail ;If they aren't the same we leave
		
		CMP	r3,	#0 ;If we've reached the last character then we exit
		BNE	NextChar ;if we haven't then we go back to the beginning and get the next character
Success
		MOV r0, #1; It's a palindrome so we put 1 in r0
		b	loop
		
Fail	
		MOV r0, #0; It's not a palindrom so we put 0 in r0
		
loop	b	loop
String	DCB	"He lived as a devil, eh?"	;string
EoS		DCB	0x00	;end of string
		END
		
		
		
		