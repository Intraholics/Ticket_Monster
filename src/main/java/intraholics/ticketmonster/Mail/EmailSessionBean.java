/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Mail;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class EmailSessionBean{
    
    @Inject
    private EmailSessionBean http;
	
	public void SendMail(String email, String password) throws Exception {
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet(email,password);
	}        
        
	// HTTP GET request
	public void sendGet(String Email, String Password) throws Exception {

		String url = "http://localhost:60109/api/values/"+Email+"&"+Password;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
	

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}


	public void SendRefund(String email,String price) throws Exception {		
		System.out.println("Testing 1 - Send Http GET request");
		http.sendRefundMail(email, price);
        }
                
        // HTTP GET request
        public void sendRefundMail(String Email,String Price) throws Exception
        {
        	String url = "http://localhost:60109/api/values/"+Email+"|"+Price;
		
        	URL obj = new URL(url);
        	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        	// optional default is GET
        	con.setRequestMethod("GET");
	

        	int responseCode = con.getResponseCode();
        	System.out.println("\nSending 'GET' request to URL : " + url);
        	System.out.println("Response Code : " + responseCode);

        	BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
        	String inputLine;
        	StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
        	public void SendReceipt(String email,String price,String creditcard) throws Exception {
		System.out.println("Testing 1 - Send Http GET request");
		http.sendReceiptMail(email,price,creditcard);
	}
                
        	// HTTP GET request
                public void sendReceiptMail(String email,String price,String creditcard) throws Exception {

		String url = "http://localhost:60109/api/values/"+email+"&"+price+"&"+creditcard;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
	

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}	
}
