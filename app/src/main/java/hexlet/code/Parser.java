package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();

    public static Map<String, Object> parseFile(File file) throws IOException {
        var fileParts = file.getName().split("\\.");
        var fileType = fileParts[fileParts.length - 1];
        return switch (fileType) {
            case "json" -> readJson(file);
            case "yaml" -> readYaml(file);
            default -> throw new RuntimeException("Unknown file format: " + fileType);
        };
    }

    private static Map<String, Object> readJson(File file) throws IOException {
        return JSON_MAPPER.readValue(file, new TypeReference<>() {
        });
    }

    private static Map<String, Object> readYaml(File file) throws IOException {
        return YAML_MAPPER.readValue(file, new TypeReference<>() {
        });
    }
}
