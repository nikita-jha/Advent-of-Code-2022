package DayTen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Code {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/" +
                "advent-of-code-2022/src/DayTen/PuzzleInput");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String testdata = """
                addx 15
                addx -11
                addx 6
                addx -3
                addx 5
                addx -1
                addx -8
                addx 13
                addx 4
                noop
                addx -1
                addx 5
                addx -1
                addx 5
                addx -1
                addx 5
                addx -1
                addx 5
                addx -1
                addx -35
                addx 1
                addx 24
                addx -19
                addx 1
                addx 16
                addx -11
                noop
                noop
                addx 21
                addx -15
                noop
                noop
                addx -3
                addx 9
                addx 1
                addx -3
                addx 8
                addx 1
                addx 5
                noop
                noop
                noop
                noop
                noop
                addx -36
                noop
                addx 1
                addx 7
                noop
                noop
                noop
                addx 2
                addx 6
                noop
                noop
                noop
                noop
                noop
                addx 1
                noop
                noop
                addx 7
                addx 1
                noop
                addx -13
                addx 13
                addx 7
                noop
                addx 1
                addx -33
                noop
                noop
                noop
                addx 2
                noop
                noop
                noop
                addx 8
                noop
                addx -1
                addx 2
                addx 1
                noop
                addx 17
                addx -9
                addx 1
                addx 1
                addx -3
                addx 11
                noop
                noop
                addx 1
                noop
                addx 1
                noop
                noop
                addx -13
                addx -19
                addx 1
                addx 3
                addx 26
                addx -30
                addx 12
                addx -1
                addx 3
                addx 1
                noop
                noop
                noop
                addx -9
                addx 18
                addx 1
                addx 2
                noop
                noop
                addx 9
                noop
                noop
                noop
                addx -1
                addx 2
                addx -37
                addx 1
                addx 3
                noop
                addx 15
                addx -21
                addx 22
                addx -6
                addx 1
                noop
                addx 2
                addx 1
                noop
                addx -10
                noop
                noop
                addx 20
                addx 1
                addx 2
                addx 2
                addx -6
                addx -11
                noop
                noop
                noop
                """;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int register = 1;
        int cycle =  0;
        int finalAnswer = 0;

        while ((line = br.readLine()) != null) {
        //for (String line : testdata.split("\n")) {
            String[] arr = line.split(" ");
            if (arr[0].equals("noop")) {
                cycle += 1;
                hashMap.put(cycle, register);
            } else if (arr[0].equals("addx")) {
                int changeAmount = Integer.parseInt(arr[1]);
                cycle += 1;
                hashMap.put(cycle, register);
                cycle += 1;
                hashMap.put(cycle, register);
                register += changeAmount;
            }
        }
        for (int i = 20; i < hashMap.size(); i += 40) {
            int signalStrength = i * hashMap.get(i);
            finalAnswer += signalStrength;
        }
        System.out.println(finalAnswer);

        //Part 2
        register=1;

        String[][] screen = new String[6][40];
        for (int row = 0; row < screen.length; row++) {
            for (int position = 0; position < screen[0].length; position++) {
                if (Math.abs(position - hashMap.get(register++)) <= 1) {
                    screen[row][position] = "#";
                } else {
                    screen[row][position] = " ";
                }
                System.out.print(screen[row][position]);
            }
            System.out.println();
        }
    }
}
