package DayFour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Code {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/" +
                "advent-of-code-2022/src/DayFour/PuzzleInput");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        String testdata = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """;
        int totalCount = 0;
        //for (String line : testdata.split("\n")) {
        while ((line=br.readLine()) != null) {
            String[] arr = line.split(",");
            String[] firstElf = arr[0].split("-");
            String[] secondElf = arr[1].split("-");

            if (Integer.parseInt(firstElf[0]) <= Integer.parseInt(secondElf[0]) &&
                    (Integer.parseInt(firstElf[1]) >= Integer.parseInt(secondElf[1]))) {
                totalCount++;
            } else if (Integer.parseInt(firstElf[0]) >= Integer.parseInt(secondElf[0]) &&
                    (Integer.parseInt(firstElf[1]) <= Integer.parseInt(secondElf[1]))) {
                totalCount++;
            }
        }
        System.out.println(totalCount);

        String line2;
        BufferedReader br2 = new BufferedReader(new FileReader(file));

        int overlapCount = 0;
        while ((line2 = br2.readLine()) != null) {
        //for (String line2 : testdata.split("\n")) {
            String[] arr = line2.split(",");
            String[] firstElf = arr[0].split("-");
            String[] secondElf = arr[1].split("-");

            if (Integer.parseInt(firstElf[1]) >= Integer.parseInt(secondElf[0]) &&
            Integer.parseInt(secondElf[1]) >= Integer.parseInt(firstElf[0])) {
                overlapCount++;
            }
        }
        System.out.println(overlapCount);
    }
}
