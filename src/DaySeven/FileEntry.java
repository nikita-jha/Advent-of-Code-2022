package DaySeven;

public class FileEntry {
    private final long size;
    private final String name;

    public FileEntry(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public long size() {
        return size;
    }

    public String getName() {
        return name;
    }



}
