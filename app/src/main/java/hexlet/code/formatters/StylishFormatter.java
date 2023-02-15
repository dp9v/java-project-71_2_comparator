package hexlet.code.formatters;

import hexlet.code.common.DiffKeys;
import hexlet.code.common.DiffStatuses;

import java.util.List;
import java.util.Map;

import static hexlet.code.common.DiffKeys.FIELD;
import static hexlet.code.common.DiffKeys.NEW_VALUE;
import static hexlet.code.common.DiffKeys.OLD_VALUE;
import static hexlet.code.common.DiffKeys.STATUS;

public class StylishFormatter {
    public static final String REMOVED_LINE_FORMAT = "  - %s: %s\n";
    public static final String ADD_LINE_FORMAT = "  + %s: %s\n";
    public static final String SAME_LINE_FORMAT = "    %s: %s\n";
    public static final String UPDATED_LINE_FORMAT = "  - %s: %s\n  + %s: %s\n";

    public static String format(List<Map<DiffKeys, Object>> diff) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<DiffKeys, Object> diffLine : diff) {
            var fieldStatus = (DiffStatuses) diffLine.get(STATUS);
            var fieldName = (String) diffLine.get(FIELD);
            var oldFieldValue = diffLine.get(OLD_VALUE);
            var newFieldValue = diffLine.get(NEW_VALUE);
            result.append(
                switch (fieldStatus) {
                    case REMOVED -> REMOVED_LINE_FORMAT.formatted(fieldName, oldFieldValue);
                    case ADDED -> ADD_LINE_FORMAT.formatted(fieldName, newFieldValue);
                    case SAME -> SAME_LINE_FORMAT.formatted(fieldName, oldFieldValue);
                    case UPDATED -> UPDATED_LINE_FORMAT.formatted(fieldName, oldFieldValue, fieldName, newFieldValue);
                }
            );
        }
        return result.append("}").toString();
    }
}
