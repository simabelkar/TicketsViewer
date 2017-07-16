import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import javax.xml.bind.DatatypeConverter;
import java.io.*;

public class HttpRequests {

	//Variables
	public static final String USERNAME = "simabelker@gmail.com";
	public static final String PASSWORD = "sima1012";
	public static final String SUBDOMAIN = "noonesupport";
	public static final String LINK = "https://" + SUBDOMAIN + ".zendesk.com/api/v2/";
	
	
	//Methods
	/**
	* This method create URL for getting single ticket information using Zendesk API.
	* @param id
	* @return API response
	* @exception id<=0
	*/
	public void getTicket(int id)
	{
		Ticket ticket = new Ticket();
		if(id<=0)
		{
			System.out.println("ERROR: Please enter valid ID");
		}
		String url = LINK + "tickets/" + id + ".json";
		String response =  sendGet(url,"");
		if(response != null)
		{
			//Parse json response
			JsonParser jsonParser = new JsonParser();
			ticket = jsonParser.parseSingleTicket(response);
			//Display ticket
			ticket.displayInformation();
		}
	}
	
	/**
	* This method send HTTP GET request using Zendesk API
	* @param targetUrl, urlParameters
	* @return response, null
	* @exception 
	*/
	private String sendGet(String targetUrl, String urlParameters)
	{	
		HttpURLConnection connection = null;
		
		try
		{
			//Create connection
			URL object = new URL(targetUrl);
			connection = (HttpURLConnection) object.openConnection();
			connection.setRequestProperty("Accept", "application/json");
			//Authentication
			Authenticator.setDefault (new Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication (USERNAME, PASSWORD.toCharArray());
			    }
			});
			byte[] message = (USERNAME+ ":" + PASSWORD).getBytes("UTF-8");
			String encoding = DatatypeConverter.printBase64Binary(message);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			/*
			 * java 8:
			 * import java.util.Base64;
			 * byte[] message = "hello world".getBytes(StandardCharsets.UTF_8);
			 * String encoded = Base64.getEncoder().encodeToString(message);
			 */
			connection.setRequestMethod("GET");	
			
			//Send request parameters if exist
			if(!urlParameters.isEmpty())
		    {
				DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.close();
		    }
			//Get response
			int responseCode = connection.getResponseCode();
			//HTTP Request success (2xx)
			if (responseCode < 300 && responseCode > 199)
			{
				//Get response message
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				//StringBuffer response = new StringBuffer();
				StringBuilder response = new StringBuilder();
				while ((inputLine = reader.readLine()) != null)
					response.append(inputLine);
				reader.close();

				return response.toString();
			}
			//HTTP Request failure
			else
			{
				printErrorMessage(responseCode);
				return null;
			}
		}
	 
		catch (Exception e) {
			System.out.println(e.getMessage());
		    System.out.println(e.getStackTrace());
		    return null;
		}
		finally
		{
			if (connection != null) 
			      connection.disconnect();
		}
	}
	
	/**
	* This method print the HTTP error message.
	* @param code
	*/
	private void printErrorMessage(int code)
	{
		switch(code)
		{
			case 400:
			{
				System.out.println("HTTP ERROR 400: Bad Request - the HTTP request that was sent to the server has invalid syntax" );
				break;
			}
			case 401:
			{
				System.out.println("HTTP ERROR 401: Unauthorized - the user trying to access the resource has not been authenticated");
				break;
			}
			case 403:
			{
				System.out.println("HTTP ERROR 403: Forbidden - the user made a valid request but the server is refusing to serve the request");
				break;
			}
			case 404:
			{
				System.out.println("HTTP ERROR 404: Not Found - unable to locate the requested file or resource");
				break;
			}
			case 500:
			{
				System.out.println("HTTP ERROR 500: Internal Server Error - the server cannot process the request for an unknown reason");
				break;
			}
			case 503:
			{
				System.out.println("HTTP ERROR 503: Service Unavailable - the server is overloaded or under maintenance");
				break;
			}
			case 504:
			{
				System.out.println("HTTP ERROR 504: Gateway Timeout -  the server is a not receiving a response from the backend servers within the allowed time period");
				break;
			}
		}
	}
}
