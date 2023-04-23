package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String expectedResultJson;
    private static String expectedResultPlain;
    private static String expectedResultStylish;

    public static Path getAbsolutePath(String fileName) {
        return Paths.get("./src/test/resources/", fileName).toAbsolutePath().normalize();
    }

    public static String readFile(String fileName) throws IOException {
        Path filePath = getAbsolutePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void setup() throws IOException {
        expectedResultJson = readFile("expected_results/json.json");
        expectedResultPlain = readFile("expected_results/plain.txt");
        expectedResultStylish = readFile("expected_results/stylish.txt");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerateWithDefaultOutput(String inputFormat) throws Exception {
        var file1 = getAbsolutePath("input_files/file1." + inputFormat).toString();
        var file2 = getAbsolutePath("input_files/file2." + inputFormat).toString();

        String actualResult = Differ.generate(file1, file2);
        assertEquals(actualResult, expectedResultStylish);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerateWithStylishOutput(String inputFormat) throws Exception {
        var file1 = getAbsolutePath("input_files/file1." + inputFormat).toString();
        var file2 = getAbsolutePath("input_files/file2." + inputFormat).toString();

        String actualResult = Differ.generate(file1, file2, "stylish");
        assertEquals(actualResult, expectedResultStylish);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerateWithPlainOutput(String inputFormat) throws Exception {
        var file1 = getAbsolutePath("input_files/file1." + inputFormat).toString();
        var file2 = getAbsolutePath("input_files/file2." + inputFormat).toString();

        String actualResult = Differ.generate(file1, file2, "plain");
        assertEquals(actualResult, expectedResultPlain);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerateWithJsonOutput(String inputFormat) throws Exception {
        var file1 = getAbsolutePath("input_files/file1." + inputFormat).toString();
        var file2 = getAbsolutePath("input_files/file2." + inputFormat).toString();

        String actualResult = Differ.generate(file1, file2, "json");

        JSONAssert.assertEquals(expectedResultJson, actualResult, false);

    }
}
