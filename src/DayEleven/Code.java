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
        String actualData = "";
        String inputLine;
        HashMap<Integer, ArrayList<Long>> hashMap = new HashMap<>();
        HashMap<Integer, Long> monkeyBusiness = new HashMap<>();
        int monkey = 0;

        while ((inputLine = br.readLine()) != null) {
            actualData = actualData + inputLine + "\n";
        }
        for (String line : actualData.split("\n\n")) {
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
            for (String line : actualData.split("\n\n")) {
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
