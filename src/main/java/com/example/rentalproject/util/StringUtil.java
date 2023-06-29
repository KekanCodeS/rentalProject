package com.example.rentalproject.util;

public class StringUtil {

    public static String generateStringWithAlphabet(String alphabet, int length) {
        StringBuilder ret = new StringBuilder();
        while (ret.length() < length) {
            ret.append(alphabet.charAt((int) Math.round(Math.random()*(alphabet.length()-1))) );
        }
        return ret.toString();
    }

}
