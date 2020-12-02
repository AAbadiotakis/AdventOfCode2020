package style.hidden.day1;

import style.hidden.FileReaderUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day1Runner {

    static Long part1(Set<Long> numbers) {
        for(Long firstNumber : numbers) {
            if(numbers.contains(2020 - firstNumber)) {
                return firstNumber * (2020 - firstNumber);
            }
        }
        return null;
    }

    static Long part2(Set<Long> numbers) {
        for(Long firstNumber : numbers) {
            for(Long secondNumber : numbers) {
                if(!firstNumber.equals(secondNumber)) {
                    if(numbers.contains(2020 - firstNumber - secondNumber)) {
                        return firstNumber * secondNumber * (2020 - firstNumber - secondNumber);
                    }
                }
            }
        }
        return null;
    }


    public static void main(String... args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day1input.txt");
        Set<Long> numbers = lines.parallelStream()
                .map(Long::parseLong)
                .collect(Collectors.toSet());
        System.out.println("Day 1 Part 1 Answer = " + part1(numbers));
        System.out.println("Day 1 Part 2 Answer = " + part2(numbers));
    }

}
