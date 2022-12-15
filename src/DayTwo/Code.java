package DayTwo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Code {

    public static void main(String[] args) throws Exception {

        //Part 1
        File file = new File ("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/" +
                "advent-of-code-2022/src/DayTwo/PuzzleInput");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        int totalScore = 0;
        while ((st = br.readLine()) != null) {
            String[] arr = st.split(" ");
            if (arr[1].equals("X")) {
                totalScore += 1;
            } else if (arr[1].equals("Y")) {
                totalScore += 2;
            } else if (arr[1].equals("Z")) {
                totalScore += 3;
            }

            if (arr[1].equals("X") && arr[0].equals("C")) {
                totalScore += 6;
            } else if (arr[1].equals("X") && arr[0].equals("A")) {
                totalScore += 3;
            } else if (arr[1].equals("Y") && arr[0].equals("A")) {
                totalScore += 6;
            } else if (arr[1].equals("Y") && arr[0].equals("B")) {
                totalScore += 3;
            } else if (arr[1].equals("Z") && arr[0].equals("B")) {
                totalScore += 6;
            } else if (arr[1].equals("Z") && arr[0].equals("C")) {
                totalScore += 3;
            }
        }

        System.out.println(totalScore);

        //Part 2
        String str;
        int totalScore2 = 0;
        BufferedReader br2 = new BufferedReader(new FileReader(file));
        while ((str = br2.readLine()) != null) {
            String[] arr = str.split(" ");
            switch (arr[1]) {
                case "X":
                    switch (arr[0]) {
                        case "A" -> totalScore2 += 3;
                        case "B" -> totalScore2 += 1;
                        case "C" -> totalScore2 += 2;
                    }
                    break;
                case "Y":
                    switch (arr[0]) {
                        case "A" -> totalScore2 += 3 + 1;
                        case "B" -> totalScore2 += 3 + 2;
                        case "C" -> totalScore2 += 3 + 3;
                    }
                    break;
                case "Z":
                    switch (arr[0]) {
                        case "A" -> totalScore2 += 6 + 2;
                        case "B" -> totalScore2 += 6 + 3;
                        case "C" -> totalScore2 += 6 + 1;
                    }

                    break;
            }
        }
        System.out.println(totalScore2);;
    }
}
