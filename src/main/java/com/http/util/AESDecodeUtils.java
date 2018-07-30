package com.http.util;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;  

public class AESDecodeUtils {

    public static void main(String[] args) throws Exception {  
        byte[] encrypData = Base64.decodeBase64("cSXlg/XyN8D316c9M9wfcCKwBnXZgjtykKiNs6D/DIIpE1WfEh8nNir5OwGxXiVfwr6RWT1uFvJXXEznRHF0/W4qJudxKA92ZVKlOxsT02A4VpGGggAL9Qayo0FT4+EfViCd5JkyOB7J/ut3CvKOArN3Uj00yKm/m814KkWs6pktugA7p5VY4TQ2SglfHSe6N0AaT+JHppOiGTlhuefWhQ==");  
        byte[] ivData = Base64.decodeBase64("lrqfuyP4/5iNHyHHOfB1fQ==");  
        byte[] sessionKey = Base64.decodeBase64("smifdW1pM+vvep19+Izfvg==");  
        System.out.println(decrypt(sessionKey,ivData,encrypData));  
    }  
  
    public static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {  
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);  
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");  
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);  
        //解析解密后的字符串  
        return new String(cipher.doFinal(encData),"UTF-8");  
    }  
}
