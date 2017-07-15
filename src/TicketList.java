
public class TicketList {

	//Variables
	Ticket[] ticketArray;
	Boolean hasPrevious = false;
	Boolean hasNext = false;
	
	//Methods
	/**
	* This method print a single Ticket in the ticketArray.
	* @param id
	* @exception id<=0
	*/
	public void displaySingleTicket(int id)
	{
		//Invalid ID inserted
		if (id<=0)
		{
			System.out.println("ERROR: Please enter valid ID");
			return;
		}
		
		displayListHeadline();
		
		//Display single ticket (TODO: use search algorithm O(log(n) - sort list by ID and divide the list to 2)
		for(int i=0; i<ticketArray.length; i++)
		{
			if(ticketArray[i].id == id)
				ticketArray[i].displayInformation();
		}
	}
	
	/**
	* This method print all tickets in the ticketArray.
	*/
	/* 
	public void displayList()
	{
		hasPrevious = false;
		hasNext = false;
		
		displayListHeadline();
		
		//Display list content
		for(int i=0; i<ticketArray.length; i++)
		{
			ticketArray[i].displayInformation();
		}
	}*/
	
	/**
	* This method print given range of tickets in TicketArray.
	* @param from, to
	* 		 from - start with 1 -> ticketArray[0]
	*/
	public void displayList(int from, int to)
	{	
		displayListHeadline();
		
		//Display list content
		for(int i=from-1; i<to; i++)
		{
			ticketArray[i].displayInformation();
		}
	}
	
	/**
	* This method print the list headline.
	*/
	private void displayListHeadline()
	{
		System.out.println("ID\t Subject\t Priority\t Status\t Created at\t");
		System.out.println("-------------------------------------------------------------------------------");
	}
}
