package com.gaga.shorteningserver.algorithm;

public class Base62 {
    private final int BASE62 = 62;
    private final String BASE62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    //10진수를 62진수로 변경
    public String convert62Number(long decimal) {
        int remainder;
        StringBuilder convertUrl = new StringBuilder();

        while (decimal > 0) {
            remainder = (int) (decimal % BASE62);
            decimal /= BASE62;

            convertUrl.append(BASE62_CHAR.charAt(remainder));
        }
        return convertUrl.toString();
    }

    public long decoding(String shortUrl) {
        StringBuilder resultStr = new StringBuilder();
        for(char c : shortUrl.toCharArray()) {
            resultStr.append(BASE62_CHAR.indexOf(c));
        }

        return Long.parseLong(resultStr.toString());
    }
}
