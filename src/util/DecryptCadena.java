package util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DecryptCadena {

	//iv length should be 16 bytes
	private String iv = "73dc84PBTGSAEZIO";
	private String key = null;
	private Cipher cipher = null;
	private SecretKeySpec keySpec = null;
	private IvParameterSpec ivSpec = null;

	/**
	* Constructor
	*
	* @throws Exception
	*/
	public DecryptCadena(String key) throws Exception {
		this.key = key;
		System.err.println("lpq - key: " + key);
	
		// Make sure the key length should be 16
		int len = this.key.length();
		if(len < 16) {
			int addSpaces = 16 - len;
			for (int i = 0; i < addSpaces; i++) {
			this.key = this.key + " ";
			}
		} else {
			this.key = this.key.substring(0, 16);
		}
		this.keySpec = new SecretKeySpec(this.key.getBytes(), "AES");
		this.ivSpec = new IvParameterSpec(iv.getBytes());
		this.cipher = Cipher.getInstance("AES/CBC/NoPadding");
	}

	/**
	* Hexa to Bytes conversion
	*
	* @param str
	* @return
	*/
	public byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}
	}

	/** Decrypt the given excrypted string
	*
	* @param encrStr
	* @throws Exception
	*/
	public String decrypt(String encrData) throws Exception {
		String decrData = null;
		try {
			this.cipher.init(Cipher.DECRYPT_MODE, this.keySpec, this.ivSpec);
			byte[] outText = this.cipher.doFinal(hexToBytes(encrData));

			decrData = new String(outText).trim();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return decrData;
	}
	public static void main(String[] args) throws Exception {
		DecryptCadena c = new DecryptCadena("PaEncriptar:");
		String decrStr = c.decrypt("f2da2b5e600da477938a204327dc4ddd");
		System.out.println("Decrypted Str :" + decrStr);
	}
	
}
