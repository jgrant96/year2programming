#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>

int main()
{
	pid_t pid;
	pid_t pid2;
	pid_t pid3;
	pid_t parent;
	int status = 0;

	/*Create a parent process*/
	/*Parent process creates a child process*/
	pid = fork();

	if (pid < 0){
		perror("fork()");
		printf("main function: error number is %d\n", errno);
		exit(pid);
	}
	/*parent process*/
	if (pid > 0){
		/*Making child_2*/
		printf("From parent process %d: child_1 is created with PID %d\n", getpid(), pid);	
		pid2 = fork();
		/* If child_2 gives an error*/
		if (pid2 < 0){
			perror("fork()");
			printf("main function: error number is %d\n", errno);
			exit(pid2);
		}
		/*if we're the parent of child_2*/
		/*we'll wait for child_2 to finish*/
		if (pid2 > 0){
		printf ("From parent Process %d: child_2 is created with PID %d\n", getpid(), pid2);
		printf ("From parent Process %d: Waiting for child_2 to complete before creating child_3\n", getpid());
		wait(&status);
		/*creating child_3*/
		pid3 = fork();
			/* if child_3 gives us an error */
			if (pid3 < 0){
				perror("fork()");
				printf("main function: error number is %d\n", errno);
				exit(pid2);
			}
			/* if we're the parent of child_3 */
			if (pid3 > 0){
				printf("From parent Process %d: child_3 is created with PID %d\n", getpid(), pid3);
				wait(NULL);
			}
			/* if we're child_3, exec to B.out */
			else{
				printf("Calling an external program B.out and leaving child_3...\n");
				execl("B.out", "B.out", NULL);
			}
		}
		/*child_2's operation which is nothing*/
		else{
			exit(1);
		}
	}
	/* if we're child_1 */
	else{		
		/*create a child */
		pid = fork();
		/*if child_1.1 gives an error */
		if (pid < 0){
		}
		/*if we're child_1, and the parent of 1.1 */
		if (pid > 0){
		printf ("From child_1: child_1.1 is created with PID %d\n", pid);
		}
		/*if we're child_1.1 we don't do anything*/
		else{
		exit(0);
		}
	}
	return 0;
}
	
	
