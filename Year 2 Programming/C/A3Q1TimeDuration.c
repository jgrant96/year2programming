#include<stdio.h>


int main(void)
{
	// Time addition program
	// Justin Grant
	// Assignemnt 3, question 1

	//initializing variables
	int timeOfDay;
	int duration;
	int hoursInMinutes1;
	int hoursInMinutes2;
	int timeInMinutes;
	int durationInMinutes;
	int finalTime;
	int finalMinutes;
	int finalHours;

	//prompt user for time of day
	printf("Enter time of day as integer in format HHMM: ");
	scanf ("%d", &timeOfDay);

	//Make sure the time of day is inbetween 0:00 and 23:59
	while ((timeOfDay < 0) |  (timeOfDay > 2359) | (timeOfDay - timeOfDay / 100 * 100) > 59)
	{
		printf ("Your input is wrong. Format is HHMM, H=hour, M=minute \n");
		printf ("Try again: ");
		scanf ("%d", &timeOfDay);
		
	}

	//Prompt the user for the time duration
	printf("Enter duration as integer in format +HHMM or -HHMM: ");
	scanf ("%d", &duration);


	//make sure the duration is a number
	while (!(duration >=0 || duration < 0))
	{
		printf ("Your input is wrong. Format is +HHMM or -HHMM \n");
		printf ("Try again nerd: ");
		scanf ("%d", duration);
		
	}

	//convert the hours part of the time of day and duration to minutes
	hoursInMinutes1 = (timeOfDay / 100) * 60;
	hoursInMinutes2 = (duration / 100) * 60;

	//combine the minutes with hours
	timeInMinutes = (timeOfDay - ((hoursInMinutes1 / 60) * 100)) + hoursInMinutes1;
	durationInMinutes = (duration - ((hoursInMinutes2 / 60) * 100)) + hoursInMinutes2;

	//Add the duration to the current time
	finalTime = (timeInMinutes + durationInMinutes) % 1440;
	//If the time is negative, we've looped around the opposite way.
	if (finalTime < 0)
		finalTime = 1440 + finalTime;
	//extract the minutes and hours from the new time
	finalHours = ((finalTime/60) % 24) * 100;
	finalMinutes = (finalTime - finalTime/60*60) %60;

	//hours is always *100, so HHMM is maintained we just add.
	finalTime = finalMinutes + finalHours;


	//print answer
	printf("The new time is: %d", finalTime);
	printf("\n");

	return 1;
}

