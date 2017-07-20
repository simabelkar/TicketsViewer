package TicketViewerTests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	 public static void main(String[] args) {
	      
		 Result result = JUnitCore.runClasses(TestSuite.class);

	      for (Failure failure : result.getFailures()) 
	      {
	         System.out.println("\n" + failure.toString());
	      }
			
	      if (result.wasSuccessful())
	    	  System.out.println("\nAll tests passed :) ");
	      else
	    	  System.out.println("\nSome tests failed :( ");
	   }
}
