package edw.edw.frameLib;

import java.util.Base64;

public final class StringEncrypt {

	private StringEncrypt() {

	}

	/********************************************************************
	 * 
	 * encryptXOR(String message, String key)
	 * 
	 * Uses provided key to encrypt provided message using simple XOR
	 * 
	 *********************************************************************/

	public static String encryptXOR(String message) {

		try {

			return Base64.getEncoder().encodeToString(message.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decrypt(String encodedMessage) {

		try {

			byte[] decodedBytes = Base64.getDecoder().decode(encodedMessage);
			return new String(decodedBytes);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String  args[])
	{
		System.out.println(encryptXOR("Health01"));
	}

}
