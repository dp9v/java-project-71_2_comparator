package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @ParameterizedTest
    @MethodSource("readFileShouldReturnCorrectFileSource")
    void parseFileShouldReturnCorrectMap(String fileName, Map<String, Object> expectedResult) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        var fileUrl = classLoader.getResource(fileName);
        var file = new File(fileUrl.getFile());
        var result = Parser.parseFile(file);
        assertEquals(result, expectedResult);
    }


    public static Stream<Arguments> readFileShouldReturnCorrectFileSource() {
        var expectedMap = Map.of(
            "host", "hexlet.io",
            "timeout", 50,
            "proxy", "123.234.53.22",
            "follow", false,
            "array", List.of(1, 2, 3),
            "subJson", Map.of("data", "Test")
        );
        return Stream.of(
            Arguments.of("input_files/yaml/file.yaml", expectedMap),
            Arguments.of("input_files/json/file1.json", expectedMap)
        );
    }
}
