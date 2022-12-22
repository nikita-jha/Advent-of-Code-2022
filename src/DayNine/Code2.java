package DayNine;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Code2 {

    static Point HEAD = new Point(0, 0);
    static Point P1 = new Point(0, 0);
    static Point P2 = new Point(0, 0);
    static Point P3 = new Point(0, 0);
    static Point P4 = new Point(0, 0);
    static Point P5 = new Point(0, 0);
    static Point P6 = new Point(0, 0);
    static Point P7 = new Point(0, 0);
    static Point P8 = new Point(0, 0);
    static Point TAIL = new Point(0, 0);
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/advent-of-" +
                "code-2022/src/DayNine/PuzzleInput");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String testdata = """
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20
                """;

        Set<Point> set = new HashSet<>();
        set.add(new Point(TAIL.x, TAIL.y));

        Map<String, int[]> dict = new HashMap<>();
        dict.put("U", new int[] {0,1});
        dict.put("D", new int[] {0,-1});
        dict.put("L", new int[] {-1,0});
        dict.put("R", new int[] {1,0});


        while ((line = br.readLine()) != null) {
        //for (String line : testdata.split("\n")) {
            String[] arr = line.split(" ");
            int moveAmount = Integer.parseInt(arr[1]);
            int[] arrPoints = dict.get(arr[0]);
            for (int i = 0; i < moveAmount; i++) {
                HEAD.setLocation(HEAD.getX() + arrPoints[0], HEAD.getY() + arrPoints[1]);
                move(HEAD, P1);
                move(P1, P2);
                move(P2, P3);
                move(P3, P4);
                move(P4, P5);
                move(P5, P6);
                move(P6, P7);
                move(P7, P8);
                move(P8, TAIL);
                set.add(new Point(TAIL.x, TAIL.y));
            }


        }

        System.out.println(set.size());

    }

    public static boolean touching(int hx, int hy, int tx, int ty) {
        return (Math.abs(hx - tx) <= 1 && Math.abs(hy - ty) <= 1);
    }

    public static void move(Point p1, Point p2) {
        if (!touching(p1.x, p1.y, p2.x, p2.y)) {
            int sign_x = p1.x == p2.x ? 0 : (p1.x - p2.x) / Math.abs(p1.x - p2.x);
            int sign_y = p1.y == p2.y ? 0 : (p1.y - p2.y) / Math.abs(p1.y - p2.y);
            p2.setLocation(p2.x + sign_x, p2.y + sign_y);
        }
    }
}
