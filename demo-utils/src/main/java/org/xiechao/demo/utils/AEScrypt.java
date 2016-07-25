package org.xiechao.demo.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yp-tc-m-2682 on 16/7/25.
 */
public class AEScrypt {

    public static void main(String[] args) throws Exception {
		/*
		 * 256 bit key 32字节
		 */
//		String key = UUID.randomUUID().toString().replaceAll("-", "");
//		System.out.println("key:"+key);
//		String content = "123##236##"+new Date().getTime();
//		System.out.println("加密前" + content);
//		String enBytes = AEScrypt.encryptAES(content, key);
//		System.out.println("加密后" + enBytes);
//		String deBytes = AEScrypt.decryptAES(enBytes, key);
//		System.out.println("解密后" + deBytes);
//		System.out.println(MD5.md5("110761"));
//
//        String enBytes;
//        String key="3b8232ddf5354545a94a5d6b3263f7c3";
//
//        enBytes="440301195802181323";
//        System.out.println(AEScrypt.encryptAES(enBytes, key));
//        System.out.println(MD5.md5x(enBytes));
//        System.out.println("========================");
//        enBytes="3568390022360500";
//        System.out.println(AEScrypt.encryptAES(enBytes, key));
//        System.out.println(MD5.md5x(enBytes));
//        System.out.println("========================");
//        enBytes="彭大娜";
//        System.out.println(AEScrypt.encryptAES(enBytes, key));
//        System.out.println(MD5.md5x(enBytes));
//        System.out.println("========================");
//        enBytes="13530876987";
//        System.out.println(AEScrypt.encryptAES(enBytes, key));
//        System.out.println(MD5.md5x(enBytes));
//        System.out.println("========================");
    }

    /*
     * 转为十六进制
     */
    private static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString().toUpperCase();
    }

    /*
     * 转为二进制
     */
    private static byte[] asBin(String src) {
        if (src.length() < 1)
            return null;
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);
            encrypted[i] = (byte) (high * 16 + low);
        }
        return encrypted;
    }

    /*
     * 加密
     */
    public static String encryptAES(String data, String secretKey) {
        byte[] key = asBin(secretKey);
        SecretKeySpec sKey = new SecretKeySpec(key, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return asHex(encrypted);
        } catch (Exception e) {
            return null;
        }
    }

    /*
     * 解密
     */
    public static String decryptAES(String encData, String secretKey) {
        byte[] tmp = asBin(encData);
        byte[] key = asBin(secretKey);
        SecretKeySpec sKey = new SecretKeySpec(key, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, sKey);
            byte[] decrypted = cipher.doFinal(tmp);
            return new String(decrypted);
        } catch (Exception e) {
            return null;
        }
    }
}
