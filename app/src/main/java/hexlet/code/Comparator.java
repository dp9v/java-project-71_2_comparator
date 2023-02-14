package hexlet.code;

import hexlet.code.common.DiffKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static hexlet.code.common.DiffKeys.FIELD;
import static hexlet.code.common.DiffKeys.STATUS;
import static hexlet.code.common.DiffKeys.VALUE;
import static hexlet.code.common.DiffStatuses.ADDED;
import static hexlet.code.common.DiffStatuses.REMOVED;
import static hexlet.code.common.DiffStatuses.SAME;

public class Comparator {
    public static List<Map<DiffKeys, Object>> compare(Map<String, Object> file1, Map<String, Object> file2) {
        var result = new ArrayList<Map<DiffKeys, Object>>();

        var allKeys = new TreeSet<>(file1.keySet());
        allKeys.addAll(file2.keySet());

        for (String key : allKeys) {
            var value1 = file1.get(key);
            var value2 = file2.get(key);
            result.addAll(compareValues(key, value1, value2));
        }
        return result;
    }

    private static List<Map<DiffKeys, Object>> compareValues(String key, Object value1, Object value2) {
        var result = new ArrayList<Map<DiffKeys, Object>>();
        if (value1 != null && value2 == null) {
            result.add(Map.of(FIELD, key, STATUS, REMOVED, VALUE, value1));
        } else if (value1 == null && value2 != null) {
            result.add(Map.of(FIELD, key, STATUS, ADDED, VALUE, value2));
        } else if (value1.equals(value2)) {
            result.add(Map.of(FIELD, key, STATUS, SAME, VALUE, value1));
        } else if (!value1.equals(value2)) {
            result.add(Map.of(FIELD, key, STATUS, REMOVED, VALUE, value1));
            result.add(Map.of(FIELD, key, STATUS, ADDED, VALUE, value2));
        }
        return result;
    }
}
