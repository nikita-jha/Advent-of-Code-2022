package DayThree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Code {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/" +
                "advent-of-code-2022/src/DayThree/PuzzleInput");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        String alphabet = "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String testdata = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """;
        int totalPriority = 0;
        while ((line = br.readLine()) != null) {
            //for (String line : testdata.split("\n")){
            int mid = line.length() / 2;
            String[] parts = {line.substring(0, mid), line.substring(mid)};
            Set<Integer> scores = new HashSet<>();
            for (int i = 0; i < parts[0].length(); i++) {
                for (int j = 0; j < parts[1].length(); j++) {
                    if (parts[0].charAt(i) == parts[1].charAt(j)) {
                        scores.add(alphabet.indexOf(parts[0].charAt(i)));
                    }
                }
            }
            totalPriority += scores.stream().mapToInt(Integer :: intValue).sum();
        }
        System.out.println(totalPriority);

        //Part 2
        BufferedReader br2 = new BufferedReader(new FileReader(file));
        String line2;
        int totalPriority2 = 0;
        ArrayList<String> totalList = new ArrayList<>();
        while ((line2 = br2.readLine()) != null) {
            totalList.add(line2);
        }
        for (int i = 0; i <= totalList.size()-3; i+=3) {
            Set<Integer> scores2 = new HashSet<>();
            for (int j = 0; j < totalList.get(i).length(); j++) {
                if (totalList.get(i+1).contains(Character.toString(totalList.get(i).charAt(j)))
                        && totalList.get(i+2).contains(Character.toString(totalList.get(i).charAt(j)))) {
                    scores2.add(alphabet.indexOf(totalList.get(i).charAt(j)));
                }
            }
            totalPriority2 += scores2.stream().mapToInt(Integer::intValue).sum();
        }
        System.out.println(totalPriority2);
    }
}