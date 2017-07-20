package TicketViewerCode;

/*This class represents tickets list and paging indicator enable paging through the list and provide an API compatible with TicketList */
public class TicketList {

	//===== Variables =====
	public static final String ENDPOINT = "/api/v2/tickets.json";
	public Ticket[] ticketArray;
	public boolean hasPrevious = false;
	public boolean hasNext = false;
	
	//===== Public Methods =====
	/**
	* This Method display list of tickets with the following information: Id, Type, Subject, Priority, Status, Created.
	* by creating request using Zendesk API, call send GET http request, call JSON parser and display the details in a list, using pagination. Display only maximum of 25 tickets per page.
	* Tickets are ordered chronologically by created date, from oldest to newest. The first ticket listed may not be the absolute oldest ticket in your account due to ticket archiving.
	* @param perPage - define how many tickets in a page (the system limit is 25 at most).
	* 		 page - the number of the requested page (used for pagination).
	* @return String - "SUCCESS" or relevant error message.
	* @exception id<=0
	*/
	public String listTickets(int page, int perPage)
	{
		if(page<1)
			return "ERROR: page number must be > 0";
		if(perPage<1)
			return "ERROR: number of records on a per-request must be > 0";
		
		System.out.println("\nProcessing your request, please wait...");
		HttpRequests httpRequest = new HttpRequests();
		String parameters = "?page=" + page + "&per_page=" + perPage;
		String response = httpRequest.sendGet(ENDPOINT, parameters);
		//Request success - 200 or 300 range
    	if(HttpRequests.responseCode> 199 && HttpRequests.responseCode < 400)
    	{
    		//Parse json response and display ticket
			JsonParser jsonParser = new JsonParser();
			TicketList ticketList = jsonParser.parseTicketsList(response);
			if(ticketList == null)
				return JsonParser.errorMessage;
			//Copy all variables to local variables
			this.ticketArray = ticketList.ticketArray;
			this.hasNext = ticketList.hasNext;
			this.hasPrevious = ticketList.hasPrevious;
			//Display the list
			displayList(page);
			
			return "SUCCESS";
    	}
    	return httpRequest.printErrorMessage(HttpRequests.responseCode) ;
	}
	
	//===== Private Methods =====
	/**
	* This method print the tickets list in a given page.
	*/
	private void displayList(int page)
	{	
		//Display title
		System.out.format("\n %66s \n", "** Displaying page number " + page + " **");
		//Display the list
		displayHeadline();
		//Display list content
		for(int i=0; i<ticketArray.length; i++)
			displayInformation(ticketArray[i]);
		displayFooter();
	}
	
	/**
	* This method print the ticket information to the user (a single row in the table).
	*/
    private void displayInformation(Ticket ticket)
    {
    	String str = ticket.subject;
    	//Limit subject length to 50 chars
    	if (str.length() >50)
    		str = str.substring(0,46) + "...";
    	//Align left
    	System.out.format("| %-10s | %-10s | %-50s | %-10s | %-10s | %-25s |\n", ticket.id+"", ticket.type, str, ticket.priority, ticket.status, ticket.createdAt);
    }
    
    /**
	* This method print the table headline.
	*/
    private void displayHeadline()
    {
		System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------");
		//Align left
		System.out.format("| %-10s | %-10s | %-50s | %-10s | %-10s | %-25s |\n", "ID", "Type", "Subject", "Priority", "Status", "Created");
		System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
	* This method print the table footer.
	*/
    private void displayFooter()
    {
		System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------");
    }
}
