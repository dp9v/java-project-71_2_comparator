package hexlet.code.formatters;

import hexlet.code.common.DiffKeys;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<DiffKeys, Object>> diff, String formatter) {
        return switch (formatter) {
            case "stylish" -> StylishFormatter.format(diff);
            case "plain" -> PlainFormatter.format(diff);
            default -> throw new RuntimeException("Unknown format: " + formatter);
        };
    }
}
