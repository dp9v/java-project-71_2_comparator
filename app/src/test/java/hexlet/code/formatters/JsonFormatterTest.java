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
import static hexlet.code.common.DiffStatuses.SAME;
import static hexlet.code.common.DiffStatuses.UPDATED;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFormatterTest {

    public static Stream<Arguments> formatterShouldReturnCorrectStringSource() {
        return Stream.of(
            Arguments.of(
                List.of(Map.of(FIELD, "key")),
                "[{\"FIELD\":\"key\"}]"
            ), Arguments.of(
                List.of(Map.of(STATUS, ADDED)),
                "[{\"STATUS\":\"ADDED\"}]"
            ), Arguments.of(
                List.of(Map.of(STATUS, SAME)),
                "[{\"STATUS\":\"SAME\"}]"
            ), Arguments.of(
                List.of(Map.of(STATUS, UPDATED)),
                "[{\"STATUS\":\"UPDATED\"}]"
            ), Arguments.of(
                List.of(Map.of(STATUS, REMOVED)),
                "[{\"STATUS\":\"REMOVED\"}]"
            ), Arguments.of(
                List.of(Map.of(NEW_VALUE, List.of(1, 2))),
                "[{\"NEW_VALUE\":[1,2]}]"
            ), Arguments.of(
                List.of(Map.of(OLD_VALUE, Map.of("key1", "value"))),
                "[{\"OLD_VALUE\":{\"key1\":\"value\"}}]"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("formatterShouldReturnCorrectStringSource")
    void formatterShouldReturnCorrectString(
        List<Map<DiffKeys, Object>> diff,
        String expectedResult
    ) throws Exception {
        var formattedDiff = Formatter.format(diff, "json");
        assertEquals(expectedResult, formattedDiff);
    }
}
