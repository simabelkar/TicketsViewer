import java.lang.Object;

public class Ticket {
	
	//Variables
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
    	System.out.println("ID\t Subject\t\t\t\t Priority\t Status\t\t Created at\t");
		System.out.println("-----------------------------------------------------------------------------------------------------");
    	System.out.println(id + "\t" + this.subject + "\t" + this.priority + "\t\t" + this.status + "\t\t" + this.createdAt);
    }
    
	/**
	* This method insert record to Ticket object. Mandatory and Read Only parameters have to be inserted when the ticket record is created.
	* @param mandatory: requesterId 
      		 read only: id, url, description, hasIncident, via, satisfactionRating,
      					followupIds, isPublic, createdAt, updatedAt
	*/
    public void insertRecord(long id, String url, String subject, String description, String priority, String status, 
    		long requesterId, Boolean hasIncident, Via via, Object satisfactionRating, String createdAt, String updatedAt )
    {
    	this.id = id;
    	this.url = url;
    	this.subject = subject;
    	this.description = description;
    	this.priority = priority;
    	this.status = status;
    	this.requesterId = requesterId;
    	this.hasIncidents = hasIncident;
    	this.via = via;
    	this.satisfactionRating = satisfactionRating;
    	this.createdAt = createdAt;
    	this.updatedAt = updatedAt;
    }
    
}	
