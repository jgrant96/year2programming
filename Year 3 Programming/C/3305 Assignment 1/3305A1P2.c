#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>

int main(){
	pid_t pid;
	pid_t parent;
	char line1 [80];
	char line2 [80];
	int status = 0;
	int n;
	int fd1[2];
	int fd2[2];
	int X;
	int Y;
	int err;
	int err2;

	/* create the pipe */
	if (pipe(fd1)<0) {
	perror("Fatal Error");
	exit(1);
	}

	if (pipe(fd2)<0) {
	perror("Fatal Error");
	exit(1);
	}

	pid = fork();

	/* error forking child */
	if (pid < 0){
		perror("fork()");
		printf("main function: error number is %d\n", errno);
		exit(pid);
	}
	/* Parent Process */
	if (pid > 0){
		close(fd1[0]);
		close(fd2[1]);
	
		sleep(1);
		printf("From parent %d: child_1 is created with PID %d\n", getpid(), pid);
		printf("From parent %d: Reading X from the user\n", getpid());
		
		/* read X */
		scanf("%d", &X);
		sleep(6);
		
		printf("A pipe is crated for communication between parent and child\n");
		printf("From parent %d: Writing X into the pipe\n", getpid());
		/* write X into the pipe */
		write(fd1[1], &X, sizeof(X));
		sleep(18);

		printf("From parent %d: Reading Y from the pipe\n", getpid());
		
		/* read Y from the pipe */
		err = read(fd2[0], &Y, sizeof(Y));

		printf("From parent %d: The value of Y is %d\n", getpid(), Y);
		sleep(40);
		
	
	}
	/* Child Process */
	else{
		close (fd1[1]);
		close (fd2[0]);
		
		sleep(5);
		printf ("From child: Reading Y from the user\n");
		
		/* read Y from the user */
		scanf("%d", &Y);
		sleep(7);
	
		printf ("From child: Reading X from the pipe\n");
		
		/* read X from the pipe */
		err2 = read(fd1[0], &X, sizeof(X));
	
		printf ("From child: Writing Y into the pipe\n");

		/* write Y into the pipe */
		write(fd2[1], &Y, sizeof(Y));
		sleep(22);
		printf ("From child: The value of X is %d\n", X);
		
	}

return 0;
}
