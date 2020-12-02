package style.hidden.day2;

import style.hidden.FileReaderUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2Runner {

    static boolean part1(String password, String letterRequiredInPassword, int minNumberRangeForPassword, int maxNumberRangeForPassword) {
        if(password.contains(letterRequiredInPassword)) {
            long numTimesLetterInPassword = 0;
            for(int i = 0; i < password.length(); i++) {
                if(letterRequiredInPassword.equals(Character.toString(password.charAt(i)))) {
                    numTimesLetterInPassword++;
                }
            }
            return minNumberRangeForPassword <= numTimesLetterInPassword && numTimesLetterInPassword <= maxNumberRangeForPassword;
        }
        return false;
    }

    static boolean part2(String password, String letterRequiredInPassword, int firstNumberInRange, int secondNumberInRange) {
        String firstCharCheck = Character.toString(password.charAt(firstNumberInRange - 1));
        String secondCharCheck = Character.toString(password.charAt(secondNumberInRange - 1));

        return letterRequiredInPassword.equals(firstCharCheck) ^ letterRequiredInPassword.equals(secondCharCheck);
    }

    public static void main(String... args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day2input.txt");
        int part1count = 0;
        int part2count = 0;
        for(String line : lines) {
            Matcher m = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)").matcher(line);
            if(m.matches()) {
                int firstNumberForPolicy = Integer.parseInt(m.group(1));
                int secondNumberForPolicy = Integer.parseInt(m.group(2));
                String letterRequiredInPassword = m.group(3);
                String password = m.group(4);

                if(part1(password, letterRequiredInPassword, firstNumberForPolicy, secondNumberForPolicy)) {
                    part1count++;
                }
                if(part2(password, letterRequiredInPassword, firstNumberForPolicy, secondNumberForPolicy)) {
                    part2count++;
                }
            }

        }
        System.out.println("Day 2 Part 1 Answer = " + part1count);
        System.out.println("Day 2 Part 2 Answer = " + part2count);
    }
}
