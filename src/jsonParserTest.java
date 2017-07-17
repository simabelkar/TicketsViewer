import static org.junit.Assert.*;

import org.junit.Test;

public class jsonParserTest {

	HttpRequests request = new HttpRequests();
	JsonParser parser = new JsonParser();
	Ticket ticket = new Ticket();
	TicketList ticketList = new TicketList();
	
	@Test
	//Parse single ticket
	public void parseSingleTicket() {
		String response = request.sendGet("/api/v2/tickets/1.json", null);
		ticket = parser.parseSingleTicket(response);
		assertNotEquals(null, ticket);
	}
	
	@Test
	//Parse ticket list > 25
	public void parseTicketListSizeLongerThan25() {
		String response = request.sendGet("/api/v2/tickets.json", "?page=1&per_page=50");
		ticketList = parser.parseTicketsList(response);
		assertNotEquals(null, ticketList);
	}
	
	@Test
	//Parse ticket list < 25
	public void parseTicketListSizeShorterThan25() {
		String response = request.sendGet("/api/v2/tickets.json", "?page=1&per_page=10");
		ticketList = parser.parseTicketsList(response);
		assertNotEquals(null, ticketList);
	}
	
	@Test
	//Parse ticket list = 25
	public void parseTicketListSizeEqual25() {
		String response = request.sendGet("/api/v2/tickets.json", "?page=1&per_page=25");
		ticketList = parser.parseTicketsList(response);
		assertNotEquals(null, ticketList);
	}
	
	@Test
	//Parse ticket list with single ticket json
	public void parseTicketListWithSingleTicketContent() {
		String response = request.sendGet("/api/v2/tickets/1.json", null);
		ticketList = parser.parseTicketsList(response);
		assertEquals(null,ticketList);
	}
	
	@Test
	//Parse single ticket
	public void parseSingleTicketWithTicketListContent() {
		String response = request.sendGet("/api/v2/tickets.json", "?page=1&per_page=25");
		ticket = parser.parseSingleTicket(response);
		assertEquals(null, ticket);
	}
	
	@Test
	//Parse ticket list with json not containing any ticket
	public void SingleTicketParsingForNonJsonConent() {
		ticket = parser.parseSingleTicket("abcde fghi");
		assertEquals(null, ticket);
	}
	
	@Test
	//Parse single ticket with json not containing any ticket
	public void TicketListParsingForNonJsonContent() {
		ticket = parser.parseSingleTicket("abcde fghi");
		assertEquals(null, ticket);
	}
}
