package IndexacionYVisualizacion;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileIndexer {
    private List<IndexFile> indexedFiles = new ArrayList<>();

    public void indexFiles(String directoryPath) {
        File directory = new File(directoryPath);
        indexFilesRecursive(directory);
    }

    private void indexFilesRecursive(File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                indexFilesRecursive(file);
            } else {
                indexedFiles.add(new IndexFile(file.getName(), file.getAbsolutePath()));
            }
        }
    }

    public List<IndexFile> searchFiles(String name) {
        List<IndexFile> result = new ArrayList<>();
        for (IndexFile indexFile : indexedFiles) {
            if (indexFile.getName().equals(name)) {
                result.add(indexFile);
            }
        }
        return result;
    }

    public List<IndexFile> listFiles() {
        List<IndexFile> sortedFiles = new ArrayList<>(indexedFiles);
        Collections.sort(sortedFiles);
        return sortedFiles;
    }
}