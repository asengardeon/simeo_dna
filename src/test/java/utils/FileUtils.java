package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;


public class FileUtils {

    public String[] readFileLines(String fileName) {
        String path = "src/test/resources/"+fileName;

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8);
        } catch (IOException e) {

            // do something
            e.printStackTrace();
        }
        return lines.toArray(new String[0]);
    }
}


