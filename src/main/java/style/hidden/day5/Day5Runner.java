package style.hidden.day5;

import style.hidden.FileReaderUtil;

import java.util.*;

public class Day5Runner {

    static long seatId(String line) {
        int row = 0;
        int rowsToAccountFor = 128;
        int seat = 0;
        int seatsToAccountFor = 8;

        for(int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if(Character.toString(c).equals("F") || Character.toString(c).equals("B")) {
                rowsToAccountFor = rowsToAccountFor >> 1;
                if(Character.toString(c).equals("B")) {
                    row += rowsToAccountFor;
                }
            } else if(Character.toString(c).equals("L") || Character.toString(c).equals("R")) {
                seatsToAccountFor = seatsToAccountFor >> 1;
                if(Character.toString(c).equals("R")) {
                    seat += seatsToAccountFor;
                }
            }
        }
        return (long) row * 8 + seat;
    }

    public static void main(String... args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day5input.txt");
        Set<Long> seatIdSet = new HashSet<>();
        for(String line : lines) {
            seatIdSet.add(seatId(line));
        }
        System.out.println("Day 5 Part 1 Answer = " + seatIdSet.stream().mapToLong(l -> l).max().getAsLong());
        Long previousSeatId = null;
        for(long l : seatIdSet) {
            if (previousSeatId != null) {
                if (l - previousSeatId > 1) {
                    System.out.println("Day 5 Part 2 Answer = " + (l - 1));
                }
            }
            previousSeatId = l;
        }

    }
}
