package style.hidden.day1;

import style.hidden.FileReaderUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Runner {

    public static void main(String... args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day1input.txt");
        List<Long> numbers = lines.parallelStream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        part1loop:
        for(Long firstNumber : numbers) {
            for(Long secondNumber : numbers) {
                if(!firstNumber.equals(secondNumber)) {
                    if((firstNumber + secondNumber) == 2020) {
                        System.out.println("First Number: " + firstNumber);
                        System.out.println("Second Number: " + secondNumber);
                        System.out.println("Day1Part1 Answer: " + firstNumber * secondNumber);
                        break part1loop;
                    }
                }
            }
        }

        System.out.println("\n-----\n");

        part2loop:
        for(Long firstNumber : numbers) {
            for(Long secondNumber : numbers) {
                for(Long thirdNumber : numbers) {
                    if(!firstNumber.equals(secondNumber) && !firstNumber.equals(thirdNumber) && !secondNumber.equals(thirdNumber)) {
                        if((firstNumber + secondNumber + thirdNumber) == 2020) {
                            System.out.println("First Number: " + firstNumber);
                            System.out.println("Second Number: " + secondNumber);
                            System.out.println("Third Number: " + thirdNumber);
                            System.out.println("Day1Part2 Answer: " + firstNumber * secondNumber * thirdNumber);
                            break part2loop;
                        }
                    }
                }
            }
        }


    }

}
