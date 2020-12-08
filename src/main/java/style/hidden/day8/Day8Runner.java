package style.hidden.day8;

import style.hidden.FileReaderUtil;

import java.util.ArrayList;
import java.util.List;

public class Day8Runner {
    public static void main(String... args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day8input.txt");
        int accumulator = 0;
        List<Integer> linesAlreadyRun = new ArrayList<>();
        int i = 0;
        while(true) {
            if(linesAlreadyRun.contains(i)) {
                System.out.println("Day 8 Part 1 Answer = " + accumulator);
                break;
            } else {
                linesAlreadyRun.add(i);
            }
            String instruction = lines.get(i).split(" ")[0].trim();
            String value = lines.get(i).split(" ")[1].trim();
            boolean isPositive = value.contains("+");
            value = value.replaceAll("\\+", "").replaceAll("-", "");
            if(instruction.equals("acc")) {
                if(isPositive) {
                    accumulator += Integer.parseInt(value);
                } else {
                    accumulator -= Integer.parseInt(value);
                }
                i++;
            } else if(instruction.equals("jmp")) {
                if(isPositive) {
                    i += Integer.parseInt(value);
                } else {
                    i -= Integer.parseInt(value);
                }
            } else if(instruction.equals("nop")) {
                i++;
            }
        }

        // Reset
        i = 0;
        accumulator = 0;
        linesAlreadyRun = new ArrayList<>();
        List<Integer> linesAttemptedToBeFlipped = new ArrayList<>();

        boolean flippedAnInstruction = false;
        Integer instructionFlipped = null;

        while(i < lines.size()) {
            if(linesAlreadyRun.contains(i)) {
                i = 0;
                flippedAnInstruction = false;
                accumulator = 0;
                linesAlreadyRun.clear();
            } else {
                linesAlreadyRun.add(i);
                String instruction = lines.get(i).split(" ")[0].trim();
                String value = lines.get(i).split(" ")[1].trim();
                boolean isPositive = value.contains("+");
                value = value.replaceAll("\\+", "").replaceAll("-", "");
                if(instruction.equals("acc")) {
                    if(isPositive) {
                        accumulator += Integer.parseInt(value);
                    } else {
                        accumulator -= Integer.parseInt(value);
                    }
                    i++;
                } else if(instruction.equals("jmp")) {
                    if(!flippedAnInstruction && !linesAttemptedToBeFlipped.contains(i)) {
                        linesAttemptedToBeFlipped.add(i);
                        flippedAnInstruction = true;
                        instructionFlipped = i;
                        i++;
                    } else {
                        if(isPositive) {
                            i += Integer.parseInt(value);
                        } else {
                            i -= Integer.parseInt(value);
                        }
                    }
                } else if(instruction.equals("nop")) {
                    if(!flippedAnInstruction && !linesAttemptedToBeFlipped.contains(i)) {
                        linesAttemptedToBeFlipped.add(i);
                        flippedAnInstruction = true;
                        instructionFlipped = i;

                        // Assume its a JMP now
                        if(isPositive) {
                            i += Integer.parseInt(value);
                        } else {
                            i -= Integer.parseInt(value);
                        }
                    }
                    i++;
                }
            }
        }
        System.out.println("Day 8 Part 2 Answer = " + accumulator);
    }
}
