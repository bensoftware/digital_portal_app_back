package com.monetique.um.utils;

/**
 * @author bpm digitale
 *
 */

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Password {

	private byte[]	_encodedPassword;

	String			_charset	= "ISO-8859-1";

	private String	_hex;

	public Password() {

	}

	public Password(String charset) {
		_charset = charset;
	}

	public Password(String charset, String passCrypted) throws UnsupportedEncodingException {
		_charset = charset;
		_encodedPassword = passCrypted.getBytes(_charset);
	}

	public static String encryptPassword(String clair) throws UnsupportedEncodingException {
		String hex = "";
		int h = -1;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] p = messageDigest.digest(clair.getBytes());
			// String sp = new String(p);
			for (int i = 0; i < p.length; i++) {
				h = p[i] & 0xFF;
				if (h < 16)
					hex += "0";
				hex += Integer.toString(h, 16).toUpperCase() + "";
				// hex = hex + Byte.toString(p[i]);
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return hex;
	}

	/**
	 * @param clair
	 * @return
	 */
	public boolean verifyPassword(String clair) {
		try {
			String hex = "";
			String hex1 = "";
			String hex2 = "";
			int h = -1;
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] p = messageDigest.digest(clair.getBytes());
			// String sp = new String(p);
			for (int i = 0; i < p.length; i++) {
				hex1 += Byte.toString(p[i]);
				h = p[i] & 0xFF;

				if (h < 16)
					hex += "0";
				hex2 += Integer.toString(h, 16).toUpperCase();
				hex += Integer.toString(h, 16).toUpperCase() + "";
				hex = hex + Byte.toString(p[i]);
			}// fin if

			// hex = hex.substring(0, 14);
			System.out.println("pass md5 hex = " + hex);
			System.out.println("pass md5 hex1 = " + hex1);
			System.out.println("pass md5 hex2 = " + hex2);

			String sp = new String(p, _charset);
			System.out.println("pass md5 = " + sp);
			System.out.println("verif ex = " + UtilString.equal(hex, _hex));
			return MessageDigest.isEqual(p, _encodedPassword);
		}
		catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}

	public void setPassword(String clair) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			_encodedPassword = messageDigest.digest(clair.getBytes());
			_hex = encryptPassword(clair);
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public String getEncodedPassword() throws UnsupportedEncodingException {
		return new String(_encodedPassword, _charset);
	}

	public String getEncodedPasswordWithoutCharset() throws UnsupportedEncodingException {
		return new String(_encodedPassword);
	}

	public static String getEncodedPasswordWithoutCharset(String clair) throws UnsupportedEncodingException {
		if (!UtilString.isCorrect(clair))
			return "";
		return new String(encryptPassword(clair));
	}

	public static void main(String[] args) {
		Password pwd = new Password();
		pwd.setPassword("toto");
		System.out.println("Verifie toto : " + pwd.verifyPassword("toto"));
		System.out.println("Verifie titi : " + pwd.verifyPassword("titi"));
		System.out.println("Verifie titi : " + pwd.verifyPassword("madoumaa"));
		try {
			System.out.println("pass 01 = " + Password.getEncodedPasswordWithoutCharset("mmb"));
			System.out.println("pass 01 = " + Password.getEncodedPasswordWithoutCharset("jau"));
			System.out.println("pass 01 = " + Password.getEncodedPasswordWithoutCharset("admin"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String keyStr = "agileway2017";

    private static Key aesKey = null;
    private static Cipher cipher = null;

    synchronized private static void init() throws Exception {
        if (keyStr == null || keyStr.length() != 16) {
            throw new Exception("bad aes key configured");
        }
        if (aesKey == null) {
            aesKey = new SecretKeySpec(keyStr.getBytes(), "AES");
            cipher = Cipher.getInstance("AES");
        }
    }

    synchronized public static String encrypt(String text, String code) throws Exception {
    	if (text == null || text.equals(""))
    		return "";
        init();
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return toHexString(cipher.doFinal(text.getBytes()));
    }

    synchronized public static String decrypt(String text, String code) throws Exception {
    	if (text == null || text.equals(""))
    		return "";
        init();
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        return new String(cipher.doFinal(toByteArray(text)));
    }

    public static String toHexString(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }

    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }
}
