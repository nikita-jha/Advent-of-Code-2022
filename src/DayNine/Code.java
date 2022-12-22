package DayNine;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Code {

    static Point HEAD = new Point(0, 0);
    static Point TAIL = new Point(0, 0);
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/advent-of-" +
                "code-2022/src/DayNine/PuzzleInput");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String testdata = """
                R 4
                U 4
                L 3
                D 1
                R 4
                D 1
                L 5
                R 2
                """;

        Set<Point> set = new HashSet<>();
        set.add(new Point(TAIL.x, TAIL.y));

        Map<String, int[]> hashmap = new HashMap<>();
        hashmap.put("U", new int[] {0,1});
        hashmap.put("D", new int[] {0,-1});
        hashmap.put("L", new int[] {-1,0});
        hashmap.put("R", new int[] {1,0});


        while ((line = br.readLine()) != null) {
        //for (String line : testdata.split("\n")) {
            String[] arr = line.split(" ");
            int moveAmount = Integer.parseInt(arr[1]);
            int[] arrPoints = hashmap.get(arr[0]);
            for (int i = 0; i < moveAmount; i++) {
                move(arrPoints[0], arrPoints[1]);
                set.add(new Point(TAIL.x, TAIL.y));
            }

        }
        //System.out.println(set);
        System.out.println(set.size());

    }

    public static boolean touching(int hx, int hy, int tx, int ty) {
        return (Math.abs(hx - tx) <= 1 && Math.abs(hy - ty) <= 1);
    }

    public static void move(int xMove, int yMove) {
        HEAD.setLocation(HEAD.getX() + xMove, HEAD.getY() + yMove);

        if (!touching(HEAD.x, HEAD.y, TAIL.x, TAIL.y)) {
            if (HEAD.x - TAIL.x == 0 || HEAD.y - TAIL.y == 0) {
                TAIL.setLocation(TAIL.x + xMove, TAIL.y + yMove);
            } else {
                int tailxMove = (HEAD.x - TAIL.x) / (Math.abs(HEAD.x - TAIL.x));
                int tailyMove = (HEAD.y - TAIL.y) / (Math.abs(HEAD.y - TAIL.y));
                TAIL.setLocation(TAIL.x + tailxMove, TAIL.y + tailyMove);
            }
        }
    }
}
