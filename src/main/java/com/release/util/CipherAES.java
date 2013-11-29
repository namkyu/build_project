package com.release.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * The Class CipherAES.
 */
public class CipherAES {

	/** The cipher. */
	private final Cipher cipher;
	/** aes key */
	private String AES_KEY = "nklee_aes_128key";
	/** charset */
	private String CHARSET = "UTF-8";

	public CipherAES() {
		try {
			cipher = Cipher.getInstance("AES"); // AES 암호화를 위한 객체를 생성
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * <pre>
	 * encrypt
	 *
	 * <pre>
	 * @param plainText
	 * @return
	 */
	public String encrypt(String plainText) {
		return encrypt(plainText, AES_KEY); // 암호화, 복호화를 하기 위한 비밀키 (16byte의 비밀키를 만들어줘야 함)
	}

	/**
	 * <pre>
	 * encrypt
	 *
	 * <pre>
	 * @param plainText
	 * @param key
	 * @return
	 */
	public String encrypt(String plainText, String key) {
		try {
			byte[] raw = key.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec); // 암호화 모드로 지정
			byte[] encrypted = cipher.doFinal(plainText.getBytes(CHARSET));
			return asHex(encrypted);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * <pre>
	 * decrypt
	 *
	 * <pre>
	 * @param cipherText
	 * @return
	 */
	public String decrypt(String cipherText) {
		if (cipherText == null) {
			return null;
		}
		return decrypt(cipherText, AES_KEY);
	}

	/**
	 * <pre>
	 * decrypt
	 *
	 * <pre>
	 * @param cipherText
	 * @param key
	 * @return
	 */
	public String decrypt(String cipherText, String key) {
		try {
			byte[] raw = key.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec); // 복호화 모드로 지정
			byte[] original = cipher.doFinal(fromString(cipherText));
			return new String(original, CHARSET);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * <pre>
	 * asHex
	 *
	 * <pre>
	 * @param buf
	 * @return
	 */
	private static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		for (int i = 0; i < buf.length; i++) {
			if ((buf[i] & 0xff) < 0x10) {
				strbuf.append("0");
			}
			strbuf.append(Long.toString(buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

	/**
	 * <pre>
	 * fromString
	 *
	 * <pre>
	 * @param hex
	 * @return
	 */
	private static byte[] fromString(String hex) {
		int len = hex.length();
		byte[] buf = new byte[((len + 1) / 2)];

		int i = 0, j = 0;
		if ((len % 2) == 1) {
			buf[j++] = (byte) fromDigit(hex.charAt(i++));
		}

		while (i < len) {
			buf[j++] = (byte) ((fromDigit(hex.charAt(i++)) << 4) | fromDigit(hex.charAt(i++)));
		}
		return buf;
	}

	/**
	 * <pre>
	 * fromDigit
	 *
	 * <pre>
	 * @param ch
	 * @return
	 */
	private static int fromDigit(char ch) {
		if (ch >= '0' && ch <= '9') {
			return ch - '0';
		}
		if (ch >= 'A' && ch <= 'F') {
			return ch - 'A' + 10;
		}
		if (ch >= 'a' && ch <= 'f') {
			return ch - 'a' + 10;
		}

		throw new IllegalArgumentException("invalid hex digit '" + ch + "'");
	}
}
