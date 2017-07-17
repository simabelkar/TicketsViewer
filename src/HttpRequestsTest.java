import static org.junit.Assert.*;

import org.junit.Test;

public class HttpRequestsTest {

	@Test
	public void sendGetWithNonExistEndpoint() {
		HttpRequests httpReq = new HttpRequests();
		String result = httpReq.sendGet("/api/v2/tickets/123456.json", null);
		assertEquals("HTTP ERROR 404: Not Found - unable to locate the requested file or resource",result);
	}
	
	@Test
	public void sendGetWithExistEndpoint() {
		HttpRequests httpReq = new HttpRequests();
		String result = httpReq.sendGet("/api/v2/tickets/3.json", null);
		assertEquals(200,httpReq.responseCode);
	}
	
	@Test
	public void sendGetWithExistEndpointWithParameters() {
		HttpRequests httpReq = new HttpRequests();
		String result = httpReq.sendGet("/api/v2/tickets.json", "?page=1&per_page=2");
		assertEquals(200,httpReq.responseCode);
	}
}
