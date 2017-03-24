		AREA assignment4_Program_2, CODE, READWRITE
		ENTRY
		ADR r1, STRING1 ;Point to the beginning of the first string
		ADR r2, STRING2 ;Point to the beginning of the new string with r2
		LDRB r3, [r1] ;Load the first character
		b theWordCheck ;branch to the point where we compare if it is the right characters

startLoop	
		MOV r6, r0; set a flag, to 0
		LDRB r3, [r1, #1]! ;Load a character into r3, and move to the next character
		CMP r3, #0x00 ;Check to see if we just loaded a null character
		STRBEQ r3, [r2] ;If we did store a null character put it in STRING2
		BEQ loop ;If we stored the null character we are done
		CMP r3, #0x20 ;Check the character to see if it is a space

theWordCheck ;We might find the word 'the' in the string
		MOVEQ r4, r1 ;make r4 point to the charater r1 is pointing to
		LDRBEQ r5, [r4, #1]! ;Load the character into r5, and go to the next character after that
		CMPEQ r5, #0x74 ;Is the character a 't'
		LDRBEQ r5, [r4, #1]!;Load the next character into r5, and go to the next character after that
		CMPEQ r5, #0x68	;is the character an 'h'
		LDRBEQ r5, [r4, #1]! ;Load the next character into r5, and go to the next character after that
		CMPEQ r5, #0x65 ;is the character an 'e'
		LDRBEQ r5, [r4, #1] ;Load the next character into r5, and go to the next character after that
		MOVEQ r6, #1 ;set a flag that shows the first few characters wrote 'the'
		CMPEQ r5, #0x20 ;is the character a space
		MOVEQ r6, #2 ;Shows the last character was a space
		CMPNE r6, #1 ;Check if the first 4 characters were ' the', the last one might be a null instead of a space
		CMPEQ r5, #0x00 ;Check if the last character is a null
		STREQ r5, [r2]; store a null in the last place
		BEQ loop; we end the program
		CMPNE r6, #2 ;if it wasn't a null then check if we got a space previously.
		STRBEQ r5, [r2], #1 ;Store the latest character in the new string. A space, because we loaded 'the'
		MOVEQ r1, r4 ;Store the next position in r1, because we loaded 'the'
		ADDEQ r1, r1, #1 ;increase
		STRBNE r3, [r2], #1
		B startLoop
		
loop	b	loop

STRING1	DCB	"and the man said they must go"	;String1
EoS		DCB 0x00	;end of string 1
		ALIGN
STRING2	space 0xFF
		END