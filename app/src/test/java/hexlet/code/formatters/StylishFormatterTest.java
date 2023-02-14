package hexlet.code.formatters;

import hexlet.code.common.DiffKeys;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static hexlet.code.common.DiffKeys.FIELD;
import static hexlet.code.common.DiffKeys.STATUS;
import static hexlet.code.common.DiffKeys.VALUE;
import static hexlet.code.common.DiffStatuses.ADDED;
import static hexlet.code.common.DiffStatuses.REMOVED;
import static hexlet.code.common.DiffStatuses.SAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StylishFormatterTest {

    public static Stream<Arguments> formatterShouldReturnCorrectStringSource() {
        return Stream.of(
            Arguments.of(List.of(Map.of(FIELD, "key", STATUS, REMOVED, VALUE, 1)), "{\n  - key: 1\n}"),
            Arguments.of(List.of(Map.of(FIELD, "key", STATUS, ADDED, VALUE, 1)), "{\n  + key: 1\n}"),
            Arguments.of(List.of(Map.of(FIELD, "key", STATUS, SAME, VALUE, 1)), "{\n    key: 1\n}"),
            Arguments.of(List.of(Map.of(FIELD, "key", STATUS, SAME, VALUE, "s")), "{\n    key: s\n}"),
            Arguments.of(List.of(Map.of(FIELD, "key", STATUS, SAME, VALUE, true)), "{\n    key: true\n}"),
            Arguments.of(
                List.of(Map.of(FIELD, "key", STATUS, SAME, VALUE, List.of(1, 2, 3))),
                "{\n    key: [1, 2, 3]\n}"
            ),
            Arguments.of(
                List.of(Map.of(FIELD, "key", STATUS, SAME, VALUE, Map.of("key1", "value"))),
                "{\n    key: {key1=value}\n}"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("formatterShouldReturnCorrectStringSource")
    void formatterShouldReturnCorrectString(
        List<Map<DiffKeys, Object>> diff,
        String expectedResult
    ) {
        var formattedDiff = StylishFormatter.format(diff);
        assertEquals(expectedResult, formattedDiff);
    }
}
