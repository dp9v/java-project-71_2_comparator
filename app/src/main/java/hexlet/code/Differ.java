package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Differ {

    public static String compare(File file1, File file2, String style) throws IOException {
        var json1 = Parser.parseFile(file1);
        var json2 = Parser.parseFile(file2);
        var compareResult = compare(json1, json2);
        return format(compareResult);
    }

    private static List<Map<String, Object>> compare(Map<String, Object> json1, Map<String, Object> json2) {
        var result = new ArrayList<Map<String, Object>>();

        var allKeys = new TreeSet<>(json1.keySet());
        allKeys.addAll(json2.keySet());

        for (String key : allKeys) {
            var value1 = json1.get(key);
            var value2 = json2.get(key);
            result.addAll(compare(key, value1, value2));
        }
        return result;
    }

    private static List<Map<String, Object>> compare(String key, Object value1, Object value2) {
        var result = new ArrayList<Map<String, Object>>();
        if (value1 != null && value2 == null) {
            result.add(Map.of("FIELD", key, "STATUS", "-", "VALUE", value1));
        } else if (value1 == null && value2 != null) {
            result.add(Map.of("FIELD", key, "STATUS", "+", "VALUE", value2));
        } else if (value1.equals(value2)) {
            result.add(Map.of("FIELD", key, "STATUS", " ", "VALUE", value1));
        } else if (!value1.equals(value2)) {
            result.add(Map.of("FIELD", key, "STATUS", "-", "VALUE", value1));
            result.add(Map.of("FIELD", key, "STATUS", "+", "VALUE", value2));
        }
        return result;
    }

    private static String format(List<Map<String, Object>> fields) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> field : fields) {
            String fieldStatus = (String) field.get("STATUS");
            String fieldName = (String) field.get("FIELD");
            Object fieldValue = field.get("VALUE");
            result.append("  ")
                .append(fieldStatus)
                .append(" ")
                .append(fieldName)
                .append(": ")
                .append(fieldValue).append("\n");
        }
        return result.append("}").toString();
    }
}
