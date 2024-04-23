package IndexacionYVisualizacion;

public class IndexFile implements Comparable<IndexFile> {
    private String name;
    private String path;

    public IndexFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    @Override
    public int compareTo(IndexFile other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + " (" + path + ")";
    }
}