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
import static hexlet.code.common.DiffKeys.STATUS;
import static hexlet.code.common.DiffKeys.OLD_VALUE;
import static hexlet.code.common.DiffStatuses.ADDED;
import static hexlet.code.common.DiffStatuses.REMOVED;
import static hexlet.code.common.DiffStatuses.SAME;
import static hexlet.code.common.DiffStatuses.UPDATED;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StylishFormatterTest {

    public static Stream<Arguments> formatterShouldReturnCorrectStringSource() {
        return Stream.of(
            Arguments.of(List.of(Map.of(FIELD, "key", STATUS, REMOVED, OLD_VALUE, 1)), "{\n  - key: 1\n}"),
            Arguments.of(List.of(Map.of(FIELD, "key", STATUS, ADDED, NEW_VALUE, 1)), "{\n  + key: 1\n}"),
            Arguments.of(List.of(Map.of(FIELD, "key", STATUS, SAME, OLD_VALUE, 1)), "{\n    key: 1\n}"),
            Arguments.of(List.of(Map.of(FIELD, "key", STATUS, SAME, OLD_VALUE, "s")), "{\n    key: s\n}"),
            Arguments.of(List.of(Map.of(FIELD, "key", STATUS, SAME, OLD_VALUE, true)), "{\n    key: true\n}"),
            Arguments.of(
                List.of(Map.of(FIELD, "key", STATUS, SAME, OLD_VALUE, List.of(1, 2))),
                "{\n    key: [1, 2]\n}"
            ),
            Arguments.of(
                List.of(Map.of(FIELD, "key", STATUS, SAME, OLD_VALUE, Map.of("key1", "value"))),
                "{\n    key: {key1=value}\n}"
            ),
            Arguments.of(List.of(
                Map.of(FIELD, "key", STATUS, UPDATED, OLD_VALUE, 1, NEW_VALUE, 2)),
                "{\n  - key: 1\n  + key: 2\n}"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("formatterShouldReturnCorrectStringSource")
    void formatterShouldReturnCorrectString(
        List<Map<DiffKeys, Object>> diff,
        String expectedResult
    ) throws Exception {
        var formattedDiff = Formatter.format(diff, "stylish");
        assertEquals(expectedResult, formattedDiff);
    }
}
