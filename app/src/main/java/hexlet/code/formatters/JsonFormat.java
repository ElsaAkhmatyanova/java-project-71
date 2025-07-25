package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffEntry;

import java.util.List;

public final class JsonFormat implements Formatter {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public String format(List<DiffEntry> diffEntries) throws Exception {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(diffEntries);
    }
}
