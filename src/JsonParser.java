import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser {
	
	//===== Variables =====
	public static final int MAX = 25;
	public static String errorMessage;
	
	//===== Public Methods =====
	/**
	* This method parse the JSON content of a single ticket.
	* @param jsonContent - content in JSON.
	* @return Ticket - Ticket object (contain the tickets properties).
	* 		 null - in case of parsing failure
	*/
	public Ticket parseSingleTicket(String jsonContent)
	{
		JSONParser jsonParser = new JSONParser();
		
		try 
		{
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonContent);
			//Handle a structure into the json object
			JSONObject ticketStructure = (JSONObject) jsonObject.get("ticket");
			//No ticket exist in the response
			if(ticketStructure.isEmpty())
			{
				errorMessage = "Error: no record found";
				return null;
			}
			return parseTicketStructure(ticketStructure);
		} 
		catch (Exception e) 
		{
			errorMessage = "Internal Error: internal error occurs while processing your request";
			return null;	
		}
	}
	
	/**
	* This method parse the JSON content of ticket list.
	* The method limit the list size to 25 per page.
	* @param jsonContent - content in JSON.
	* @return TicketList - TicketList object (contain the ticket array and navigation flag)
	* 		null - in case of parsing failure.
	*/
	public TicketList parseTicketsList(String jsonContent)
	{
		TicketList ticketList = new TicketList();
		JSONParser jsonParser = new JSONParser();
		
		try
		{
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonContent);
			//Get tickets array from json object
			JSONArray ticketArray = (JSONArray)  jsonObject.get("tickets");
			//No tickets exist in the response
			if(ticketArray.isEmpty())
			{
				errorMessage = "Error: no records found";
				return null;
			}
			//Page contain list size < 25
			if(ticketArray.size()<MAX)
				ticketList.ticketArray = new Ticket[ticketArray.size()];
			else
				ticketList.ticketArray = new Ticket[MAX];
			//Take tickets elements from array, limit to 25 items per page
			for(int i=0; i<ticketArray.size() && i<MAX; i++)
			{
				//Parse single ticket within the array
				JSONObject ticketStructure = (JSONObject)ticketArray.get(i);
				Ticket t = parseTicketStructure(ticketStructure);
				if (t == null)
				{
					errorMessage = "Error: no records found";
					return null;
				}
				ticketList.ticketArray[i]  = t;
			}
			parseNavigationFlags(jsonObject,ticketList);
			
			return ticketList;
		}
		catch (Exception e)
		{
			errorMessage = "Internal Error: internal error occurs while processing your request";
			return null;
		}
	}
	
	//===== Private Methods =====
	/**
	* This method parse the JSON object of a single ticket structure.
	* @param jsonContent - content in JSON.
	* @return Ticket - Ticket object (contain the tickets properties).
	* 		 null - in case of parsing failure
	*/
	private Ticket parseTicketStructure(JSONObject ticketStructure)
	{
		JSONArray jsonArray;
		Ticket ticket = new Ticket();
		
		if(ticketStructure == null)
			return null;
		
		ticket.url = (String)ticketStructure.get("url");
		ticket.id = (long)ticketStructure.get("id");
		ticket.externalId = (String)ticketStructure.get("external_id");
		//Handle a structure into the ticket object
		JSONObject viaStructure = (JSONObject) ticketStructure.get("via");
		if(viaStructure != null)
		{
			ticket.via.channel = (String)viaStructure.get("channel");
			JSONObject sourceStructure = (JSONObject) viaStructure.get("source");
			if(sourceStructure != null)
			{
				ticket.via.source.from = (JSONObject) sourceStructure.get("from");
				ticket.via.source.to = (JSONObject) sourceStructure.get("to");
				ticket.via.source.rel = (JSONObject) sourceStructure.get("rel");
			}
		}
		ticket.createdAt = (String)ticketStructure.get("created_at");
		ticket.updatedAt = (String)ticketStructure.get("updated_at");
		ticket.type = (String)ticketStructure.get("type");
		ticket.subject = (String)ticketStructure.get("subject");
		ticket.rawSubject = (String) ticketStructure.get("raw_subject");
		ticket.description = (String) ticketStructure.get("description");
		ticket.priority = (String) ticketStructure.get("priority");
		ticket.status = (String) ticketStructure.get("status");
		ticket.recipient = (String) ticketStructure.get("recipient");
		ticket.requesterId = (long) ticketStructure.get("requester_id");
		if(ticketStructure.get("submitter_id") != null)
			ticket.submitterId = (long) ticketStructure.get("submitter_id");
		if (ticketStructure.get("assignee_id")!= null)
			ticket.assigneeId = (long) ticketStructure.get("assignee_id");
		if(ticketStructure.get("organization_id") != null)
			ticket.organizationId = (long) ticketStructure.get("organization_id");
		if(ticketStructure.get("group_id") != null)
			ticket.groupId = (long) ticketStructure.get("group_id");
		//Get array from ticket structure
		jsonArray = (JSONArray) ticketStructure.get("collaborator_ids");
		if(!jsonArray.isEmpty())
		{
			//Take the elements of the json array
			for(int i=0; i<jsonArray.size(); i++)
			{
				ticket.collaboratorsIds[i] = (long)jsonArray.get(i);
			}
		}
		if(ticketStructure.get("forum_topic_id")!= null)
			ticket.forumTopicId = (long) ticketStructure.get("forum_topic_id");
		if(ticketStructure.get("problem_id")!= null)
			ticket.problemId = (long) ticketStructure.get("problem_id");
		ticket.hasIncidents = (boolean) ticketStructure.get("has_incidents");
		ticket.isPublic = (boolean) ticketStructure.get("is_public");
		ticket.dueAt = (String)ticketStructure.get("due_at");
		//Get array from ticket structure
		jsonArray = (JSONArray) ticketStructure.get("custom_fields");
		if(!jsonArray.isEmpty())
		{
			//Take the elements of the json array
			for(int i=0; i<jsonArray.size(); i++)
			{
				ticket.tags[i] = (String)jsonArray.get(i);
			}
		}
		//Get array from ticket structure
		jsonArray = (JSONArray) ticketStructure.get("custom_fields");
		if(!jsonArray.isEmpty())
		{
			//Take the elements of the json array
			for(int i=0; i<jsonArray.size(); i++)
			{
				ticket.customField[i] = (String)jsonArray.get(i);
			}
		}
		ticket.satisfactionRating = (Object) ticketStructure.get("satisfaction_rating");
		//Get array from ticket structure
		jsonArray = (JSONArray) ticketStructure.get("sharing_agreement_ids");
		if(!jsonArray.isEmpty())
		{
			//Take the elements of the json array
			for(int i=0; i<jsonArray.size(); i++)
			{
				if(jsonArray.get(i) != null)
					ticket.sharingAgreementIds[i] = (long)jsonArray.get(i);
			}
		}
		//ticket.customField = (String[]) ticketStructure.get("fields");
		if(ticketStructure.get("brand_id")!= null)
			ticket.brandId = (long) ticketStructure.get("brand_id");
		ticket.allowChannelback = (boolean) ticketStructure.get("allow_channelback");
		
		return ticket;
	}
	
	/**
	* This method parse the navigation flag in tickets list structure.
	* @param jsonObject - jsonContent - content in JSON.
	* 		ticketList - a given ticket list.
	*/
	private void parseNavigationFlags(JSONObject jsonObject, TicketList ticketList)
	{
		String nextPageUrl = (String) jsonObject.get("next_page");
		String previousPageUrl = (String) jsonObject.get("previous_page");
		
		//Copy to TicketList object
		if(nextPageUrl == null)
			ticketList.hasNext = false;
		else
			ticketList.hasNext = true;
		if(previousPageUrl == null)
			ticketList.hasPrevious = false;
		else
			ticketList.hasPrevious = true;
	}
}