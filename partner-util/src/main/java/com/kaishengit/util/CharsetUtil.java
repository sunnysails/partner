package com.kaishengit.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by sunny on 2017/3/18.
 */
public class CharsetUtil {
    public static String toUTF8(String char0) {
        try {
            return new String(char0.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
