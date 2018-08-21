package telran.text.coding.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.text.coding.Cipher;

class TestTextCoding {
	Cipher cipher;
	String orgText1 = "Hello world!";
	String expText1 = "#~$%$>$>$:!*$d$:$-$>$$!(";
	String orgText2 = "0123456789";
	String expText2 = "@~@!@@@#@$@%@^@&@*@(";
	String orgText3 = "a1B4J5 !=";
	String expText3 = "$!@!@-@$#@@%!*!(@?";
	
	@BeforeEach
	void setUp() throws Exception {
		cipher = new Cipher("~!@#$%^&*()<>?|: =-+.asd");
	}

	@Test
	void testGetCipher() {
		assertEquals(expText1, getClearCipher(cipher.getCipher(orgText1)));
		assertEquals(expText2, getClearCipher(cipher.getCipher(orgText2)));
		assertEquals(expText3, getClearCipher(cipher.getCipher(orgText3)));
	}
	
	@Test
	void testGetPlainText() {
		assertEquals(orgText1, cipher.getPlainText(cipher.getCipher(orgText1)));
		assertEquals(orgText2, cipher.getPlainText(cipher.getCipher(orgText2)));
		assertEquals(orgText3, cipher.getPlainText(cipher.getCipher(orgText3)));
	}
	
	private String getClearCipher(String cipherText1) {
		ArrayList<String> arL;
		StringBuilder strB = new StringBuilder();
		arL = cipher.getCleanCipher(cipherText1);
		for (int i = 0; i < arL.size(); i++) {
			strB.append(arL.get(i));
		}
		return strB.toString();
	}


}
