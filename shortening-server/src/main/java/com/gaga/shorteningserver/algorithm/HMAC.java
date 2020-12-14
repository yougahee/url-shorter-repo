package com.gaga.shorteningserver.algorithm;

import org.apache.tomcat.util.codec.binary.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HMAC {
    private static final String HMAC_SHA256_ALGORITHM = "HMACSHA256";
    private final static String key = "asjhencjdbaejx";

    public static String doHMAC(String data) {
        String result = null;

        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA256_ALGORITHM);
        Mac mac;
        try{
            mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(data.getBytes());

            result = encodeBase62(rawHmac);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String encodeBase62(byte[] rawData) {
        String result;

        //일단 Base64로 테스트 해보고 아마 그러면 /이거랑 공백이랑 +,- 이런거 나오는 거 아닌가?
        //Base62로 해보기
        byte[] resultArray = Base64.encodeBase64(rawData);
        result = new String(resultArray);
        return result;
    }

    public static String sha256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());

        return bytesToHex(md.digest());
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            System.out.println(b);
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String message = "testtest";

        System.out.println(doHMAC(message));
    }
}
