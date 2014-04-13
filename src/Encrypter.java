package security;

import java.security.MessageDigest;
/**
 * Author: Christopher Ghyzel
 * Description: Encrypts Strings to sha256 byte array and converts the byte array to hexadecimal
 * to store as a varchar(64) in a database
 */
public class Encrypter {
    /**
     * Encrypts a string with Sha256 encryption methods
     * @param original string to be hashed
     * @return hexadecimal representation of the hash
     */
    public static String toSha256(String original) {
	try {
	    //Message Digest object to get the byte array
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] hash = digest.digest(original.getBytes("UTF-8"));
	    String hex = "";
	    //For loop converts byte array to hexadecimal
	    for (int i = 0; i < hash.length; i++) {
		hex = hex +  Integer.toHexString(0xff & hash[i]);
	    }
	    return hex;
	} catch (Exception e) {
	    System.out.println("Exception in toSha256" + e.getMessage());
	}
	return null;
    }
}
