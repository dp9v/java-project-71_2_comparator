package hexlet.code.formatters;

import hexlet.code.common.DiffKeys;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static hexlet.code.common.DiffKeys.FIELD;
import static hexlet.code.common.DiffKeys.NEW_VALUE;
import static hexlet.code.common.DiffKeys.OLD_VALUE;
import static hexlet.code.common.DiffKeys.STATUS;
import static hexlet.code.common.DiffStatuses.ADDED;
import static hexlet.code.common.DiffStatuses.REMOVED;
import static hexlet.code.common.DiffStatuses.UPDATED;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlainFormatterTest {

    public static Stream<Arguments> formatterShouldReturnCorrectStringSource() {
        return Stream.of(
            Arguments.of(
                List.of(Map.of(FIELD, "key", STATUS, REMOVED, OLD_VALUE, 1)),
                "Property 'key' was removed\n"
            ),
            Arguments.of(
                List.of(Map.of(FIELD, "key", STATUS, ADDED, NEW_VALUE, 1)),
                "Property 'key' was added with value: 1\n"
            ),
            Arguments.of(
                List.of(Map.of(FIELD, "key", STATUS, UPDATED, OLD_VALUE, "value", NEW_VALUE, true)),
                "Property 'key' was updated. From 'value' to true\n"
            ),
            Arguments.of(
                List.of(Map.of(FIELD, "key", STATUS, ADDED, NEW_VALUE, List.of(1, 2, 3))),
                "Property 'key' was added with value: [complex value]\n"
            ),
            Arguments.of(
                List.of(Map.of(FIELD, "key", STATUS, ADDED, NEW_VALUE, Map.of("key1", "value"))),
                "Property 'key' was added with value: [complex value]\n"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("formatterShouldReturnCorrectStringSource")
    void formatterShouldReturnCorrectString(
        List<Map<DiffKeys, Object>> diff,
        String expectedResult
    ) {
        var formattedDiff = PlainFormatter.format(diff);
        assertEquals(expectedResult, formattedDiff);
    }
}
