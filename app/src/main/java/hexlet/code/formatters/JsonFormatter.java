package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.common.DiffKeys;

import java.util.List;
import java.util.Map;

public class JsonFormatter {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    public static String format(List<Map<DiffKeys, Object>> diff) throws Exception {
        return JSON_MAPPER.writeValueAsString(diff);
    }
}
