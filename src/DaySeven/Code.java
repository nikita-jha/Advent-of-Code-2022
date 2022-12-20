package DaySeven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Code {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/nikitajha/Documents/NikitaFinal/Coding Projects/CompetitionCode-master/advent-of-" +
                "code-2022/src/DaySeven/PuzzleInput");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        String testdata = """
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k
                """;

        List<DirectoryEntry> allDirectories = new ArrayList<>();
        DirectoryEntry rootDirectory = new DirectoryEntry(null, "/");
        allDirectories.add(rootDirectory);
        DirectoryEntry currentDirectory = rootDirectory;


        //for (String line: testdata.split("\n")) {
        while ((line = br.readLine()) != null) {
            String[] linearr = line.split(" ");

            if ("$".equals(linearr[0])) {
                if ("cd".equals(linearr[1])) {
                    if ("/".equals(linearr[2])) {
                        currentDirectory = rootDirectory;
                    } else if ("..".equals(linearr[2])) {
                        currentDirectory = currentDirectory.getParent();
                    } else {
                        currentDirectory = currentDirectory.getDir(linearr[2]);
                    }
                }
            } else if ("dir".equals(linearr[0])) {
                DirectoryEntry de = new DirectoryEntry(currentDirectory, linearr[1]);
                currentDirectory.addFile(de);
                allDirectories.add(de);
            } else {
                currentDirectory.addFile(new FileEntry(linearr[1], Long.parseLong(linearr[0])));
            }

        }

        //part 1
        long count = 0;
        for (DirectoryEntry de : allDirectories) {
            if (de.size() < 100000) {
                count += de.size() ;
            }
        }

        System.out.println(count);

        //part 2
        long usedSpace = allDirectories.get(0).size();
        long unusedSpace = 70000000 - usedSpace;
        long sizeToDelete = 30000000 - unusedSpace;

        long min = Integer.MAX_VALUE;
        for (DirectoryEntry de : allDirectories) {
            if (de.size() > sizeToDelete && de.size() < min) {
                min = de.size();
            }
        }
        System.out.println(min);




    }

}
