package TicketViewerTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	TicketTest.class,
	TicketListTest.class,
	HttpRequestsTest.class,
	JsonParserTest.class
})

public class TestSuite {

}
