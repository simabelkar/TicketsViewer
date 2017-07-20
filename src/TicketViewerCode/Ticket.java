package TicketViewerCode;

import java.lang.Object;
import java.util.ArrayList;

/*This class represents a single ticket in the system and provide an API compatible with Ticket */
public class Ticket {
	
	//===== Variables =====
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
    Via via = new Via(); //This object explains how the ticket was created (READ ONLY)
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
    
    //Nested class
    public class Via
    {
    	String channel;
    	Source  src = new Source();
    	//Nested class
    	public class Source
    	{
    		Object from;
    		Object to;
    		Object rel;
    	}
    }
    //===== Public Methods =====
	/**
	* This method display a number of ticket properties (Subject, CreatedAt, Description, RequesterId, submitterId,assigneeId)
	* by creating request using Zendesk API, call send GET http request, call JSON parser and display the details of a single ticket.
	* @param id - the id of the requested ticket.
	* @return String - "SUCCESS" or relevant error message.
	* @exception id<=0.
	*/
    public String showTicket(int id)
    {
    	if(id<=0)
			return "ERROR: ID must be > 0";
    	
    	System.out.println("\nProcessing your request, please wait...");
    	HttpRequests httpRequest = new HttpRequests();
    	String url = ENDPOINT + id + ".json";
    	String response = httpRequest.sendGet(url, null);
    	//Request success - 200 or 300 range
    	if(HttpRequests.responseCode> 199 && HttpRequests.responseCode < 400)
    	{
    		//Parse json response and display ticket
			JsonParser jsonParser = new JsonParser();
			Ticket ticket = jsonParser.parseSingleTicket(response);
			if(ticket == null)
				return JsonParser.errorMessage;
			
			ticket.displayInformation();
			return "SUCCESS";
    	}
    	
    	return httpRequest.printErrorMessage(HttpRequests.responseCode);
    }
    
    //===== Private Methods =====
    /**
	* This method print the Ticket details to the user.
	*/
    private void displayInformation()
    {
    	//Display title
    	System.out.format("\n %66s \n", "** Displaying ticket number " + this.id + " **");
    	System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------");
    	//Subject align left, createdAt align right
    	System.out.format("| Subject: %-82s  Created at: %-25s |\n", this.subject, this.createdAt);
    	System.out.format("|%-132s|\n| Description: %-116s  |\n", " ","");
    	//Handle Description content
    	int length=this.description.length();
    	//Description is short, and fit 1 row
    	if(length <= 130)
    		System.out.format("| %-130s |\n", this.description);
    	//Description is too long, need text wrapping
    	ArrayList<String> descWrapped = WrapTextFullWords (this.description, 130);
    	for (int i=0; i< descWrapped.size(); i++)
    	{
    		System.out.format("| %-130s |\n", descWrapped.get(i));
    	}

    	System.out.format("|%-132s|\n| Requester id: %-28s  Submitter id: %-33s  Assignee id: %-24s |\n", "", this.requesterId, this.submitterId, this.assigneeId);
    	System.out.println(" ------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
	* This method wrap the text showing full words, used to wrap the description content.
	*/
    private ArrayList<String> WrapTextFullWords (String str, int maxLength)
    {
    	ArrayList<String> result = new ArrayList<String>();
    	
    	//Remove new line within the description
    	str = str.replace("\n", "");
    	String[] wordsArray = str.split(" ");
    	String line = "";
    	//Wrap text
    	for(int i=0; i<wordsArray.length; i++)
    	{
    		//Words can be added to the line
    		if(line.length() + wordsArray[i].length() <= maxLength-1)
    			line = line.concat(wordsArray[i] + " ");
    		//Line reached max length
    		else
    		{
    			i--; //Skip the word that not inserted to the line
    			result.add(line);
    			line = "";
    		}
    	}
    	//Add the last line
    	result.add(line); 
    	
    	return result;
    }
}
    


