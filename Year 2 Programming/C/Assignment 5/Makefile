#format is target-name: target dependencies
#{-tab-}actions

# MACRO definitions
CC = gcc
CFLAG = -std=c99 -Wall

#All Targets
all: operation

#executable depends on the files operation_functions.o and operation.o
operation: operation_functions.o operation.o
	$(CC) $(CFLAG) -o operation operation_functions.o operation.o

#operation.o depends on source and header files
operation.o: operation.c operation_functions.h
	$(CC) $(CFLAG) -c operation.c

#operation_functions depends on source and header files
operation_functions.o: operation_functions.c operation_functions.h
	$(CC) $(CFLAG) -c operation_functions.c

#Clean the build directory
clean:
	rm -f *.o operation
