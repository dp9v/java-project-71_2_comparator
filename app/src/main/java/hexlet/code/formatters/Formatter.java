package hexlet.code.formatters;

import hexlet.code.FormatterTypes;
import hexlet.code.common.DiffKeys;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<DiffKeys, Object>> diff, FormatterTypes formatter) {
        return switch (formatter) {
            case STYLISH -> StylishFormatter.format(diff);
        };
    }
}
