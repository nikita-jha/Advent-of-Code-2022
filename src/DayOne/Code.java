package DayOne;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Code {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/" +
                "advent-of-code-2022/src/DayOne/PuzzleInput");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int sum = 0, firstL = Integer.MIN_VALUE, secondL = Integer.MIN_VALUE, thirdL = Integer.MIN_VALUE;
        while ((st = br.readLine()) != null) {
            if (st.isEmpty()) {
                if (sum > firstL) {
                    thirdL = secondL;
                    secondL = firstL;
                    firstL = sum;
                } else if (sum > secondL) {
                    thirdL = secondL;
                    secondL = sum;
                } else if (sum > thirdL) {
                    thirdL = sum;
                }
                sum = 0;
                continue;
            }
            sum += Integer.parseInt(st);
        }
        System.out.println(firstL + secondL + thirdL + "");

    }

}
