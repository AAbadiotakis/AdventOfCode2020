package style.hidden.day4;

import style.hidden.FileReaderUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4Runner {

    record Passport(
            String birthYear,
            String issueYear,
            String expirationYear,
            String height,
            String hairColor,
            String eyeColor,
            String passportId,
            String countryId
    ) {}

    enum PassportFields {
        byr,
        iyr,
        eyr,
        hgt,
        hcl,
        ecl,
        pid,
        cid
    }

    static final Pattern birthYearPattern = Pattern.compile("(19[2-9][0-9])|(200[0-2])");
    static final Pattern issueYearPattern = Pattern.compile("(201[0-9])|(2020)");
    static final Pattern expirationYearPattern = Pattern.compile("(202[0-9])|(2030)");
    static final Pattern heightPattern = Pattern.compile("(1[5-8][0-9]cm)|(19[0-3]cm)|(59in)|(6[0-9]in)|(7[0-6]in)");
    static final Pattern hairColorPattern = Pattern.compile("(#[0-9a-f]{6})");
    static final Pattern eyeColorPattern = Pattern.compile("(amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth)");
    static final Pattern passportIdPattern = Pattern.compile("[0-9]{9}");

    static boolean validatePassport(Passport passport) {
        if(!birthYearPattern.matcher(passport.birthYear).matches()) {
            return false;
        }
        if(!issueYearPattern.matcher(passport.issueYear).matches()) {
            return false;
        }
        if(!expirationYearPattern.matcher(passport.expirationYear).matches()) {
            return false;
        }
        if(!heightPattern.matcher(passport.height).matches()) {
            return false;
        }
        if(!hairColorPattern.matcher(passport.hairColor).matches()) {
            return false;
        }
        if(!eyeColorPattern.matcher(passport.eyeColor).matches()) {
            return false;
        }
        if(!passportIdPattern.matcher(passport.passportId).matches()) {
            return false;
        }
        return true;
    }

    static boolean validateMap(Map<PassportFields, String> map) {
        if(
                map.containsKey(PassportFields.byr) &&
                map.containsKey(PassportFields.iyr) &&
                map.containsKey(PassportFields.eyr) &&
                map.containsKey(PassportFields.hgt) &&
                map.containsKey(PassportFields.hcl) &&
                map.containsKey(PassportFields.ecl) &&
                map.containsKey(PassportFields.pid)
        ) {
            return true;
        }
        return false;
    }

    public static void main(String... args) throws Exception {
        String str = FileReaderUtil.parseFileAsString("day4input.txt");
        String[] lines = str.split("\n\n");

        Pattern pattern = Pattern.compile("(?<birthYear>byr:\\S+)|(?<issueYear>iyr:\\S+)|(?<expirationYear>eyr:\\S+)|(?<height>hgt:\\S+)|(?<hairColor>hcl:\\S+)|(?<eyeColor>ecl:\\S+)|(?<passportId>pid:\\S+)|(?<countryId>cid:\\S+)");
        List<Passport> passports = new ArrayList<>();
        for(String line : lines) {
            Map<PassportFields, String> map = new HashMap<>();
            line = line.replaceAll("\n", " ");
            Matcher m = pattern.matcher(line);
            if(m.lookingAt()) {
                map.put(PassportFields.valueOf(m.group().split(":")[0]), m.group().split(":")[1]);
                for(int i = 0; i < m.groupCount(); i++) {
                    if(m.find()) {
                        map.put(PassportFields.valueOf(m.group().split(":")[0]), m.group().split(":")[1]);
                    }
                }
            }
            if(validateMap(map)) {
                Passport passport = new Passport(
                        map.get(PassportFields.byr),
                        map.get(PassportFields.iyr),
                        map.get(PassportFields.eyr),
                        map.get(PassportFields.hgt),
                        map.get(PassportFields.hcl),
                        map.get(PassportFields.ecl),
                        map.get(PassportFields.pid),
                        map.get(PassportFields.cid)
                );
                passports.add(passport);
            }
        }
        System.out.println("Day 4 Part 1 Answer = " + passports.size());
        System.out.println("Day 4 Part 2 Answer = " + passports.stream().filter(p -> validatePassport(p)).count());
    }
}
