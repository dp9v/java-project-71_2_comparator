package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Comparator {

    public static List<Map<String, Object>> compare(Map<String, Object> json1, Map<String, Object> json2) {
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
        if (value1==null && value2!=null) {
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
}
