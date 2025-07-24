package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static final String JSON_FILE_PATH_1 = "fixtures/files/file1.json";
    private static final String JSON_FILE_PATH_2 = "fixtures/files/file2.json";
    private static final String YML_FILE_PATH_1 = "fixtures/files/file1.yml";
    private static final String YML_FILE_PATH_2 = "fixtures/files/file2.yml";

    private static String stylishExpectedForJson;
    private static String plainExpectedForJson;
    private static String jsonExpectedForJson;
    private static String stylishExpectedForYml;

    private static String readFile(String resourcesFilePath) {
        try {
            return Files.readString(getResourseFilePath(resourcesFilePath));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Path getResourseFilePath(String path) {
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
            if (resource == null) {
                throw new NullPointerException("URL must be not null!");
            }
            return Path.of(resource.toURI());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @BeforeAll
    static void setUp() {
        stylishExpectedForJson = readFile("fixtures/expected_results/stylish_result_for_json_file.txt");
        plainExpectedForJson = readFile("fixtures/expected_results/plain_result_for_json_file.txt");
        jsonExpectedForJson = readFile("fixtures/expected_results/json_result_for_json_file.txt");
        stylishExpectedForYml = readFile("fixtures/expected_results/stylish_result_for_yml_file.txt");
    }

    static Stream<Arguments> formatProvider() {
        return Stream.of(
                Arguments.of(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "stylish", stylishExpectedForJson),
                Arguments.of(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "plain", plainExpectedForJson),
                Arguments.of(JSON_FILE_PATH_1, JSON_FILE_PATH_2, "json", jsonExpectedForJson),
                Arguments.of(JSON_FILE_PATH_1, JSON_FILE_PATH_2, null, stylishExpectedForJson),
                Arguments.of(YML_FILE_PATH_1, YML_FILE_PATH_2, "stylish", stylishExpectedForYml)
        );
    }

    @ParameterizedTest
    @MethodSource("formatProvider")
    void testGenerateDiffWithFormats(String path1, String path2, String format, String expected) throws Exception {
        String actual = Differ.generate(
                getResourseFilePath(path1).toAbsolutePath().normalize().toString(),
                getResourseFilePath(path2).toAbsolutePath().normalize().toString(),
                format
        );
        assertEquals(expected.trim(), actual.trim());
    }
}
