import static org.junit.Assert.*;

import org.junit.Test;

public class TicketTest {

	@Test
	public void showTicketWithId0() {
		Ticket ticket = new Ticket();
		String result = ticket.showTicket(0);
		assertEquals("ERROR: Please enter valid ID",result);
	}
	
	@Test
	public void showTicketWithIdMinus() {
		Ticket ticket = new Ticket();
		String result = ticket.showTicket(-1);
		assertEquals("ERROR: Please enter valid ID",result);
	}
	
	@Test
	public void showTicketWithIdNotExist() {
		Ticket ticket = new Ticket();
		String result = ticket.showTicket(123456);
		assertEquals("HTTP ERROR 404: Not Found - unable to locate the requested file or resource",result);
	}
	
	@Test
	public void showTicketWithIdValid() {
		Ticket ticket = new Ticket();
		String result = ticket.showTicket(3);
		assertEquals("SUCCESS",result);
	}

}
