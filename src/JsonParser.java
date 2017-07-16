import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonParser {
	
	Ticket ticket;
	TicketList ticketArray;
	
	/**
	* This method parse the JSON content of single ticket.
	* @param jsonContent
	* @return Ticket
	*/
	public Ticket parseSingleTicket(String jsonContent)
	{
		ticket = new Ticket();
		JSONArray jsonArray;
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonContent);
			//Handle a structure into the json object
			JSONObject ticketStructure = (JSONObject) jsonObject.get("ticket");
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
			//TODO via structure
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
			//collaborator_ids long[]
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
					ticket.sharingAgreementIds[i] = (long)jsonArray.get(i);
				}
			}
			//ticket.customField = (String[]) ticketStructure.get("fields");
			if(ticketStructure.get("brand_id")!= null)
				ticket.brandId = (long) ticketStructure.get("brand_id");
			ticket.allowChannelback = (boolean) ticketStructure.get("allow_channelback");
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ticket;
	}
}