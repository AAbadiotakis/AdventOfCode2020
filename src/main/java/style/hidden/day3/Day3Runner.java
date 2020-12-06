package style.hidden.day3;

import style.hidden.FileReaderUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3Runner {

    static int treeCounter(int moveX, int moveY, Map<Integer, String> map, int maxRows) {
        int treeCounter = 0;
        int x = 0;
        int y = 0;
        while(true) {
            x += moveX;
            y += moveY;
            if (y >= maxRows) {
                return treeCounter;
            }
            String currentRow = map.get(y);
            while(x >= currentRow.length()) {
                currentRow += currentRow;
            }
            if(Character.toString(currentRow.charAt(x)).equals("#")) {
                treeCounter++;
            }
        }
    }


    public static void main(String... args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day3input.txt");
        Map<Integer, String> map = new HashMap<>();
        int i = 0;
        for(String line : lines) {
            map.put(i, line);
            i++;
        }

        int moveX3moveY1 = treeCounter(3, 1, map, i);
        System.out.println("Day 3 Part 1 Answer = " + moveX3moveY1);

        int moveX1moveY1 = treeCounter(1, 1, map, i);
        int moveX5moveY1 = treeCounter(5, 1, map, i);
        int moveX7moveY1 = treeCounter(7, 1, map, i);
        int moveX1moveY2 = treeCounter(1, 2, map, i);

        System.out.println("Day 3 Part 2 Answer = " + (moveX3moveY1 * moveX1moveY1 * moveX5moveY1 * moveX7moveY1 * moveX1moveY2));
    }
}
