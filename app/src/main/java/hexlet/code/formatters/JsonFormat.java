package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffEntry;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class JsonFormat implements Formatter {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public String format(List<DiffEntry> diffEntries) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(diffEntries);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert diff to JSON", e);
        }
    }
}
