package lab7.task2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import static java.nio.file.FileVisitResult.*;

/**
 * Counter for java files of a given directory using java.nio API
 */
public class JavaFilesVisitor extends SimpleFileVisitor<Path> {

    private ArrayList<Path> javaFiles = new ArrayList<>();

    public final ArrayList<Path> getJavaFiles() {
        return javaFiles;
    }

    /**
     * TODO - override the visitFile(Path file, BasicFileAttributes attr) method of the SimpleFileVisitor.
     * Add to javaFiles all the Java related files: the ones with .java and .class extensions.
     *
     * @return
     */
    // Afișați numărul de fișiere cu extensia ”.class” sau ”.java”
    // și numele acestora din directorul specificat în schelet.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr){
        if((file.toString().endsWith(".class")) || (file.toString().endsWith(".java"))
                && (!attr.isDirectory())){
            javaFiles.add(file);
        }
        return CONTINUE;
    }

    public static void main(String[] args) throws IOException {

        Path startingDir = Paths.get("./src/lab7");
        JavaFilesVisitor filesVisitor = new JavaFilesVisitor();
        /*
         * The walkFileTree methods does a depth-first traversal of a directory, starting from startingDir.
         * When it reaches a file, the visitFile method is invoked on the currently visited file.
         */
        try {
            Files.walkFileTree(startingDir, filesVisitor);
            ArrayList<Path> javaFiles = filesVisitor.getJavaFiles();

            // TODO: Print the number of files visited and their names
            for(Path file : javaFiles){
                System.out.println(file);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
