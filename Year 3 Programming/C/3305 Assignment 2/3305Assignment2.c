#include <stdio.h>
#include <stdlib.h>

/* JUSTIN GRANT
3305 ASSIGNMENT 2
STUDENT NUMBER 250787131 */




/* Generic process structure */
struct Process {
	int processId;
	int burstTime;
	struct Process * next;
};

/* Generic queue structure */
struct Queue {
	int type;
	int queueId;
	int priority;
	int timeQuantum;
	int numberOfProcesses;
	struct Process * headProcess;
};

/* Various function prototypes */
int addProcess(struct Queue *queue, int processId, int burstTime);
int addFCFSRRQueue(struct Queue *queue, int processId, int burstTime);
int addSJFQueue(struct Queue *queue, int processId, int burstTime);
struct Process popProcess(struct Queue *queue);
struct Process popFCFSSJFQueue(struct Queue *queue);
struct Process popRRQueue(struct Queue *queue);

/* Adds a process to a generic queue */
int addProcess(struct Queue *queue, int processId, int burstTime)
{
	(*queue).numberOfProcesses = (*queue).numberOfProcesses + 1;
	if ((*queue).type == 0 || (*queue).type == 2){
		return addFCFSRRQueue(queue, processId, burstTime);
	}
	else /* (queue.type == SJF) */{
		return addSJFQueue(queue, processId, burstTime);
	}
}

/* This will add a process to the end of the queue
We use this for First Come First Serve and Round Robin queues
as these as these are implemented as true queues. The differences between
the two come when a process is popped*/
int addFCFSRRQueue(struct Queue *queue, int processId, int burstTime)
{
	/*Create the process to add */
	struct Process *processToAdd;
	processToAdd = malloc(sizeof(struct Process));


	/*initialize the processes values */
	(*processToAdd).processId = processId;
	(*processToAdd).burstTime = burstTime;

	/* check if the first process in the queue is null aka if the queue is empty */
	if ((*queue).numberOfProcesses <= 1){
		(*queue).headProcess = processToAdd;
		return 0;
	}
	/* check if the next processes are empty */
	struct Process * runner = (*queue).headProcess;

	while ((*runner).next != NULL){
		runner = (*runner).next;
	}

	(*runner).next = processToAdd;

	return 0;
}

/* this will add a process to the queue depending on its burst time.
processes with shorter burst times are closer to being popped from the queue */
int addSJFQueue(struct Queue *queue, int processId, int burstTime)
{
	/* Create the process to add */
	struct Process * processToAdd;
	processToAdd = malloc(sizeof(struct Process));

	(*processToAdd).processId = processId;
	(*processToAdd).burstTime = burstTime;

	/* check if the first process in the queue is null, akak if the queue is empty */
	if ((*queue).headProcess == NULL){
		(*queue).headProcess = processToAdd;
		return 0;
	}

	/*check if the next processes not null or less than the next one*/
	struct Process *runner = (*queue).headProcess;
	struct Process *prev = (*queue).headProcess;
	runner = (*runner).next;
	
	if ((*prev).burstTime > burstTime){
		(*processToAdd).next = prev;
		(*queue).headProcess = processToAdd;
		return 0;
	}
		
	while (runner != NULL && (*runner).burstTime < burstTime){
		runner = (*runner).next;
		prev = (*prev).next;
	}

	/* the process is between previous, and runner in the linked list */
	(*processToAdd).next = runner;
	(*prev).next = processToAdd;
	return 0;
}

/* This will return and remove the item at the head of a generic queue */
struct Process popProcess(struct Queue *queue){
	if ((*queue).type == 0 || (*queue).type == 1)
		return (popFCFSSJFQueue(queue));
	else /*(queue.type == RR)*/
		return (popRRQueue(queue));
}

/* This is used to dequeue an item from a first come first serve or
shortest job first queue. These queues have will run the process in its entirety
once it is popped from the queue. */
struct Process popFCFSSJFQueue(struct Queue *queue){
	struct Process processToReturn;
	struct Process * processToFree;
	(*queue).numberOfProcesses--;
	processToFree= (*queue).headProcess;
	(*queue).headProcess = (*(*queue).headProcess).next;

	processToReturn = *processToFree;
	return processToReturn;
}

/*This is used to dequeue an item from a round robin queue. In this queue we will re-add the process if 
it requires more processing, but decrease the burst time of the process. */
struct Process popRRQueue(struct Queue *queue){
	int newBurstTime;
	struct Process processToReturn;
	struct Process * processToFree;
	
	processToFree = (*queue).headProcess;
	(*queue).headProcess = (*(*queue).headProcess).next;

	if ((*processToFree).burstTime > (*queue).timeQuantum){
		addFCFSRRQueue(queue, (*processToFree).processId, ((*processToFree).burstTime - (*queue).timeQuantum));
	}
	else {
		(*queue).numberOfProcesses--;
	}
	
	processToReturn = *processToFree;
	return processToReturn;
}

/* The main program in steps we will
- Open a file to read (and write)
- Read the file character by character
- Initializes queues
- Add processes to the queues
- Pop the queues by priority
- Print statistics on the queues
- Write the statistics to files
*/
int main(void)
{
	/* create variables that will be used to create processes
and change the queues */
	struct Queue * myQueue[3];
	struct Queue queueA, queueB, queueC;
	struct Process processPopped;
	int orderOfPop;
	int sumOfWaitingTime;
	int typeOfQueue;
	int priorityOfQueue;
	int timeQuantum;
	int processId;
	int burstTime;
	int queueId;
	int count;
	int totalWaitingTime = 0;
	FILE *file;
	FILE *output;
	size_t n = 0;
	int c;

	myQueue[0] = &queueA;
	myQueue[1] = &queueB;
	myQueue[2] = &queueC;
	
	


	file = fopen("mullevel_queue_CPU_scheduling_input_file.txt", "r");
	output = fopen("multilevel_cpu_output.txt", "w");

	printf("Starting program\n\n");
	fprintf(output, "Starting program\n\n");

	if (file == NULL)
		return 0;

	printf("File opened\n\n");
	fprintf(output, "File opened\n\n");

	/* we will parse through the file and read all of the tokens we
	find */
	while ((c = fgetc(file)) != EOF){
		
		/* if we find a 'q' this means that we are making
a queue. We read the second character after to know what id it has */
		if (c == 'q'){
			c = fgetc(file);
			c = fgetc(file);
			queueId = (c - '0');
		}

		/* if we read a 'p' we might read an r, which will let us set the priority of the queue.
		we might also read the a process id, in which case we will be soon be reading a process */
		if (c == 'p'){
			c = fgetc(file);

			/*set priority */
			if (c == 'r'){
				c = fgetc(file);
				c = fgetc(file);
				priorityOfQueue = (c - '0') - 1;
				(myQueue[priorityOfQueue])->queueId = queueId;
				(*myQueue[priorityOfQueue]).numberOfProcesses = 0;
				(*myQueue[priorityOfQueue]).headProcess = NULL;
		
			}
			/* create a process */
			else
			{
				
				/* get the process id from the file */
				processId = (c - '0');
				c = fgetc(file);
				while(c != ' ' && c != '\n'){
					processId = processId * 10;
					processId = processId + (c - '0');
					c = fgetc(file);
				}

				/* get the burst time from the file */
				c = fgetc(file);
				burstTime = (c - '0');
				c = fgetc(file);
				while(c == '0' ||c == '1' ||c == '2' ||c == '3' ||c == '4' ||c == '5' ||c == '6' ||c == '7' ||c == '8' ||c == '9' ){
					burstTime = burstTime * 10;
					burstTime = burstTime + (c - '0');
					c = fgetc(file);
				}
			
				addProcess(myQueue[priorityOfQueue], processId, burstTime);
		
			}

		}
			/* If we see a character that signifies the burst type */
		/* f means first come first serve */
		if (c == 'f'){
			typeOfQueue = 0;
			(myQueue[priorityOfQueue])->type = 0;
			/* advance past the word */
			c = fgetc(file);
			c = fgetc(file);
			c = fgetc(file);
			c = fgetc(file);
		}
		
		/* s means shortest job first */
		if (c == 's'){
			typeOfQueue = 1;
			(myQueue[priorityOfQueue])->type = 1;
			/* advance past the word */
			c = fgetc(file);
			c = fgetc(file);
			c = fgetc(file);
		}

		/* r means round robin */
		/* For this one we expect to see time quantum soon after
		so we will advance ahead to that position and get the time quantum */
		if (c == 'r'){
			typeOfQueue = 2;
			(myQueue[priorityOfQueue])->type = 2;
			/* second r */
			c = fgetc(file);
			/* space */
			c = fgetc(file);
			/* t */
			c = fgetc(file);
			/* q */
			c = fgetc(file);
			/* space */
			c = fgetc(file);

			/* we finally get the number */
			c = fgetc(file);
			timeQuantum = (c - '0');
			c = fgetc(file);
			while(c != ' ' && c != '\n'){
				timeQuantum = timeQuantum * 10;
				timeQuantum = timeQuantum + (c - '0');
			}
			
			(*myQueue[priorityOfQueue]).timeQuantum = timeQuantum;
		}

			
	}

	/* The file should finally be parsed
	1. the queues are created. 0 is the queue with the lowest priority.
	2. the queues should now all have their properties changed. like queue type.
	3. process should now have been added to each ready queue
	4. the processes should now have their burst time correct
	*/

	/* Next we pop processes from queues in order */
	for (count = 0; count < 3; count++){
		orderOfPop = 1;
		sumOfWaitingTime = 0;
		printf("Running Queue #%d, with Queue ID %d \n\n", (count+1), (*myQueue[count]).queueId);
		fprintf(output, "Running Queue #%d, with Queue ID %d \n\n", (count+1), (*myQueue[count]).queueId);
	
		
		/* Keep popping until the queue is empty */
		
		do{
			processPopped = popProcess(myQueue[count]);
			printf("Order #%d - process with id %d, popped from stack \n", orderOfPop, processPopped.processId);
			printf("process has waited in total %d time to run\n", sumOfWaitingTime);
			fprintf(output, "Order #%d - process with id %d, popped from stack \n", orderOfPop, processPopped.processId);
			fprintf(output, "process has waited in total %d time to run\n", sumOfWaitingTime);

			/* we have to check if we popped from a round robin queue. If we have then the waiting time is not 
			increased by the burst time of the process and instead the by the time quantum */
			if ((*myQueue[count]).type == 2 && (*myQueue[count]).timeQuantum < processPopped.burstTime){
				sumOfWaitingTime = sumOfWaitingTime + (*myQueue[count]).timeQuantum;
				printf("Process ran for %d time and was re-enqueued \n", (*myQueue[count]).timeQuantum);
				fprintf(output, "Process ran for %d time and was re-enqueued \n", (*myQueue[count]).timeQuantum);
			}
			else{
				sumOfWaitingTime = sumOfWaitingTime + processPopped.burstTime;
				printf("Process has finished execution. Process ran for %d time \n", processPopped.burstTime);
				fprintf(output, "Process has finished execution. Process ran for %d time \n", processPopped.burstTime);

				/* If we've completed the process we must also print it's turnaround time */
				if (myQueue[count]->type == 2 && processPopped.burstTime <= myQueue[count]->timeQuantum){
					printf("Turnover Time for process is: %d\n", sumOfWaitingTime);
					fprintf(output, "Turnover Time for process is: %d\n", sumOfWaitingTime);
				}
			}
			printf("\n");
			fprintf(output, "\n");
			orderOfPop++;

		}
		while((*myQueue[count]).numberOfProcesses > 0);
		/* statistics on the queue that has finished running */
		printf("All processes completed in queue \n");
		printf("Total Waiting Time for queue is %d, average waiting time is %f \n", sumOfWaitingTime, 
		 									((1.0 * sumOfWaitingTime) / ( 1.0 *(orderOfPop - 1))));
		fprintf(output, "All processes completed in queue \n");
		fprintf(output, "Total Waiting Time for queue is %d, average waiting time is %f \n", sumOfWaitingTime, 
		 									((1.0 * sumOfWaitingTime) / ( 1.0 *(orderOfPop - 1))));
		

		totalWaitingTime = totalWaitingTime + sumOfWaitingTime;
		printf("\n");
		fprintf(output, "\n");
		orderOfPop = 1;
		sumOfWaitingTime = 0;
	}
	/* Print the total running time for processes */
	printf("In total for all processes, the time taken is: %d time\n", totalWaitingTime);
	fprintf(output, "In total for all processes, the time taken is: %d time\n", totalWaitingTime);

	fclose(file);
	fclose(output);
	return 0;
}


