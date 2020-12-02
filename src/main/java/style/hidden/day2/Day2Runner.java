package style.hidden.day2;

import style.hidden.FileReaderUtil;

import java.util.List;

public class Day2Runner {

    static boolean part1(String password, String letterRequiredInPassword, int minNumberRangeForPassword, int maxNumberRangeForPassword) {
        if(password.contains(letterRequiredInPassword)) {
            long numTimesLetterInPassword = 0;
            for(int i = 0; i < password.length(); i++) {
                if(letterRequiredInPassword.equals(Character.toString(password.charAt(i)))) {
                    numTimesLetterInPassword++;
                }
            }
            if(minNumberRangeForPassword <= numTimesLetterInPassword && numTimesLetterInPassword <= maxNumberRangeForPassword) {
                return true;
            }
        }
        return false;
    }

    static boolean part2(String password, String letterRequiredInPassword, int firstNumberInRange, int secondNumberInRange) {
        String firstCharCheck = Character.toString(password.charAt(firstNumberInRange - 1));
        String secondCharCheck = Character.toString(password.charAt(secondNumberInRange - 1));

        if(letterRequiredInPassword.equals(firstCharCheck) && !letterRequiredInPassword.equals(secondCharCheck)) {
            return true;
        } else if(!letterRequiredInPassword.equals(firstCharCheck) && letterRequiredInPassword.equals(secondCharCheck)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String... args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day2input.txt");
        int part1count = 0;
        int part2count = 0;
        for(String line : lines) {
            String passwordPolicy = line.split(":")[0];
            String password = line.split(":")[1].trim();
            String rangeForPassword = passwordPolicy.split(" ")[0];
            String letterRequiredInPassword = passwordPolicy.split(" ")[1];
            int firstNumberForPassword = Integer.parseInt(rangeForPassword.split("-")[0]);
            int secondNumberForPassword = Integer.parseInt(rangeForPassword.split("-")[1]);

            if(part1(password, letterRequiredInPassword, firstNumberForPassword, secondNumberForPassword)) {
                part1count++;
            }
            if(part2(password, letterRequiredInPassword, firstNumberForPassword, secondNumberForPassword)) {
                part2count++;
            }
        }
        System.out.println("Day 2 Part 1 Answer = " + part1count);
        System.out.println("Day 2 Part 2 Answer = " + part2count);
    }
}
