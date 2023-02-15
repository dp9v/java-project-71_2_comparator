package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {


    @Test
    void compare() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        var expectedResultUrl = classLoader.getResource("expected_results/plain.txt");
        var expectedContent = Files.readString(Path.of(expectedResultUrl.toURI()));

        var fileFullPath1 = classLoader.getResource("input_files/json/file1.json").getPath();
        var fileFullPath2 = classLoader.getResource("input_files/json/file2.json").getPath();

        String result = Differ.generate(fileFullPath1, fileFullPath2);
        assertEquals(result.trim(), expectedContent.trim());
    }
}
