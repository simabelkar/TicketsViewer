import java.lang.Object;

public class Ticket {
	
	//Variables
	public static final String ENDPOINT = "/api/v2/tickets/";
	
	long id; //Automatically assigned when creating ticket (READ ONLY)
    String url; //The API url of this ticket (READ ONLY)
    String externalId; //An id you can use to link Zendesk Support tickets to local records
    String type; //The type of this ticket, i.e. "problem", "incident", "question" or "task"
    String subject; //The value of the subject field for this ticket
    String rawSubject; //The dynamic content placeholder, if present, or the "subject" value, if not. See Dynamic Content
    String description; //The first comment on the ticket (READ ONLY)
    String priority; //Priority, defines the urgency with which the ticket should be addressed: "urgent", "high", "normal", "low"
    String status; //The state of the ticket, "new", "open", "pending", "hold", "solved", "closed"
    String recipient; //The original recipient e-mail address of the ticket
    long requesterId; //The user who requested this ticket (MANDATORY)
    long submitterId; //The user who submitted the ticket; The submitter always becomes the author of the first comment on the ticket.
    long assigneeId; //	What agent is currently assigned to the ticket
    long organizationId; //The organization of the requester. You can only specify the ID of an organization associated with the requester.
    long groupId; //The group this ticket is assigned to
    long[] collaboratorsIds; //Who are currently CC'ed on the ticket
    long forumTopicId; //The topic this ticket originated from, if any
    long problemId; //The problem this incident is linked to, if any
    boolean hasIncidents; //Is true of this ticket has been marked as a problem, false otherwise (READ ONLY)
    String dueAt; //If this is a ticket of type "task" it has a due date. Due date format uses ISO 8601 format.
    String[] tags; //The array of tags applied to this ticket
    Via via = new Via();; //This object explains how the ticket was created (READ ONLY)
    String[] customField; //The custom fields of the ticket
    Object satisfactionRating; //The satisfaction rating of the ticket, if it exists, or the state of satisfaction, 'offered' or 'unoffered' (READ ONLY)
    long[] sharingAgreementIds; //The ids of the sharing agreements used for this ticket
    long[] followupIds; //The ids of the followups created from this ticket - only applicable for closed tickets (READ ONLY)
    long ticketFormId; //The id of the ticket form to render for this ticket - only applicable for enterprise accounts
    long brandId; //The id of the brand this ticket is associated with - only applicable for enterprise accounts
    boolean allowChannelback; //Is false if channelback is disabled, true otherwise - only applicable for channels framework ticket (READ ONLY)
    boolean isPublic; //Is true if any comments are public, false otherwise (READ ONLY)
    String createdAt; //When this record was created (READ ONLY)
    String updatedAt; //When this record last got updated (READ ONLY)

    //Methods
    /**
	* This method print the Ticket information to the user.
	*/
    public void displayInformation()
    {
    	System.out.println(id + "\t" + this.subject + "\t" + this.priority + "\t\t" + this.status + "\t\t" + this.createdAt);
    }
    
    /**
	* This method print the Ticket information to the user with columns headline.
	*/
    private void displayInformationWithHeadline()
    {
    	System.out.println("ID\t Subject\t\t\t\t Priority\t Status\t\t Created at\t");
		System.out.println("-----------------------------------------------------------------------------------------------------");
		displayInformation();
    }
    
	/**
	* This method returns a number of ticket properties, but not the ticket comments.
	* By creating endpoind using Zendesk API, call GET http request, call JSON parser and display the information.
	* @param id
	* @return error message, success
	* @exception id<=0
	*/
    public String showTicket(int id)
    {
    	if(id<=0)
			return "ERROR: Please enter valid ID";
    	
    	HttpRequests httpRequest = new HttpRequests();
    	String url = ENDPOINT + id + ".json";
    	String response = httpRequest.sendGet(url, null);
    	//Request success - 200 or 300 range
    	if(httpRequest.responseCode> 199 && httpRequest.responseCode < 400)
    	{
    		//Parse json response and display ticket
			JsonParser jsonParser = new JsonParser();
			Ticket ticket = jsonParser.parseSingleTicket(response);
			if(ticket == null)
				return "An error occured while parsing json response";
			ticket.displayInformationWithHeadline();
			return "SUCCESS";
    	}
    	
    	return httpRequest.printErrorMessage(httpRequest.responseCode);
    }
    
}	
