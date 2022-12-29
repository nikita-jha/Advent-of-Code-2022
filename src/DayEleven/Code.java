package DayEleven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
public class Code {
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-" +
                "master/advent-of-code-2022/src/DayEleven/PuzzleInput");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String testData = """
                Monkey 0:
                  Starting items: 99, 63, 76, 93, 54, 73
                  Operation: new = old * 11
                  Test: divisible by 2
                    If true: throw to monkey 7
                    If false: throw to monkey 1
                                
                Monkey 1:
                  Starting items: 91, 60, 97, 54
                  Operation: new = old + 1
                  Test: divisible by 17
                    If true: throw to monkey 3
                    If false: throw to monkey 2
                                
                Monkey 2:
                  Starting items: 65
                  Operation: new = old + 7
                  Test: divisible by 7
                    If true: throw to monkey 6
                    If false: throw to monkey 5
                                
                Monkey 3:
                  Starting items: 84, 55
                  Operation: new = old + 3
                  Test: divisible by 11
                    If true: throw to monkey 2
                    If false: throw to monkey 6
                                
                Monkey 4:
                  Starting items: 86, 63, 79, 54, 83
                  Operation: new = old * old
                  Test: divisible by 19
                    If true: throw to monkey 7
                    If false: throw to monkey 0
                                
                Monkey 5:
                  Starting items: 96, 67, 56, 95, 64, 69, 96
                  Operation: new = old + 4
                  Test: divisible by 5
                    If true: throw to monkey 4
                    If false: throw to monkey 0
                                
                Monkey 6:
                  Starting items: 66, 94, 70, 93, 72, 67, 88, 51
                  Operation: new = old * 5
                  Test: divisible by 13
                    If true: throw to monkey 4
                    If false: throw to monkey 5
                                
                Monkey 7:
                  Starting items: 59, 59, 74
                  Operation: new = old + 8
                  Test: divisible by 3
                    If true: throw to monkey 1
                    If false: throw to monkey 3
                """;
        //String line;
        HashMap<Integer, ArrayList<Long>> hashMap = new HashMap<>();
        HashMap<Integer, Long> monkeyBusiness = new HashMap<>();
        int monkey = 0;

        //while ((line = br.readLine()) != null) {
        for (String line : testData.split("\n\n")) {
            String[] arr = line.split("\n");
            hashMap.put(monkey, new ArrayList<>());

            String startingItems = arr[1].replaceAll(",", "");
            String[] startingItemsArr = startingItems.split(" ");
            for (int i = 4; i < startingItemsArr.length; i++) {
                hashMap.get(monkey).add(Long.parseLong(startingItemsArr[i]));
            }
            monkeyBusiness.put(monkey, (long) hashMap.get(monkey).size());
            monkey++;
        }

        long round = 1;
        while (round++ <= 10000) {
            monkey = 0;
            for (String line : testData.split("\n\n")) {
                String[] arr = line.split("\n");

                long worryLevel;

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
                    //worryLevel = Math.round(worryLevel / 3);
                    worryLevel = worryLevel % 9699690;
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
        long firstLargest = Integer.MIN_VALUE;
        long secondLargest = Integer.MIN_VALUE;

        for (long i : monkeyBusiness.values()) {
            if (i > firstLargest) {
                secondLargest = firstLargest;
                firstLargest = i;
            } else if (i > secondLargest) {
                secondLargest = i;
            }
        }
        System.out.println(firstLargest * secondLargest);

    }

    public static long operation(String str, long p, long q) {
        if (str.equals("*")) {
            return (p * q);
        } else {
            return (p + q);
        }
    }
}
