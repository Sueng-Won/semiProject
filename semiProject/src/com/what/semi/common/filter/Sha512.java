package com.what.semi.common.filter;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

//비밀번호 암호화를 위한 sha-512
public class Sha512 {
	public static String getSha512(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = plainText.getBytes(Charset.forName("UTF-8"));
            md.update(bytes);
            return Base64.encode(md.digest());
        } catch (Exception e) {
            System.out.println("Sha512 error.");
            e.printStackTrace();
            return null;
        }
    }

}
