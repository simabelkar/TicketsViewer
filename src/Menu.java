import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

	//static TicketList ticketArray;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Ticket ticket = new Ticket();
		TicketList ticketList = new TicketList();
		String response;
		
		while(true)
		{
			try {
				displayMainMenu();
				String userInput = br.readLine();
				switch(userInput)
				{
					case "1":
					{
						System.out.println("INFO: 1 pressed");
						response = ticketList.listTickets(1, 25);
						if(!response.equals("SUCCESS"))
							System.out.println(response);
						break;
					}
					case "2":
					{
						System.out.println("INFO: 2 pressed");
						response = ticket.showTicket(3);
						if(!response.equals("SUCCESS"))
							System.out.println(response);
						break;
					}
					case ">":
					{
						System.out.println("INFO: > pressed");
						break;
					}
					case "<":
					{
						System.out.println("INFO: < pressed");
						break;
					}
					case "quit":
					{
						System.out.println("Bye bye :)");
						return;
					}
					default: 
						System.out.println("ERROR: The input is invalid, please try again");
				}
			} 
			catch (IOException e) {
				System.out.println("ERROR: An error occured while reading your input");
				System.out.println(e.getMessage());
			    System.out.println(e.getStackTrace());
			}
		}
		
		
		
	}
	
	//Methods
	/**
	* This method display the main menu to the user.
	*/
	private static void displayMainMenu()
	{
		System.out.println("\nSelect view option:");
		System.out.println("Type '1' to view all tickets");
		System.out.println("Type '2' to view a single ticket");
		//if (ticketArray.hasNext)
			System.out.println("Type '>' to move to the next page");
		//if(ticketArray.hasPrevious)
			System.out.println("Type '<' to move to the previous page");
		System.out.println("Type 'quit' to exit");
	}
	
	

}
