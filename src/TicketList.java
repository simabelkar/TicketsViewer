
public class TicketList {

	//Variables
	public static final String ENDPOINT = "/api/v2/tickets.json";
			
	Ticket[] ticketArray;
	String hasPrevious = null;
	String hasNext = null;
	long count = 0;
	
	//Methods
	/**
	* This Method returns a maximum of 100 tickets per page
	* Tickets are ordered chronologically by created date, from oldest to newest. The first ticket listed may not be the absolute oldest ticket in your account due to ticket archiving.
	* @param perPage, page
	* @exception id<=0
	*/
	public String listTickets(int page, int perPage)
	{
		if(page<1)
			return "ERROR: invalid page number passed";
		if(perPage<1)
			return "ERROR: invalid number of records on a per-request passed";
		
		System.out.println("\nProcessing your request, please wait...");
		HttpRequests httpRequest = new HttpRequests();
		String parameters = "?page=" + page + "&per_page=" + perPage;
		String response = httpRequest.sendGet(ENDPOINT, parameters);
		//Request success - 200 or 300 range
    	if(httpRequest.responseCode> 199 && httpRequest.responseCode < 400)
    	{
    		//Parse json response and display ticket
			JsonParser jsonParser = new JsonParser();
			TicketList ticketList = jsonParser.parseTicketsList(response);
			if(ticketList == null)
				return "An error occured while parsing json response";
			//Copy all variables to local variables
			this.ticketArray = ticketList.ticketArray;
			this.hasNext = ticketList.hasNext;
			this.hasPrevious = ticketList.hasPrevious;
			this.count = ticketList.count;
			//Display the list
			System.out.println("\n** Displaying page number " + page + " **\n");
			displayList();
			
			return "SUCCESS";
    	}
    	return httpRequest.printErrorMessage(httpRequest.responseCode) ;
	}
	
	/**
	* This method print given range of tickets in TicketArray.
	*/
	private void displayList()
	{	
		displayListHeadline();
		
		//Display list content
		for(int i=0; i<ticketArray.length; i++)
		{
			ticketArray[i].displayInformation();
		}
	}
	
	/**
	* This method print the list headline.
	*/
	private void displayListHeadline()
	{
		System.out.println("ID\t Subject\t\t\t\t Priority\t Status\t\t Created at\t");
		System.out.println("-----------------------------------------------------------------------------------------------------");
	}
}
