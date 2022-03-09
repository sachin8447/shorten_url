package com.shortenurl;

import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShortenLinkUtil {
    static Base64 base64 = new Base64();

    public static String encodeToBase62(String inputString){
        return new String(base64.encode(inputString.getBytes()));
    }
    public static String decodeFromBase62(String inputString){
        return new String(base64.encode(inputString.getBytes()));
    }




    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }


    public static String createLinkShortenLink(String url){
        try{
            String shortUrl =  encodeToBase62(toHexString(getSHA(url)));
            return shortUrl.substring(0,7);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";

    }
}
