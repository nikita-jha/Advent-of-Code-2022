package DayEleven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Code {
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-" +
                "master/advent-of-code-2022/src/DayEleven/PuzzleInput");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String testData = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                                
                Monkey 1:
                  Starting items: 54, 65, 75, 74
                  Operation: new = old + 6
                  Test: divisible by 19
                    If true: throw to monkey 2
                    If false: throw to monkey 0
                                
                Monkey 2:
                  Starting items: 79, 60, 97
                  Operation: new = old * old
                  Test: divisible by 13
                    If true: throw to monkey 1
                    If false: throw to monkey 3
                                
                Monkey 3:
                  Starting items: 74
                  Operation: new = old + 3
                  Test: divisible by 17
                    If true: throw to monkey 0
                    If false: throw to monkey 1
                """;
        //String line;
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        HashMap<Integer, Integer> monkeyBusiness = new HashMap<>();
        int monkey = 0;

        //while ((line = br.readLine()) != null) {
        for (String line : testData.split("\n\n")) {
            String[] arr = line.split("\n");
            hashMap.put(monkey, new ArrayList<>());

            String startingItems = arr[1].replaceAll(",", "");
            String[] startingItemsArr = startingItems.split(" ");
            for (int i = 4; i < startingItemsArr.length; i++) {
                hashMap.get(monkey).add(Integer.parseInt(startingItemsArr[i]));
            }
            monkeyBusiness.put(monkey, hashMap.get(monkey).size());
            monkey++;
        }

        int round = 1;
        while (round++ <= 20) {
            monkey = 0;
            for (String line : testData.split("\n\n")) {
                String[] arr = line.split("\n");

                int worryLevel;

                String[] operationLine = arr[2].split(" ");
                String[] divisibleLine = arr[3].split(" ");
                String[] isTrue = arr[4].split(" ");
                String[] isFalse = arr[5].split(" ");


                for (int i = 0; i < hashMap.get(monkey).size(); i++) {
                    if (operationLine[7].equals("old")) {
                        worryLevel = operation(operationLine[6], hashMap.get(monkey).get(i), hashMap.get(monkey).get(i));
                    } else {
                        worryLevel = operation(operationLine[6], hashMap.get(monkey).get(i), Integer.parseInt(operationLine[7]));

                    }
                    worryLevel = Math.round(worryLevel / 3);
                    if (worryLevel % Integer.parseInt(divisibleLine[5]) == 0) {
                        hashMap.get(Integer.parseInt(isTrue[9])).add(worryLevel);
                        monkeyBusiness.replace(Integer.parseInt(isTrue[9]), monkeyBusiness.get(Integer.parseInt(isTrue[9])),
                                monkeyBusiness.get(Integer.parseInt(isTrue[9])) + 1);
                        hashMap.get(monkey).remove(i);
                        i--;
                    } else {
                        hashMap.get(Integer.parseInt(isFalse[9])).add(worryLevel);
                        monkeyBusiness.replace(Integer.parseInt(isFalse[9]), monkeyBusiness.get(Integer.parseInt(isFalse[9])),
                                monkeyBusiness.get(Integer.parseInt(isFalse[9])) + 1);
                        hashMap.get(monkey).remove(i);
                        i--;
                    }

                }
                monkey++;
            }
        }
        for (int i = 0; i < monkey; i++) {
            monkeyBusiness.replace(i, monkeyBusiness.get(i), monkeyBusiness.get(i) - hashMap.get(i).size());
        }
        int firstLargest = monkeyBusiness.get(0);
        int secondLargest = monkeyBusiness.get(1);

        for (int i : monkeyBusiness.values()) {
            if (i > firstLargest) {
                secondLargest = firstLargest;
                firstLargest = i;
            } else if (i > secondLargest) {
                secondLargest = i;
            }
        }
        System.out.println(firstLargest * secondLargest);

    }

    public static int operation(String str, int p, int q) {
        if (str.equals("*")) {
            return p * q;
        } else {
            return p + q;
        }
    }
}
