package DaySix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Code {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/" +
                "advent-of-code-2022/src/DaySix/PuzzleInput");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        String testdata = """
                zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
                """;

        //for (String line: testdata.split("\n")) {
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length()-14; i++) {
                boolean isPacket = true;
                HashMap<Character, Integer> hashmap = new HashMap<>();
                for (int p = 0; p < 14; p++) {
                    hashmap.put(line.charAt(i + p), hashmap.getOrDefault(line.charAt(i + p), 0) + 1);
                }

                for (int value : hashmap.values()) {
                    if (value != 1) {
                        isPacket = false;
                        break;
                    }
                }
                if (!isPacket) {
                    continue;
                }
                System.out.println(i + 14);
                break;
            }
        }
    }
}
