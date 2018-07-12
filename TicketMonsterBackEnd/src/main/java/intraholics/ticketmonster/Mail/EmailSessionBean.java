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

       	public void SendReceipt(String email,String price,String creditcard) throws Exception {

		EmailSessionBean http = new EmailSessionBean();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendReceiptMail(email, price, creditcard);
        }
                
        // HTTP GET request
        public void sendReceiptMail(String Email,String Price,String Creditcard) throws Exception
        {
        	String url = "http://localhost:60109/api/values/"+Email+"/"+Price+"/"+Creditcard;
		
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

		EmailSessionBean http = new EmailSessionBean();

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
	
}
