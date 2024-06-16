package string.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions {
    public static void main(String[] args) {
        String[] testStrings = {"12345", "abc123", "123abc", "", "abc"};

        Pattern pattern1 = Pattern.compile("^\\d*$");
        Pattern pattern2 = Pattern.compile("\\d*");
        Pattern pattern3 = Pattern.compile("^\\d*");
        Pattern pattern4 = Pattern.compile("\\d$");

        System.out.println("Pattern 1: ^\\d*$");
        for (String s : testStrings) {
            Matcher matcher1 = pattern1.matcher(s);
            System.out.println("String: " + s + " -> " + matcher1.matches());
        }

        System.out.println("\nPattern 2: \\d*");
        for (String s : testStrings) {
            Matcher matcher2 = pattern2.matcher(s);
            System.out.println("String: " + s + " -> " + matcher2.find());
        }

        System.out.println("\nPattern 3: ^\\d*");
        for (String s : testStrings) {
            Matcher matcher3 = pattern3.matcher(s);
            System.out.println("String: " + s + " -> " + matcher3.find());
        }

        System.out.println("\nPattern 4: \\d$");
        for (String s : testStrings) {
            Matcher matcher4 = pattern4.matcher(s);
            System.out.println("String: " + s + " -> " + matcher4.find());
        }
    }
}
