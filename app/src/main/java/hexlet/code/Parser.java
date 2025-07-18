package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parseFromFile(String path) throws IOException {
        String content = Files.readString(Path.of(path));
        String ext = path.substring(path.lastIndexOf('.') + 1);
        TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {
            // type ref
        };
        return switch (ext) {
            case "json" -> JSON_MAPPER.readValue(content, typeRef);
            case "yml", "yaml" -> YAML_MAPPER.readValue(content, typeRef);
            default -> throw new IllegalArgumentException("Unsupported format: " + ext);
        };
    }
}
