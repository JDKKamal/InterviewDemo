package com.jdkgroup.utils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.RegexValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static String patternPassword = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$";
    public static String patternBusinessName = "^[a-zA-Z0-9]+$";
    public static String patternEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z]{2,8}" +
            ")+";
    public final static String patternName = "[a-zA-Z]{3,10}$";
    public final static String patternMobile = "^[0-9]{10,10}$";

    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str) || StringUtils.isBlank(str) || Strings.isNullOrEmpty(str);
    }

    public static boolean isRegexValidator(String str, String expression) {
        RegexValidator sensitive = new RegexValidator(expression);
        return sensitive.isValid(str);
    }

    public static boolean isEqual(final String strA, final String strB) {
        return StringUtils.equals(strA, strB);
    }

    public static String range(String str, char start, char end)
    {
        return CharMatcher.inRange(start, end).retainFrom(str);
    }

    public static boolean isLinkAvailable(String link) {
        Pattern pattern = Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(link);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static String removeAllWhiteSpace(String value) {
        return value.replaceAll("\\s+", "");
    }
}
