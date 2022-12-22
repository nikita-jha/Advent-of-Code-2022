//Part 1
package DayEight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Code {

    public static void main(String[] args) throws  Exception {

        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/advent-of-" +
                "code-2022/src/DayEight/PuzzleInput");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;

        String testData = """
                30373
                25512
                65332
                33549
                35390
                """;

        int lineCounter = 0;
        int finalAnswer = 0;
        List<ArrayList<Integer>> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
        //for (String line : testData.split("\n")) {
            String[] arr = line.split("");
            if (lineCounter == 0) {
                for (int i = 1; i < arr.length - 1; i++) {
                    ArrayList<Integer> list1 = new ArrayList<>();
                    list1.add(Integer.parseInt(arr[i]));
                    list.add(list1);
                }
            } else {
                int counter2 = 0;
                for (int i = 1; i < arr.length - 1; i++) {
                    list.get(counter2++).add(Integer.parseInt(arr[i]));
                }
            }
            lineCounter++;
        }

        BufferedReader br2 = new BufferedReader(new FileReader(file));

        String line2;
        int linecounter2 = 0;

        //for (String line2: testData.split("\n")) {
        while ((line2 = br2.readLine()) != null) {
            if (linecounter2 > 0 && linecounter2 < list.get(0).size()-1) {
                ArrayList<Integer> horizontal = new ArrayList<>();
                String[] arr = line2.split("");
                for (String str : arr) {
                    horizontal.add(Integer.parseInt(str));
                }

                int counter2 = 0;
                for (int i = 1; i < arr.length - 1; i++) {
                    boolean horizontalVisible = check(Integer.parseInt(arr[i]), horizontal, i);
                    boolean verticalVisible = check(Integer.parseInt(arr[i]), list.get(counter2++), linecounter2);
                    if (horizontalVisible || verticalVisible) {
                        finalAnswer++;
                    }
                }
            }
            linecounter2++;
        }

        System.out.println(finalAnswer + 2 * list.get(0).size() + list.size() * 2);

    }

    public static boolean check(int num, List<Integer> list, int index) {
        boolean first = true;
        boolean second = true;
        for (int i = 0; i < index; i++) {
            if (list.get(i) >= num) {
                first = false;
                break;
            }
        }
        for (int i = index+1; i < list.size(); i++) {
            if (list.get(i) >= num) {
                second = false;
                break;
            }
        }
        return first || second;
    }

}