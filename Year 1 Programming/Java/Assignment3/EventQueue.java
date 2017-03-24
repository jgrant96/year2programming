
public class EventQueue {
	
	final int impossibleNumItemsValue=-1;

	QueueADT<Event> eventQueue;

	// Constructor
	public EventQueue() {
		eventQueue=new LinkedQueue<Event>();
	}

	/////////////////////////////////////////////////////////////////
	// Insert events one at a time in sorted order on eventTime
	/////////////////////////////////////////////////////////////////
	/**
	 * Inserts an event into the queue, maintain the queues order, which is by time
	 * @param insertEvent The event to be inserted
	 * @param debug boolean variable to test function in the queue
	 */
	public void insertEvent(Event insertEvent,boolean debug) {
		String s;

		if(debug)
		{
			System.out.format("\nINSERT EVENT:\nEvent time: %7.2f Event type: %s",
					insertEvent.getEventTime(),insertEvent.getEventType());
			if(insertEvent.getEventNumItems()!=impossibleNumItemsValue)
				System.out.format(" Event number of items: %d",insertEvent.getEventNumItems());
			System.out.format("\n");
		}

		if(debug)
		{
			System.out.println("\nEvent Queue before new event enqueued");
			System.out.println("-------------------------------------");
			s=eventQueue.toString();

			if(!s.equals(""))
				System.out.println(s);
			else
				System.out.println("Empty Queue");

			System.out.println("\n");
		}

		// Insert your code here to do the insertion
		// Events are dequeued from the eventQueue and placed in a temporary queue till we find where we need to
		// insert our event. Once we do we enqueue the new event object into the temporary queue.
		// Then we enqueue the rest of eventQueue into tempQueue, and tempQueue, because the main queue.
		LinkedQueue<Event> tempQueue = new LinkedQueue<Event>();
		if (eventQueue.isEmpty())
			tempQueue.enqueue(insertEvent);
		while (!eventQueue.isEmpty())
		{
			if (insertEvent.getEventTime() > eventQueue.first().getEventTime())
			{
				tempQueue.enqueue(eventQueue.dequeue());
			}
			else
			{
				tempQueue.enqueue(insertEvent);
				while(!eventQueue.isEmpty())
				{
					tempQueue.enqueue(eventQueue.dequeue());
				}
			}
		}
		eventQueue = tempQueue;

		if(debug)
		{
			System.out.println("\nEvent Queue after new event enqueued");
			System.out.println("------------------------------------");
			s=eventQueue.toString();

			if(!s.equals(""))
				System.out.println(s);
			else
				System.out.println("Empty Queue");

			System.out.println("\n");
		}
	}

	/////////////////////////////////////////////////////////////////////////
	// Delete all events from order queue whose eventTime >= minAllDoneTime
	/////////////////////////////////////////////////////////////////////////
	/**
	 * Delete's all events from the Queue that have a time value greater than minAllDoneTIme
	 * @param minAllDoneTime The time value that separates events that are kept and deleted
	 * @param debug boolean variable used to test various functions in the method
	 */
	public void deleteEvents(double minAllDoneTime,boolean debug) {
		// this method assumes that the queue is already sorted
		// We dequeue events from eventQueue until the eventTime >= minAllDoneTime
		// and enqueue them in backwards order in tempQueue.
		// The first ALL_DONE event whose eventTime is
		// equal to minAllDoneTime is enqueued in tempQueue as well.
		// The eventQueue is then emptied and lastly tempQueue is copied back into
		// now empty eventQueue in correct sorted order.

		String s;


		if(debug)
		{
			System.out.println("\nEvent Queue before all events with eventTime >" + 
					minAllDoneTime + " deleted");
			System.out.println("------------------------------------------------------");
			s=eventQueue.toString();

			if(!s.equals(""))
				System.out.println(s);
			else
				System.out.println("Empty Queue");

			System.out.println("\n");
		}

		// Insert your code here to do the deletion
		//We cycle through the entire queue and enqueue the events into a new queue maintaining the same order
		// only if they have time values lower than minAllDoneTime. If they don't the events are just discarded.
		LinkedQueue<Event> tempQueue = new LinkedQueue<Event>();
		while (!eventQueue.isEmpty())
		{
			if (minAllDoneTime > eventQueue.first().getEventTime())
				tempQueue.enqueue(eventQueue.dequeue());
			else eventQueue.dequeue();

		}
		eventQueue = tempQueue;

		if(debug)
		{
			System.out.println("\nEvent Queue after all events with eventTime >" + 
					minAllDoneTime + " deleted");
			System.out.println("-----------------------------------------------------");
			s=eventQueue.toString();

			if(!s.equals(""))
				System.out.println(s);
			else
				System.out.println("Empty Queue");

			System.out.println("\n");
		}

	}

}
