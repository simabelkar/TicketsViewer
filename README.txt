-------------------
Author: Sima Dahan
-------------------
This readme explain how to use the ticket viewer program for "Zendesk Coding Challenge 2017", 
which is availble on Github at:  https://github.com/simabelkar/TicketsViewer


Zendesk Ticket Viewer
---------------------
Ticket Viewer is a tool, which connected to Zendesk API and allow you to:
- Request all the tickets for your account and display then in a list (by pressing '2' in main menu). The list
  is ordered by created date, from oldest to newest. 
- Page through tickets when more than 25 are returned (by pressign 'n' or 'p' in ticket list view).
- Display individual ticket details by inserting the ticket id (by pressing '1' in main menu or ticket list 
  view, then entering the ticket id).


General Information:
--------------------
This project includes the followings files:
Source files is 'src' directory (Menu.java, Ticket.java, TicketList.java, HttpRequests.java, JsonParser.java)
Libraries files is 'lib' directory (json-simple-1.1.1.jar)
Unit tests files (TicketTest.java, TicketListTest.java, HttpRequestsTest.java, JsonParserTest.java)
This README 


Getting Started
----------------
These instructions will get you a copy of this project up and runinng on your local machine.


==== Pre-requisites ====

Need to install Java 7 or higher.


==== Installing ====

---- COMPILE AND RUN ON THE COMMAND LINE ----
If you want to compile the program on the command line, and if you have downloaded the project from Github to you computer, you can easily compile and run the program.

* For Windows: 
  1. Just change into the project root directory and use the command
					
					javac -d .\bin -cp .\lib\* .\src\TicketViewerTests\*.java .\src\TicketViewerCode\*.java
					
	 to compile all the files for this program. As long as your computer supports Java 7 or higher, there should be no errors.
  
  2. from project root directory run the program using the java command
				
					java -cp .\lib\json-simple-1.1.1.jar;.\bin TicketViewerCode.Menu

* For Mac:
	
					


Running the test
----------------
* For Windows: 
  from project root directory run the tests using the java command

					java -cp .\lib\*;.\bin TicketViewerTests.TestRunne

For Mac:
					
					
Build With
----------
This tool tested with Java 7 on Window OS.


Contact info: simabelker@gmail.com, www.simadahan.com 

