package hexlet.code;

import hexlet.code.common.DiffKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

import static hexlet.code.common.DiffKeys.FIELD;
import static hexlet.code.common.DiffKeys.NEW_VALUE;
import static hexlet.code.common.DiffKeys.OLD_VALUE;
import static hexlet.code.common.DiffKeys.STATUS;
import static hexlet.code.common.DiffStatuses.ADDED;
import static hexlet.code.common.DiffStatuses.REMOVED;
import static hexlet.code.common.DiffStatuses.SAME;
import static hexlet.code.common.DiffStatuses.UPDATED;

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
        if (value2 == null) {
            result.add(Map.of(FIELD, key, STATUS, REMOVED, OLD_VALUE, value1));
        } else if (value1 == null) {
            result.add(Map.of(FIELD, key, STATUS, ADDED, NEW_VALUE, value2));
        } else if (Objects.equals(value1, value2)) {
            result.add(Map.of(FIELD, key, STATUS, SAME, OLD_VALUE, value1));
        } else {
            result.add(Map.of(FIELD, key, STATUS, UPDATED, OLD_VALUE, value1, NEW_VALUE, value2));
        }
        return result;
    }
}
