package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Differ {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void compare(File file1, File file2, String style) throws IOException {
        var json1 = readMapFromFile(file1);
        var json2 = readMapFromFile(file2);
        var compareResult = Comparator.compare(json1, json2);
        System.out.println(format(compareResult));
    }

    public static Map<String, Object> readMapFromFile(File file) throws IOException {
        return MAPPER.readValue(file, new TypeReference<>() {
        });
    }

    public static String format(List<Map<String, Object>> fields) {
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
        return result.toString();
    }
}
