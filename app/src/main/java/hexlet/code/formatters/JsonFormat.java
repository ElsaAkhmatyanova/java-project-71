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

public class JsonFormat implements Formatter{

    @Override
    public String format(List<DiffEntry> diff) throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(diff);
    }

    public static Map<String, Object> parse(String filePath) throws Exception {
        Path fileAbsPath = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(fileAbsPath)) {
            throw new Exception("File '" + fileAbsPath + "' does not exist");
        }
        String fileContent = Files.readString(fileAbsPath);
        return mapper.readValue(fileContent, new TypeReference<Map<String, Object>>() {
        });
    }
}
