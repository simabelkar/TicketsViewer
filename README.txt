
This readme explain how to use the ticket viewer program for "Zendesk Coding Challenge 2017", 
which is availble on Github at:  https://github.com/simabelkar/TicketsViewer


Zendesk Ticket Viewer  - by Sima Dahan.
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


Pre-requisites
--------------
<What thing you need to install the SW and how to install them>
Java 7 or higher.
Junit 4.x

Installing
----------
You have serval options for running the Program:

---- RUN IN AN IDE ----

If you want to run the program in an IDE, such as Eclipse, you should be able to copy the entire
contents of the 'src' and 'lib' folder to the project in the IDE, and then run the program.


---- COMPILE AND RUN ON THE COMMAND LINE ----


Windows: 

If you want to compile the program on the command line, and if you have downloaded the project from Github 
to you computer, you can easily compile and run the program.
Just change into the 'src' directory and use the command

					javac -d ..\bin -cp ..\lib\json-simple-1.1.1.jar *.java

The '-d' flags tells javac where to put the output file.
The '-cp' flag tells javac where the used-defined classes and packages are located .
					
to compile all the files for this program. As long as your computer supports Java 7 or higher, there should 
be no errors (You might see some warnings, especially if you use a newer version of java, but warning do not 
stop the program from being compiled or executed.) 
Change into the 'bin' directory and use the command	
You can then run the program using the java command
				
					java Menu

Mac:
	
					
					
					

How it is used:
--------------
<discriptive example of usage after it is up and running>

Running the test
----------------
<Explain how to run the automated tests for this system>
---- RUN ON THE COMMAND LINE ----
For Windows:
					javac -cp <path>/junit.jar orj.junit.runner.JUnitCore <TestClass1> <TestClass2> <TestClass3> [WITHOUR .CLASS EXTANSION]
					javac -cp ./<path>/lib/* <class>.java

Build With
----------
<the framework used>
<dependency management>


Contact info: email, website

