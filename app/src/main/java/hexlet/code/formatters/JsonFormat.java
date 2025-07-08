package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffEntry;

import java.util.List;

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
