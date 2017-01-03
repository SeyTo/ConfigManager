package org.rjj.config.utils;

/**
 * Created by rj on 23/12/16.
 */
public class TypeUtils {

    /**
     * Determine if a string is an integer.
     * @param str the string to determine if it is an integer
     * @return true if it resembles an integer
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}
