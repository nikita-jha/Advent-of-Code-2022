package DayFive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Code {

    public static void main(String[] args) throws Exception{
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/" +
                "advent-of-code-2022/src/DayFive/PuzzleInput");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String testdata = """
                    [D]   
                [N] [C]   
                [Z] [M] [P]
                 1   2   3
                                
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """;
        String line;

        int numLines = 8;
        int numStacks = 9;
        int lineCount = 0;
        Deque<String> stacklines = new ArrayDeque<>();
        List<Deque<String>> stacks = new ArrayList<>();
        List<Integer> stackIdx = new ArrayList<>();

        while ((line = br.readLine()) != null) {

        //for (String line : testdata.split("\n")) {
            if (line.isBlank()) {
                continue;
            }

            if (lineCount < numLines) {
                stacklines.push(line);
                lineCount++;
                continue;
            }

            if (stackIdx.isEmpty()) {
                for (int i = 1; i < numStacks + 1; i++) {
                    stackIdx.add(line.indexOf("" + i));
                }
                lineCount++;
                continue;
            }

            if (stacks.isEmpty()) {
                for (int j = 0; j < numStacks; j++) {
                    stacks.add(new ArrayDeque<>());
                }
            }

            String stackLine;
            while (!stacklines.isEmpty()) {
                stackLine = stacklines.pop();
                for (int p = 0; p < numStacks; p++) {
                    if (stackIdx.get(p) < stackLine.length()) {
                        String boxID = stackLine.substring(stackIdx.get(p), stackIdx.get(p)+1);
                        if (!boxID.isBlank()) {
                            stacks.get(p).push(boxID);
                        }
                    }
                }
            }

            Pattern movePattern = Pattern.compile("^move (\\d+) from (\\d+) to (\\d+)$");
            Matcher m = movePattern.matcher(line);

            //part 1
//            if (m.find()) {
//                int numToMove = Integer.parseInt(m.group(1));
//                int moveFrom = Integer.parseInt(m.group(2));
//                int moveTo = Integer.parseInt(m.group(3));
//                for (int w = 0; w < numToMove; w++) {
//                    String boxToMove = stacks.get(moveFrom-1).pop();
//                    stacks.get(moveTo-1).push(boxToMove);
//                }
//            }

            //part 2
            if (m.find()) {
                int numToMove = Integer.parseInt(m.group(1));
                int moveFrom = Integer.parseInt(m.group(2));
                int moveTo = Integer.parseInt(m.group(3));
                Deque<String> cratesToMove = new ArrayDeque<>();
                for (int w = 0; w < numToMove; w++) {
                    cratesToMove.push(stacks.get(moveFrom-1).pop());
                }
                while (!cratesToMove.isEmpty()) {
                    stacks.get(moveTo-1).push(cratesToMove.pop());
                }
            }
            lineCount++;
        }

        for (int i = 0; i < stacks.size(); i++) {
            System.out.print(stacks.get(i).peek());
        }
    }
}
