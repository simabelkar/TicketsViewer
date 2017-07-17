import static org.junit.Assert.*;

import org.junit.Test;

public class TicketListTest {

	@Test
	public void listTicketsWithPage0() {
		TicketList ticketList = new TicketList();
		String result = ticketList.listTickets(0, 25);
		assertEquals("ERROR: invalid page number passed",result);
	}
	
	@Test
	public void listTicketsWithPageMinus() {
		TicketList ticketList = new TicketList();
		String result = ticketList.listTickets(-1, 25);
		assertEquals("ERROR: invalid page number passed",result);
	}
	
	@Test
	public void listTicketsWithPerPage0() {
		TicketList ticketList = new TicketList();
		String result = ticketList.listTickets(1, 0);
		assertEquals("ERROR: invalid number of records on a per-request passed",result);
	}
	
	@Test
	public void listTicketsWithPerPageMinus() {
		TicketList ticketList = new TicketList();
		String result = ticketList.listTickets(1, -1);
		assertEquals("ERROR: invalid number of records on a per-request passed",result);
	}
	
	@Test
	public void listTicketsWithPage0AndPerPage0() {
		TicketList ticketList = new TicketList();
		String result = ticketList.listTickets(0, 0);
		assertEquals("ERROR: invalid page number passed",result);
	}
	
	@Test
	public void listTicketsWithPageNotExist() {
		TicketList ticketList = new TicketList();
		String result = ticketList.listTickets(1234, 2);
		assertEquals("An error occured while parsing json response",result);
	}
	
	@Test
	//page = 4, per_page = 3: 3 tickets are returned 
	public void listTicketsWithPerPageExceedLimit() {
		TicketList ticketList = new TicketList();
		String result = ticketList.listTickets(4, 3);
		assertEquals("SUCCESS",result);
	}
	
	@Test
	public void listTicketsWithPerPageExceedTotalTickets() {
		TicketList ticketList = new TicketList();
		String result = ticketList.listTickets(1, 103);
		assertEquals("SUCCESS",result);
	}
	
	@Test 
	//per_page = total count: only 25 tickets returns
	public void listTicketsWithPerPageMaximum() {
		TicketList ticketList = new TicketList();
		String result = ticketList.listTickets(1, 101);
		assertEquals("SUCCESS",result);
	}
	
	@Test
	public void listTicketsWithValidParameters() {
		TicketList ticketList = new TicketList();
		String result = ticketList.listTickets(2, 10);
		assertEquals("SUCCESS",result);
	}

}
