package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {


    @Test
    void compare() throws IOException {
        System.out.println("!!!!Test");
        ClassLoader classLoader = getClass().getClassLoader();
        var expectedResultUrl = classLoader.getResource("expected_results/plain.txt");
        var fileUrl1 = classLoader.getResource("input_files/file1.json");
        var fileUrl2 = classLoader.getResource("input_files/file2.json");

        var file1 = new File(fileUrl1.getFile());
        var file2 = new File(fileUrl2.getFile());
        String result = Differ.compare(file1, file2, "stylish");
        var content = new String(expectedResultUrl.openStream().readAllBytes());
        assertEquals(result.trim(), content.trim());
    }
}
