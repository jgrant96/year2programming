		#Problem 3
		#initialize the variables we'll use on the first run
.data
resultValue:	.word	0


.text
main:
		addi $s0, $s0, 1
		addi $s1, $s1, 3
		add $s7, $ra, $0

		#Get ready the arguments store them on the stack
		addi $sp, $sp, -4
		sw $s0, 0($sp)
		addi $sp, $sp, -4
		sw $s1, 0($sp) 
		jal Acker
		
		li $v0, 1      		# syscall 4 (print_str)
        	lw $a0, resultValue     # argument: string
        	syscall         	# print the string

		add $ra, $s7, $0
		jr $ra
Loop:		j Loop

Acker:		#store the return address onto the stack
		addi $sp, $sp, -4
		sw $ra, 0($sp)
	
		#grab the parameters
		lw $t0, 8($sp) #Get the M argument from the stack
		lw $t1, 4($sp) #Get the N argument from the stack
	
		beq $t0, $0, MEqualZero #M = 0
		beq $t1, $0, NEqualZero #N = 0
		

#ELSE
		#We find n-1
		addi $t6, $t1, -1

		#Get ready the arguments store them on the stack as we're calling A(m, n-1)
		addi $sp, $sp, -4
		sw $t0, 0($sp)
		addi $sp, $sp, -4
		sw $t6, 0($sp) 
		jal Acker

		#The result should be in $v0

		#we must get back M since $t0 was changed then decrease it by 1
		lw $t0, 8($sp)
		addi $t0, $t0, -1

		#We're going to call function again A(m-1, A(m, n-1))
		addi $sp, $sp, -4
		sw $t0, 0($sp)
		addi $sp, $sp, -4
		sw $v0, 0($sp) 
		jal Acker
		
		#The result should be in $v0

		#Load the return address
		lw $ra, 0($sp)
		
		#Get rid of arguments on the stack
		addi $sp, $sp, 12
		
		#return
		jr $ra

	
MEqualZero: 
		#M equals zero so return
		addi $t1, $t1, 1 #N = N+1

		#Get the return address
		lw $ra, 0($sp)

		#Get rid of arguments on the stack
		addi $sp, $sp, 12
	
		#update the result value
		add $v0, $t1, $0
		sw $v0, resultValue
		#return
		jr $ra


NEqualZero:
		#M = M - 1
		addi $t0, $t0, -1
		addi $t7, $0, 1
		#Set up the stack for another recursive call
		addi $sp, $sp, -4
		sw $t0, 0($sp)
		addi $sp, $sp, -4
		sw $t7, 0($sp)

		#Initiate the call result will be in $v0
		jal Acker

		#Get the return address
		lw $ra, 0($sp)

		#Get rid of arguments on the stack
		addi $sp, $sp, 12
		
		#return
		jr $ra


