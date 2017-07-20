package TicketViewerCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*This class is the user interface using CLI */
public class Menu {

	//===== Variables =====
	public static final int PER_PAGE = 25;
	public static Ticket ticket = new Ticket();
	public static TicketList ticketList = new TicketList();
	public static String response;
	public static boolean hasNext = false, hasPrev = false;
	public static boolean displayMain = true;
	
	//===== Main =====
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int page = 1;
		
		displayLogo();
		while(true)
		{
			try {
				if(displayMain)
					displayMainMenu();
				String userInput = br.readLine();
				switch(userInput)
				{
					//Display a single ticket by id
					case "1":
					{
						int id = 0;
						do
						{	
							//Get ticket id from the user
							System.out.print("Please enter ticket ID: ");
							try{
								id = Integer.parseInt(br.readLine());
								if(id>0)
								{
									response = ticket.showTicket(id);
									if(!response.equals("SUCCESS"))
										System.out.println(response);
								}
								else
									System.out.println("The ticket ID is invalid, please try again!");
							}
							//The input is not numeric
							catch(NumberFormatException e){
								System.out.println("The ticket ID is invalid, please try again!");
							}
						}while(id<=0);
						displaySingleTicketMenu();
						break;
					}
					//Display tickets list using paging
					case "2":
					{
						//Gate ticket list of 25 tickets
						getPage(page);
						break;
					}
					//Navigate to next page, available only in list view
					case "n":
					{
						if(hasNext != false)
						{
							page++;
							//Gate ticket list of 25 tickets
							getPage(page);
						}
						else
							System.out.println("INPUT ERROR: Cannot navigate to the next page");
						break;
					}
					//Navigate to previous page, available only in list view
					case "p":
					{
						if(hasPrev != false && page>1)
						{
							page--;
							//Gate ticket list of 25 tickets
							getPage(page);
						}
						else
							System.out.println("INPUT ERROR: Cannot naviage to the previous page");
						break;
					}
					//Navigate back to main menu
					case "back":
					{
						displayMain = true;
						page = 1;
						break;
					}
					//Exit	
					case "quit":
					{
						System.out.println("Thank you for using Ticket Viewer. Bye bye :)");
						return;
					}
					//Default error in case non of the options typed
					default: 
						System.out.println("INPUT ERROR: The input is invalid, please try again");
				}
			} 
			catch (IOException e) {
				System.out.println("ERROR: An error occured while reading your input");
				System.out.println(e.getMessage());
			    System.out.println(e.getStackTrace());
			}
		}
		
		
		
	}
	
	//===== Private Methods =====
	/**
	* This method display the main menu to the user.
	*/
	private static void displayMainMenu()
	{
		System.out.println("\nSelect view option:");
		System.out.println("Type '1' to view a single ticket");
		System.out.println("Type '2' to view all tickets");
		System.out.println("Type 'quit' to exit");
	}
	
	/**
	* This method display the navigation menu to the user the the ticket list displays.
	*/
	private static void displayNavigationMenu()
	{
		displayMain = false;
		System.out.println("\nType 'n' to move to the next page");
		System.out.println("Type 'p' to move to the previous page");
		System.out.println("Type '1' to view a single ticket");
		System.out.println("Type 'back' go back to main menu");
	}
	
	/**
	* This method display the navigation menu to the user the the ticket list displays.
	*/
	private static void displaySingleTicketMenu()
	{
		displayMain = false;
		System.out.println("\nType 'back' go back to main menu");
	}
	
	/**
	* This method display the main menu to the user when the program loaded.
	*/
	private static void displayLogo()
	{
		System.out.println(" .----------------."); 
		System.out.println("| .--------------. |"); 
		System.out.println("| |   ________   | |"); 
		System.out.println("| |  |  __   _|  | |                _                            _          _   _      _        _           _  "); 
		System.out.println("| |  |_/  / /    | |               | |                          | |        | | (_)    | |      | |         (_)  "); 
		System.out.println("| |     .'.' _   | |  __      _____| | ___ ___  _ __ ___   ___  | |_ ___   | |_ _  ___| | _____| |_  __   ___  _____      _____ _ __ "); 
		System.out.println("| |   _/ /__/ |  | |  \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  | __| |/ __| |/ / _ \\ __| \\ \\ / / |/ _ \\ \\ /\\ / / _ \\ '__|"); 
		System.out.println("| |  |________|  | |   \\ V  V /  __/ | (_| (_) | | | | | |  __/ | || (_) | | |_| | (__|   <  __/ |_   \\ V /| |  __/\\ V  V /  __/ |   "); 
		System.out.println("| |              | |    \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/   \\__|_|\\___|_|\\_\\___|\\__|   \\_/ |_|\\___| \\_/\\_/ \\___|_| "); 
		System.out.println("| '--------------' |"); 
		System.out.println(" '----------------' "); 
	}
	
	/**
	* This method display the requested page and update the navigation flags of this page.
	* @param page - the requested page
	*/
	private static void getPage(int page)
	{
		response = ticketList.listTickets(page, PER_PAGE);
		if(!response.equals("SUCCESS"))
			System.out.println(response);
		hasNext = ticketList.hasNext;
		hasPrev = ticketList.hasPrevious;
		displayNavigationMenu();
	}

}
