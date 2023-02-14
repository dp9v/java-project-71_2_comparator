package hexlet.code;

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

class ComparatorTest {

    public static Stream<Arguments> compareShouldReturnCorrectMapSource() {
        var list = List.of(1, 2, 3);
        var map = Map.of("key", "value");
        return Stream.of(
            Arguments.of(
                Map.of("key", list),
                Map.of(),
                List.of(Map.of(FIELD, "key", STATUS, REMOVED, VALUE, list))),
            Arguments.of(
                Map.of(),
                Map.of("key", list),
                List.of(Map.of(FIELD, "key", STATUS, ADDED, VALUE, list))),
            Arguments.of(
                Map.of("key", map),
                Map.of("key", list),
                List.of(
                    Map.of(FIELD, "key", STATUS, REMOVED, VALUE, map),
                    Map.of(FIELD, "key", STATUS, ADDED, VALUE, list)
                )),
            Arguments.of(
                Map.of("key", list),
                Map.of("key", List.of(1, 2, 3)),
                List.of(Map.of(FIELD, "key", STATUS, SAME, VALUE, list))
            )
        );
    }

    @ParameterizedTest
    @MethodSource("compareShouldReturnCorrectMapSource")
    void compareShouldReturnCorrectMap(
        Map<String, Object> input1,
        Map<String, Object> input2,
        List<Map<DiffKeys, Object>> expectedResult
    ) {
        var compareResult = Comparator.compare(input1, input2);
        assertEquals(compareResult, expectedResult);
    }
}
