package hexlet.code.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class JsonParser {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> parse (String filePath) throws Exception {
        Path fileAbsPath = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(fileAbsPath)) {
            throw new Exception("File '" + fileAbsPath + "' does not exist");
        }
        String fileContent = Files.readString(fileAbsPath);
        return mapper.readValue(fileContent, new TypeReference<Map<String,Object>>(){});
    }
}
