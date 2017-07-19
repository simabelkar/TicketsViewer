import static org.junit.Assert.*;
import org.junit.Test;

public class HttpRequestsTest {

	//Tested Method: sendGet()
	@Test
	//Send Request without parameters -> SUCCESS
	public void sendGetWithExistWithoutParameters() {
		HttpRequests httpReq = new HttpRequests();
		//Execution
		httpReq.sendGet("/api/v2/tickets/3.json", null);
		//Verification
		assertEquals(200,HttpRequests.responseCode);
	}
	
	@Test
	//Send Request with parameters -> SUCCESS
	public void sendGetWithWithParameters() {
		HttpRequests httpReq = new HttpRequests();
		httpReq.sendGet("/api/v2/tickets.json", "?page=1&per_page=2");
		assertEquals(200,HttpRequests.responseCode);
	}
	@Test
	//Non exist endpoint -> ERROR
	public void sendGetWithNonExistEndpoint() {
		HttpRequests httpReq = new HttpRequests();
		//Execution
		String result = httpReq.sendGet("/api/v2/tickets/123456.json", null);
		//Verification
		assertEquals("HTTP ERROR 404: Not Found - unable to locate the requested file or resource",result);
	}
}
