package style.hidden.day9;

import style.hidden.FileReaderUtil;

import java.util.Arrays;
import java.util.List;

public class Day9Runner {

    public static void main(String... args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day9input.txt");
        List<String> previousValues = Arrays.asList(new String[25]);
        int i = 0;
        String day9part1answer = null;
        for(String line : lines) {
            if( i < 25) {
                // No need to check first 25 values before populating the List
                previousValues = setPreviousValue(line, previousValues);
                i++;
            } else {
                Long value = Long.parseLong(line);
                boolean hasSum = false;
                for(String previousValue : previousValues) {
                    if(Long.parseLong(previousValue) < value) {
                        Long missingValue = value - Long.parseLong(previousValue);
                        hasSum = previousValues.stream().anyMatch(l -> l.equals(Long.toString(missingValue)));
                        if(hasSum) {
                            break;
                        }
                    }
                }
                if(!hasSum) {
                    day9part1answer = line;
                    System.out.println("Day 9 Part 1 Answer = " + day9part1answer);
                    break;
                } else {
                    setPreviousValue(line, previousValues);
                }
            }
        }

        for(int l = 0; l < lines.size(); l++) {
            Long day9part1Long = Long.valueOf(day9part1answer);
            Long valueCheck = new Long(day9part1Long);
            int lineOffset = 0;
            while(valueCheck > 0) {
                valueCheck -= Long.valueOf(lines.get(l + lineOffset));
                lineOffset++;
            }
            if(valueCheck == 0) {
                Long smallestValue = Long.MAX_VALUE;
                Long largestValue = Long.MIN_VALUE;
                // lineOffset is 1 too high after the last while loop runs (setting valueCheck == 0)
                // Thus we use < vs <=
                for(int j = 0; j < lineOffset; j++) {
                    Long currentValue = Long.valueOf(lines.get(l + j));
                    if(smallestValue > currentValue) {
                        smallestValue = currentValue;
                    }
                    if(currentValue > largestValue) {
                        largestValue = currentValue;
                    }
                }
                System.out.println("Day 9 Part 2 Answer = " + (smallestValue + largestValue));
                return;
            }
        }
    }



    static List<String> setPreviousValue(String value, List<String> previousValues) {
        for(int i = previousValues.size() - 2; i >= 0; i--) {
            String str = previousValues.get(i);
            previousValues.set(i + 1, str);
        }
        previousValues.set(0, value);
        return previousValues;
    }
}
