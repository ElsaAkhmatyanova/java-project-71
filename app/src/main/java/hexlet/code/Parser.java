package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String content, String dataType) throws IOException {
        return switch (dataType) {
            case "json" -> JSON_MAPPER.readValue(content, new TypeReference<>() {});
            case "yaml", "yml" -> YAML_MAPPER.readValue(content, new TypeReference<>() {});
            default -> throw new IllegalArgumentException("Unsupported format: " + dataType);
        };
    }
}
