package telran.text.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import NumberSystems.NumberSystems;

public class Cipher {
	private 		static final int 		NUMBER_OF_CHARS = 10000;
	private 		String 					secret;
	private 		ArrayList<Character> 	garbage 		= new ArrayList<>();
	
	// { public*****************************************************************
	// Constructor 	Cipher(			String secret)
	// String 		getSecret() 						return secret
	// String 		generateNewSecret() 				return secret
	// String		getCipher(		String plainText) 	return cipher
	// String 		getPlainText(	String cipher) 		return plainText
	// ArrayList<String> getCleanCipher(String cipher) 	return resClean
	public Cipher(String secret) {
		this.secret = secret;
		generateNewSecret(secret);
	}
	
	public String getSecret() {
		return secret;
	}

	public String generateNewSecret() {
		generateNewSecret("");
		return this.secret;
	}
	
	public String getCipher(String plainText) {
		if (!validate(plainText)) {
			System.out.println("Error! No validate text!");
			return null;
		};
		StringBuilder chipher = new StringBuilder();
		for (int i = 0; i < plainText.length(); i++) {
			chipher.append(NumberSystems.translationDecToN(plainText.charAt(i),
					secret));
			chipher.append(getGarbage());
		}
		return chipher.toString();
	}
	
	public String getPlainText(String cipher) {
		StringBuilder 		strB_newString	= new StringBuilder();
		ArrayList<String>	resClean 		= getCleanCipher(cipher);
		
		for (int i = 0; i < resClean.size(); i++) {
			strB_newString.append((char)NumberSystems.translationNToDec(resClean.get(i), secret));
		}
		
		return strB_newString.toString();
	}

	public ArrayList<String> getCleanCipher(String cipher) {
		String[] 			resDirty;
		ArrayList<String> 	resClean	= new ArrayList<>();
		String 				delimeter 	= "[^";
		for (int i = 0; i < secret.length(); i++) {
			if (Character.toString(secret.charAt(i)).matches("[a-zA-Z0-9]")) {
				delimeter += secret.charAt(i);
			} else {
				delimeter += "\\" + secret.charAt(i);
			}
		}
		delimeter += "]";
		resDirty = cipher.split(delimeter);
		
		for (int i = 0; i < resDirty.length; i++) {
			if (!resDirty[i].equals("")) {
				resClean.add(resDirty[i]);
			}
		}
		return resClean;
	}
	// } public*****************************************************************
	
	// { private****************************************************************
	// void 				generateNewSecret(String secret)
	// void 				swap(int cur, int secr)
	// String 				getGarbage()						
	// boolean 				validate(String plainText)
	private void generateNewSecret(String secret) {
		for (int i = 0; i < NUMBER_OF_CHARS; i++) {
			garbage.add((char)i);
		}
		Collections.shuffle(garbage);
		if (!secret.equals("")) {
			for (int i = 0; i < secret.length(); i++) {
				swap(i, garbage.indexOf(secret.charAt(i)));
			}
		} else {
			for (int i = 0; i < 24; i++) {
				this.secret += garbage.get(i);
			}
		}
		for (int i = 0; i < secret.length(); i++) {
			garbage.remove(0);
		}
	}

	private void swap(int cur, int secr) {
		Character tmp = garbage.get(cur);
		garbage.set(cur, 	garbage.get(secr));
		garbage.set(secr, 	tmp);
	}

	private String getGarbage() {
		Random random = new Random();
		int amount = random.nextInt(5) + 1;
		StringBuilder stringBuilder = new StringBuilder();
		int index;
		for (int i = 0; i < amount; i++) {
			index = random.nextInt(NUMBER_OF_CHARS - 24 - 1);
			stringBuilder.append(garbage.get(index));
		}
		return stringBuilder.toString();
	}

	private boolean validate(String plainText) {
		for (int i = 0; i < plainText.length(); i++) {
			if ((int)plainText.charAt(i) < 0 || (int)plainText.charAt(i) > 127) {
				return false;
			}
		}
		return true;
	}
	// } private****************************************************************
	
	public static void main(String[] args) {
		Cipher cipher = new Cipher("~!@#$%^&*()<>?|: =-+.asd");
		for (int i = 0; i < cipher.getSecret().length(); i++) {
			System.out.print(i);
		}
		System.out.println();
		System.out.println(cipher.getSecret());
		
		String str_orgText 		= "a1B4J5 !=";
		String str_cipher		= "";
		String str_plainText	= "";
		
		str_cipher 		= cipher.getCipher(str_orgText);
		str_plainText	= cipher.getPlainText(str_cipher);
		
		System.out.println(str_orgText);
		System.out.println(str_cipher);
		System.out.println(str_plainText);
		
		for (int i = 0; i < str_orgText.length(); i++) {
			System.out.println(str_orgText.charAt(i) + " " + (int)str_orgText.charAt(i));
		}
	}
}

