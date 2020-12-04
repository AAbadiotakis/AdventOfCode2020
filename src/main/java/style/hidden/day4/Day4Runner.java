package style.hidden.day4;

import style.hidden.FileReaderUtil;

import java.util.List;

public class Day4Runner {

    public static void main(String... args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day4input.txt");
        int validPassportCounter = 0;
        int strictlyValidPassportCounter = 0;
        String birthYear = null;
        String issueYear = null;
        String expirationYear = null;
        String height = null;
        String hairColor = null;
        String eyeColor = null;
        String passportId = null;
        String countryId = null;

        for(String line : lines) {
            if(line.isBlank() || line.isEmpty()) {
                if( birthYear != null &&
                    issueYear != null &&
                    expirationYear != null &&
                    height != null &&
                    hairColor != null &&
                    eyeColor != null &&
                    passportId != null
                ) {
                    validPassportCounter++;
                    if(birthYear.matches("[0-9]{4}") && Integer.parseInt(birthYear) >= 1920 && Integer.parseInt(birthYear) <= 2002) {
                        if(issueYear.matches("[0-9]{4}") && Integer.parseInt(issueYear) >= 2010 && Integer.parseInt(issueYear) <= 2020) {
                            if(expirationYear.matches("[0-9]{4}") && Integer.parseInt(expirationYear) >= 2020 && Integer.parseInt(expirationYear) <= 2030) {
                                if(
                                        (height.contains("cm") && Integer.parseInt(height.replace("cm","")) >= 150 && Integer.parseInt(height.replace("cm", "")) <= 193) ||
                                        (height.contains("in") && Integer.parseInt(height.replace("in", "")) >= 59 && Integer.parseInt(height.replace("in", "")) <= 76)
                                ) {
                                    if(hairColor.matches("#[0-9a-f]{6}")) {
                                        if(eyeColor.equals("amb") || eyeColor.equals("blu") || eyeColor.equals("brn") || eyeColor.equals("gry") || eyeColor.equals("grn") || eyeColor.equals("hzl") || eyeColor.equals("oth")) {
                                            if(passportId.matches("[0-9]{9}")) {
                                                strictlyValidPassportCounter++;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }

                birthYear = null;
                issueYear = null;
                expirationYear = null;
                height = null;
                hairColor = null;
                eyeColor = null;
                passportId = null;
                countryId = null;
            } else {
                String[] keyValuePair = line.split(" ");
                for(String str : keyValuePair) {
                    String key = str.split(":")[0];
                    String value = str.split(":")[1];
                    if(key.equals("byr")) {
                        birthYear = value;
                    } else if(key.equals("iyr")) {
                        issueYear = value;
                    } else if(key.equals("eyr")) {
                        expirationYear = value;
                    } else if(key.equals("hgt")) {
                        height = value;
                    } else if(key.equals("hcl")) {
                        hairColor = value;
                    } else if(key.equals("ecl")) {
                        eyeColor = value;
                    } else if(key.equals("pid")) {
                        passportId = value;
                    } else if(key.equals("cid")) {
                        countryId = value;
                    }
                }
            }

        }
        System.out.println("Day 4 Part 1 Answer = " + validPassportCounter);
        // Was 213
        System.out.println("Day 4 Part 2 Answer = " + strictlyValidPassportCounter);

    }
}
