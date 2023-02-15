package hexlet.code;

import hexlet.code.common.DiffKeys;

import java.util.ArrayList;
import java.util.HashMap;
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
            var keyCompareResults = new HashMap<DiffKeys, Object>();
            keyCompareResults.put(FIELD, key);

            if (!file2.containsKey(key)) {
                keyCompareResults.put(STATUS, REMOVED);
                keyCompareResults.put(OLD_VALUE, value1);
            } else if (!file1.containsKey(key)) {
                keyCompareResults.put(STATUS, ADDED);
                keyCompareResults.put(NEW_VALUE, value2);
            } else if (Objects.equals(value1, value2)) {
                keyCompareResults.put(STATUS, SAME);
                keyCompareResults.put(OLD_VALUE, value1);
            } else {
                keyCompareResults.put(STATUS, UPDATED);
                keyCompareResults.put(OLD_VALUE, value1);
                keyCompareResults.put(NEW_VALUE, value2);
            }
            result.add(keyCompareResults);
        }
        return result;
    }
}
