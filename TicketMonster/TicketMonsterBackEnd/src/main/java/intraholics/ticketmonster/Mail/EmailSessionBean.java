package intraholics.ticketmonster.Mail;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;


@Stateless
public class EmailSessionBean{
	
	public void SendMail(String email, String password) throws Exception {

		EmailSessionBean http = new EmailSessionBean();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet(email,password);
	}
	
	// HTTP GET request
		public void sendGet(String Email, String Password) throws Exception {

			String url = "http://localhost:64037/api/email/"+Email+"&"+Password;
			
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

       	public void SendReceipt(String email,String price,String creditcard, String orderID) throws Exception {

		EmailSessionBean http = new EmailSessionBean();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendReceiptMail(email, price, creditcard, orderID);
        }
                
        // HTTP GET request
        public void sendReceiptMail(String Email,String Price,String Creditcard, String Order) throws Exception
        {
        	String url = "http://localhost:64037/api/email/"+Email+"/"+Price+"/"+Creditcard+"/"+Order;
		
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
    
		
	public void SendRefund(String email,String price, String order) throws Exception {

		EmailSessionBean http = new EmailSessionBean();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendRefundMail(email, price, order);
        }
                
        // HTTP GET request
        public void sendRefundMail(String Email,String Price, String orderID) throws Exception
        {
        	String url = "http://localhost:64037/api/email/"+Email+"|"+Price+"|"+orderID;
		
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
