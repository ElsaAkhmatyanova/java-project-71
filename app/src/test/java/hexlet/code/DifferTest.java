package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class DifferTest {
    private static final String DIFF_RESULT_JSON_FROM_JSON_FILE =
            readFileToStringFromResources("fixtures/expected_results/json_result_for_json_file.txt");
    private static final String DIFF_RESULT_PLAIN_FROM_JSON_FILE =
            readFileToStringFromResources("fixtures/expected_results/plain_result_for_json_file.txt");
    private static final String DIFF_RESULT_STYLISH_FROM_JSON_FILE =
            readFileToStringFromResources("fixtures/expected_results/stylish_result_for_json_file.txt");
    private static final String DIFF_RESULT_STYLISH_FROM_YML_FILE =
            readFileToStringFromResources("fixtures/expected_results/stylish_result_for_yml_file.txt");

    private static String readFileToStringFromResources(String resourcesFilePath) {
        try {
            return Files.readString(getResourseFilePath(resourcesFilePath));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Path getResourseFilePath(String path) {
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
            assert resource != null;
            return Path.of(resource.toURI());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    @SneakyThrows
    void testStylishFormatWithJsonFile() {
        Path filePath1 = getResourseFilePath("fixtures/files/file1.json");
        Path filePath2 = getResourseFilePath("fixtures/files/file2.json");
        String diffResult =
                Differ.generate(
                        filePath1.toAbsolutePath().normalize().toString(),
                        filePath2.toAbsolutePath().normalize().toString(),
                        "stylish");
        assertEquals(
                DIFF_RESULT_STYLISH_FROM_JSON_FILE.trim().replaceAll("\\s+", ""),
                diffResult.trim().replaceAll("\\s+", ""));
    }

    @Test
    @SneakyThrows
    void testPlainFormatWithJsonFile() {
        Path filePath1 = getResourseFilePath("fixtures/files/file1.json");
        Path filePath2 = getResourseFilePath("fixtures/files/file2.json");
        String diffResult =
                Differ.generate(
                        filePath1.toAbsolutePath().normalize().toString(),
                        filePath2.toAbsolutePath().normalize().toString(),
                        "plain");
        assertEquals(
                DIFF_RESULT_PLAIN_FROM_JSON_FILE.trim().replaceAll("\\s+", ""),
                diffResult.trim().replaceAll("\\s+", ""));
    }

    @Test
    @SneakyThrows
    void testJsonFormatWithJsonFile() {
        Path filePath1 = getResourseFilePath("fixtures/files/file1.json");
        Path filePath2 = getResourseFilePath("fixtures/files/file2.json");
        String diffResult =
                Differ.generate(
                        filePath1.toAbsolutePath().normalize().toString(),
                        filePath2.toAbsolutePath().normalize().toString(),
                        "json");
        assertEquals(
                DIFF_RESULT_JSON_FROM_JSON_FILE.trim().replaceAll("\\s+", ""),
                diffResult.trim().replaceAll("\\s+", ""));
    }

    @Test
    @SneakyThrows
    void testStylishFormatWithYmlFile() {
        Path filePath1 = getResourseFilePath("fixtures/files/file1.yml");
        Path filePath2 = getResourseFilePath("fixtures/files/file2.yml");
        String diffResult =
                Differ.generate(
                        filePath1.toAbsolutePath().normalize().toString(),
                        filePath2.toAbsolutePath().normalize().toString(),
                        "stylish");
        assertEquals(
                DIFF_RESULT_STYLISH_FROM_YML_FILE.trim().replaceAll("\\s+", ""),
                diffResult.trim().replaceAll("\\s+", ""));
    }
}
