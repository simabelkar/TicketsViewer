package TicketsViewerTests;

import static org.junit.Assert.*;

import org.junit.Test;

import TicketViewerCode.Ticket;

/*Unit tests for Ticket class methods */
public class TicketTest {

	//Tested Method: showTicket()
	@Test
	//Id = 0 -> SUCCESS
	public void showTicketWithId0() throws Exception {
		Ticket ticket = new Ticket();
		//Execution
		String result = ticket.showTicket(0);
		//Verification
		assertEquals("ERROR: ID must be > 0",result);
	}
	
	@Test
	//Valid id - SUCCESS
	public void showTicketWithIdValid() throws Exception {
		Ticket ticket = new Ticket();
		//Execution
		String result = ticket.showTicket(3);
		//Verification
		assertEquals("SUCCESS",result);
	}
	
	@Test
	//Id < 0 -> ERROR
	public void showTicketWithIdMinus() throws Exception {
		Ticket ticket = new Ticket();
		//Execution
		String result = ticket.showTicket(-1);
		//Verification
		assertEquals("ERROR: ID must be > 0",result);
	}
	
	@Test
	//Id not exist -> ERROR
	public void showTicketWithIdNotExist() throws Exception {
		Ticket ticket = new Ticket();
		//Execution
		String result = ticket.showTicket(123456);
		//Verification
		assertEquals("HTTP ERROR 404: Not Found - unable to locate the requested file or resource",result);
	}
}
