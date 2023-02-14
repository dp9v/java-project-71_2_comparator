package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.File;
import java.io.IOException;

public class Differ {

    public static String compare(File file1, File file2) throws IOException {
        return compare(file1, file2, FormatterTypes.STYLISH);
    }

    public static String compare(File file1, File file2, FormatterTypes formatterType) throws IOException {
        var json1 = Parser.parseFile(file1);
        var json2 = Parser.parseFile(file2);
        var compareResult = Comparator.compare(json1, json2);
        return Formatter.format(compareResult, formatterType);
    }
}
