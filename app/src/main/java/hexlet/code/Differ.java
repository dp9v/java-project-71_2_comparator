package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import java.io.IOException;
import java.util.Map;

public class Differ {

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    public static String generate(String file1, String file2, String formatterType) throws Exception {
        var json1 = readFile(file1);
        var json2 = readFile(file2);
        var compareResult = Comparator.compare(json1, json2);
        return Formatter.format(compareResult, formatterType);
    }

    private static Map<String, Object> readFile(String filePath) throws IOException {
        var normalizePath = normalizePath(filePath);
        var format = getFormat(filePath);

        var content = Files.readString(normalizePath);

        return Parser.parse(content, format);
    }

    private static Path normalizePath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

    private static String getFormat(String filePath) {
        var pathParts = filePath.split("\\.");
        return pathParts[pathParts.length - 1];
    }
}
