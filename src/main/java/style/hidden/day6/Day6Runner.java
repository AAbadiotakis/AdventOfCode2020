package style.hidden.day6;

import style.hidden.FileReaderUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day6Runner {
    public static void main(String... args) throws Exception {
        String input = FileReaderUtil.parseFileAsString("day6input.txt");
        String[] lines = input.split("\n\n");
        int yesCounter = 0;
        int allYesCounter = 0;
        for(String line : lines) {
            List<Character> chars = line.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            long numPeople = chars.stream().filter(ch -> Character.toString(ch).equals("\n")).count() + 1;
            Map<Character, Long> map = new HashMap<>();
            for(Character c : chars.stream().filter(ch -> !Character.toString(ch).equals("\n")).collect(Collectors.toList())) {
                if(map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1L);

                }
                if(map.get(c) == numPeople) {
                    allYesCounter++;
                }
            }
            yesCounter += chars.stream().filter(ch -> !Character.toString(ch).equals("\n")).distinct().count();
        }
        System.out.println("Day 6 Part 1 Answer = " + yesCounter);
        System.out.println("Day 6 Part 2 Answer = " + allYesCounter);

    }
}
