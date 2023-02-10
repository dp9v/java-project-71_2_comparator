package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Differ {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String compare(File file1, File file2, String style) throws IOException {
        var json1 = readMapFromFile(file1);
        var json2 = readMapFromFile(file2);
        var compareResult = compare(json1, json2);
        return format(compareResult);
    }

    private static Map<String, Object> readMapFromFile(File file) throws IOException {
        return MAPPER.readValue(file, new TypeReference<>() {
        });
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
        if (value1 != null && value2 == null) {
            return List.of(Map.of("FIELD", key, "STATUS", "-", "VALUE", value1));
        }
        if (value1 == null && value2 != null) {
            return List.of(Map.of("FIELD", key, "STATUS", "+", "VALUE", value2));
        }
        if (value1.equals(value2)) {
            return List.of(Map.of("FIELD", key, "STATUS", " ", "VALUE", value1));
        }
        if (!value1.equals(value2)) {
            return List.of(
                Map.of("FIELD", key, "STATUS", "-", "VALUE", value1),
                Map.of("FIELD", key, "STATUS", "+", "VALUE", value2)
            );
        }
        return List.of();
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
