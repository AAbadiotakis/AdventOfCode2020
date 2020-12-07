package style.hidden.day7;

import style.hidden.FileReaderUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7Runner {

    record BagHolder(String bagColor, Integer numberBags) {}

    static boolean recursiveBagSearch(String bagColor, Map<String, List<BagHolder>> map) {
        if(!map.containsKey(bagColor)) {
            return false;
        }
        List<BagHolder> bagHolders = map.get(bagColor);
        for(BagHolder bagHolder : bagHolders) {
//            System.out.println(bagHolder);
            if(bagHolder.bagColor.equals("shiny gold")) {
                return true;
            } else {
                if(recursiveBagSearch(bagHolder.bagColor, map)) {
                    return true;
                }
            }
        }
        return false;
    }

    static int recursiveBagCount(String bagColor, Map<String, List<BagHolder>> map) {
        int totalCount = 0;
        if(!map.containsKey(bagColor)) {
            return totalCount;
        }
        List<BagHolder> bagHolders = map.get(bagColor);
        for(BagHolder bagHolder : bagHolders) {
            totalCount += bagHolder.numberBags + (bagHolder.numberBags * recursiveBagCount(bagHolder.bagColor, map));
        }
        return totalCount;
    }

    public static void main(String... args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day7input.txt");
        Map<String, List<BagHolder>> map = new HashMap<>();
        for(String line : lines) {
            String bagColor = line.split("bag[s]*")[0];
            String contains = line.split("contain")[1];
            String[] containedBags = contains.split(",");
            List<BagHolder> bagHolders = new ArrayList<>();
            for(String containedBag : containedBags) {
                containedBag = containedBag.trim();
                if(!containedBag.equals("no other bags.")) {
                    Integer bagAmount = Integer.parseInt(containedBag.split(" ")[0]);
                    String bagName = containedBag.split(String.valueOf(bagAmount))[1].trim().split("bag[s]*")[0].trim();
                    BagHolder bagHolder = new BagHolder(bagName, bagAmount);
                    bagHolders.add(bagHolder);
                }
            }
            map.put(bagColor.trim(), bagHolders);
        }
        int shinyGoldBagCounter = 0;
        for(String key : map.keySet()) {
            if(recursiveBagSearch(key, map)) {
                shinyGoldBagCounter++;
            }
        }
        System.out.println("Day 7 Part 1 Answer = " + shinyGoldBagCounter);
        System.out.println("Day 7 Part 2 Answer = " + recursiveBagCount("shiny gold", map));
    }
}
