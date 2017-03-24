		AREA assignment4_Program_1, CODE, READWRITE
		ENTRY
		ADR r1, STRING1 ;Point to the beginning of the first string
		ADR r3, STRING3	;Point to the beginning of the new string with r3
LoadString1 ;Loading and storing the first string
		LDRB r2, [r1], #1 ;Store a character into r2, from the position point by r1
		CMP r2, #0x00 ;Check to see if the character is a null character
		ADREQ r1, STRING2 ;Make r1 now point to the next string
		BEQ LoadString2 ;Go to the second part where we load and store the second string
		STRB r2, [r3], #1 ;Store a character into r3's position and move to the next data spot
		B LoadString1
		
LoadString2 ;Loading and storing the second string
		LDRB r2, [r1], #1 ;Store a character from the second string into r2
		STRB r2, [r3], #1 ;Store a character from r2 into r3's position and move to the next spot
		CMP r2, #0x00 ;Check if we just stored a null character
		BNE LoadString2 ;if we didn't we load the next character
loop	b	loop

;-----------------DATA--------------------------
STRING1	DCB	"This is a test string1"	;String 1
EoS1	DCB	0x00	;End of string 1
STRING2	DCB	"This is a test string2"	;String2
EoS2	DCB	0x00	;end of string 3
STRING3	space 0xFF
		END