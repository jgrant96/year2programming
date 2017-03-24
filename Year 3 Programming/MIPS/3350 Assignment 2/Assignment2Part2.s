main:

#Create array B
		addi $sp, $sp, -32
		
		#B pointer is in $s0
		add $s0, $sp, $0
		
#Create array A
		addi $sp, $sp, -32
		addi $t0, $0, 29
		sw $t0, 0($sp)
		addi $t0, $0, 45
		sw $t0, 4($sp)
		addi $t0, $0, 5
		sw $t0, 8($sp)
		addi $t0, $0, 9
		sw $t0, 12($sp)
		addi $t0, $0, 25
		sw $t0, 16($sp)
		addi $t0, $0, 6
		addi $t0, $0, 33
		sw $t0, 20($sp)
		addi $t0, $0, 22
		sw $t0, 24($sp)
		addi $t0, $0, 51
		sw $t0, 28($sp)
		
		#A pointer is in $s1
		add $s1, $sp, $0

#Initialize n
		addi $s2, $0, 8

#push the arguments on to the stack
		addi $sp, $sp, -4
		sw $s0, 0($sp)
		addi $sp, $sp, -4
		sw $s1, 0($sp)
		addi $sp, $sp, -4
		sw $s2, 0($sp)

#Call MergeSort
		jal MergeSort
		lw $s1, 0($s0)
		lw $s2, 4($s0)
		lw $s3, 8($s0)
		lw $s4, 12($s0)
		lw $s5, 16($s0)
		lw $s6, 20($s0)
		lw $s7, 24($s0)
		lw $t0, 28($s0)

End: 		j End



MergeSort:
		#push the return address
		addi $sp, $sp, -4
		sw $ra, 0($sp)

		lw $t0, 12($sp)
		lw $t1, 8($sp)
		lw $t2, 4($sp)

		#Check if n is equal to 1
		addi $t3, $0, 1
		beq $t2, $t3, Equal
		j NotEqual


Equal:
		#B[0] = A[0]
		lw $t3, 0($t1)
		sw $t3, 0($t0)

		#load the return address
		lw $ra, 0($sp)

		#Get rid of arguments on the stack
		addi $sp, $sp, 16

		#Return
		jr $ra

NotEqual:
		#Create an array C
		sub $sp, $sp, $t2
		sub $sp, $sp, $t2
		sub $sp, $sp, $t2
		sub $sp, $sp, $t2

		#$t3 points to the stack C
		add $t3, $0, $sp

		#push the base address of C onto the stack
		addi $sp, $sp, -4
		sw $t3, 0($sp)

		#find n/2
		srl $t4, $t2, 1
		
		#save variable A, B, and n
		addi $sp, $sp, -4
		sw $t0, 0($sp)
		addi $sp, $sp, -4
		sw $t1, 0($sp)
		addi $sp, $sp, -4
		sw $t2, 0($sp)

		#push C A and n/2 on to the stack for another program call
		addi $sp, $sp, -4
		sw $t3, 0($sp)
		addi $sp, $sp, -4
		sw $t1, 0($sp)
		addi $sp, $sp, -4
		sw $t4, 0($sp)

		
		#Call MergeSort
		jal MergeSort

		#Load the arguments back
		lw $t0, 8($sp) #A
		lw $t1, 4($sp) #B
		lw $t2, 0($sp) #n
		lw $t3, 12($sp) #C address

		#find n/2
		srl $t4, $t2, 1

		#find n-n/2
		sub $t5, $t2, $t4

		#find n/2*4
		sll $t4, $t4, 2

		#Get the address of A + n/2
		add $t6, $t0, $t4
		
		#Get the address of C + n/2
		add $t7, $t3, $t4

		#Get read another MergeSort Call
		#Push C+n/2, A+n/2 and n-n/2 onto the stack
		addi $sp, $sp, -4
		sw $t7, 0($sp)
		addi $sp, $sp, -4
		sw $t6, 0($sp)
		addi $sp, $sp, -4
		sw $t5, 0($sp)

		#Got to MergeSort
		jal MergeSort

		#Return from MergeSort

		#get the memory back
		lw $t0, 8($sp) #A
		lw $t1, 4($sp) #B
		lw $t2, 0($sp) #n
		lw $t3, 12($sp) #C address
		
		#find n/2
		srl $t4, $t2, 1

		#find n-n/2
		sub $t5, $t2, $t4

		#find n/2*4
		sll $t0, $t4, 2

		#findC+n/2
		add $t0, $t3, $t0

		#Get the arguments ready for the call
		addi $sp, $sp, -4
		sw $t1, 0($sp)
		addi $sp, $sp, -4
		sw $t3, 0($sp)
		addi $sp, $sp, -4
		sw $t0, 0($sp)
		addi $sp, $sp, -4
		sw $t4, 0($sp)
		addi $sp, $sp, -4
		sw $t5, 0($sp)

		#Go to Merge call
		jal Merge
	
		#Return from Merge call
		#Get the value of N to free up space
		lw $t0, 0($sp) #n

		#We need to free up the memory
		#Memory used in order is
		#B address
		#A address
		#n value
		#Return Address
		#n*4 size array
		#address of C
		#Address B
		#Address A
		#Address N
		addi $sp, $sp, 16

		#get rid of the array
		add $sp, $sp, $t0
		add $sp, $sp, $t0
		add $sp, $sp, $t0
		add $sp, $sp, $t0
		
		#Grab the return address
		lw $ra, 0($sp)

		#free up the rest of the data
		addi $sp, $sp, 16
		
		#Return
		jr $ra


Merge:
		#push the return address
		addi $sp, $sp, -4
		sw $ra, 0($sp)
	

		lw $t0, 20($sp) #C Address
		lw $t1, 16($sp) #A Address
		lw $t2, 12($sp) #B Address
		lw $t3, 8($sp)  #na
		lw $t4, 4($sp)	#nb

LoopOne:
		#While Loop Condition
		slt $t5, $0, $t3 #0 < na?
		beq $t5, $0, LoopTwo
		slt $t5, $0, $t4 #0 < nb?
		beq $t5, $0, LoopTwo

		#Get the Constent of A and B
		lw $t6, 0($t1) #A
		lw $t7, 0($t2) #B

		#B < A?
		slt $t5, $t7, $t6
		beq $t5, $0, LOTrue
		
		#B is less than A

		#Put B inside of C
		sw $t7, 0($t0)

		#Increase B and C
		addi $t0, $t0, 4
		addi $t2, $t2, 4

		#decrease nb
		addi $t4, $t4, -1
		
		j LoopOne

LOTrue:
		#B is greater or equal to A
		
		#Put A inside of C
		sw $t6, 0($t0)

		#Increase A and C
		addi $t0, $t0, 4
		addi $t1, $t1, 4

		#decrease na
		addi $t3, $t3, -1

		j LoopOne

LoopTwo:
		slt $t5, $0, $t3 #0 < na?
		beq $t5, $0, LoopThree

		lw $t6, 0($t1) #A

		#Put A inside of C
		sw $t6, 0($t0)

		#Increase A and C
		addi $t0, $t0, 4
		addi $t1, $t1, 4

		#decrease na
		addi $t3, $t3, -1
		
		#return to beginning
		j LoopTwo
		
		

LoopThree:
		slt $t5, $0, $t4 #0 < nb?
		beq $t5, $0, EndMerge

		lw $t7, 0($t2) #B

		#Put B inside of C
		sw $t7, 0($t0)

		#Increase B and C
		addi $t0, $t0, 4
		addi $t2, $t2, 4

		#decrease nb
		addi $t4, $t4, -1

		j LoopThree

EndMerge:
		lw $ra, 0($sp)
		addi $sp, $sp, 24

		#return
		jr $ra

		
		
