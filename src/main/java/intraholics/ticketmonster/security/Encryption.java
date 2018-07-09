/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.security;
/**
 *
 * @author Kostis
 */
    

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class Encryption {
	
	public String encryptMessage(String Message){
				
		MessageDigest md5 = null ;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            }
		md5.update(StandardCharsets.UTF_8.encode(Message));
		String encrmessage = String.format("%032x", new BigInteger(1, md5.digest()));
                return encrmessage;
			
	}

}

