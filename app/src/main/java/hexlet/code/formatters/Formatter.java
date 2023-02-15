package hexlet.code.formatters;

import hexlet.code.common.DiffKeys;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<DiffKeys, Object>> diff, String formatter) {
        return switch (formatter) {
            case "Stylish" -> StylishFormatter.format(diff);
            case "Plain" -> PlainFormatter.format(diff);
            default -> throw new RuntimeException("Unknown format: " + formatter);
        };
    }
}
