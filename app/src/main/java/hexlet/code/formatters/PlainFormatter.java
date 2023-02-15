package hexlet.code.formatters;

import hexlet.code.common.DiffKeys;
import hexlet.code.common.DiffStatuses;

import java.util.List;
import java.util.Map;

import static hexlet.code.common.DiffKeys.FIELD;
import static hexlet.code.common.DiffKeys.NEW_VALUE;
import static hexlet.code.common.DiffKeys.OLD_VALUE;
import static hexlet.code.common.DiffKeys.STATUS;

public class PlainFormatter {
    public static final String REMOVED_LINE_FORMAT = "Property '%s' was removed\n";
    public static final String ADD_LINE_FORMAT = "Property '%s' was added with value: %s\n";
    public static final String UPDATED_LINE_FORMAT = "Property '%s' was updated. From %s to %s\n";

    public static String format(List<Map<DiffKeys, Object>> diff) {
        StringBuilder result = new StringBuilder();
        for (Map<DiffKeys, Object> diffLine : diff) {
            var fieldStatus = (DiffStatuses) diffLine.get(STATUS);
            var fieldName = (String) diffLine.get(FIELD);
            var oldFieldValue = diffLine.get(OLD_VALUE);
            var newFieldValue = diffLine.get(NEW_VALUE);
            result.append(
                switch (fieldStatus) {
                    case REMOVED -> REMOVED_LINE_FORMAT.formatted(fieldName);
                    case ADDED -> ADD_LINE_FORMAT.formatted(fieldName, format(newFieldValue));
                    case UPDATED ->
                        UPDATED_LINE_FORMAT.formatted(fieldName, format(oldFieldValue), format(newFieldValue));
                    case SAME -> "";
                }
            );
        }
        return result.toString();
    }

    public static String format(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }
}
