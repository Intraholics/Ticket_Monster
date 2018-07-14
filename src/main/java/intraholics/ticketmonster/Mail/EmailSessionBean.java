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


@Stateless
public class EmailSessionBean{
private static final String mailapiURL="http://localhost:64037/api/email/";	
    
	public void SendMail(String email, String password) throws Exception {

		System.out.println("Testing 1 - Send Http GET request");

			String url = mailapiURL+email+"&"+password;
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
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
		}

       	public void SendReceipt(String email,String price,String creditcard,String OrderID) throws Exception {

                String url = mailapiURL+email+"/"+price+"/"+creditcard+"/"+OrderID;
		
        	URL obj = new URL(url);
        	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
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
    
		
	public void SendRefund(String email,String price,String OrderID) throws Exception {
        	String url = mailapiURL+email+"|"+price+"|"+OrderID;
		
        	URL obj = new URL(url);
        	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
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
