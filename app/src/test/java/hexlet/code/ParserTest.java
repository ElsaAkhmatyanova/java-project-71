package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ParserTest {

    private Path tempFile;

    @AfterEach
    void tearDown() throws IOException {
        if (tempFile != null && Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
    }

    @Test
    void testParseJsonFile() throws IOException {
        String jsonContent = "{\"name\":\"John\"}";
        tempFile = Files.createTempFile("test", ".json");
        Files.writeString(tempFile, jsonContent);

        Map<String, Object> result = Parser.parseFromFile(tempFile.toString());

        assertEquals("John", result.get("name"));
    }

    @Test
    void testParseYamlFile() throws IOException {
        String yamlContent = "name: John";
        tempFile = Files.createTempFile("test", ".yaml");
        Files.writeString(tempFile, yamlContent);

        Map<String, Object> result = Parser.parseFromFile(tempFile.toString());

        assertEquals("John", result.get("name"));
    }

    @Test
    void testUnsupportedFileExtension() throws IOException {
        String txtContent = "Just some text";
        tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, txtContent);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class, () -> Parser.parseFromFile(tempFile.toString()));

        assertEquals("Unsupported format: txt", exception.getMessage());
    }

    @Test
    void testFileNotFoundThrowsIOException() {
        String invalidPath = "non_existing_file.json";
        assertThrows(IOException.class, () -> Parser.parseFromFile(invalidPath));
    }
}
