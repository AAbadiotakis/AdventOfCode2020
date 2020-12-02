package style.hidden;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderUtil {

    public static List<String> parseFile(String fileName) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return Files.readAllLines(Paths.get(classLoader.getResource(fileName).toURI()));
    }
}
