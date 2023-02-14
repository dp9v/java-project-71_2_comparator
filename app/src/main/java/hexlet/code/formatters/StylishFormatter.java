package hexlet.code.formatters;

import hexlet.code.common.DiffKeys;
import hexlet.code.common.DiffStatuses;

import java.util.List;
import java.util.Map;

import static hexlet.code.common.DiffKeys.FIELD;
import static hexlet.code.common.DiffKeys.STATUS;
import static hexlet.code.common.DiffKeys.VALUE;

public class StylishFormatter {
    public static String format(List<Map<DiffKeys, Object>> diff) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<DiffKeys, Object> diffLine : diff) {
            var fieldStatus = (DiffStatuses) diffLine.get(STATUS);
            var fieldName = (String) diffLine.get(FIELD);
            var fieldValue = diffLine.get(VALUE);
            result.append("  ")
                .append(fieldStatus.value)
                .append(" ")
                .append(fieldName)
                .append(": ")
                .append(fieldValue).append("\n");
        }
        return result.append("}").toString();
    }
}
