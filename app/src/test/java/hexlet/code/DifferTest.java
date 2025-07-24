package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    private static String stylishExpectedForJson;
    private static String plainExpectedForJson;
    private static String jsonExpectedForJson;
    private static String stylishExpectedForYml;
    private static String plainExpectedForYml;
    private static String jsonExpectedForYml;

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

    private String getFixturePathString(String fileName) {
        return getResourseFilePath("fixtures/files/" + fileName).toAbsolutePath().normalize().toString();
    }

    @BeforeAll
    static void setUp() {
        stylishExpectedForJson = readFile("fixtures/expected_results/stylish_result_for_json.txt");
        plainExpectedForJson = readFile("fixtures/expected_results/plain_result_for_json.txt");
        jsonExpectedForJson = readFile("fixtures/expected_results/json_result_for_json.txt");
        stylishExpectedForYml = readFile("fixtures/expected_results/stylish_result_for_yml.txt");
        plainExpectedForYml = readFile("fixtures/expected_results/plain_result_for_yml.txt");
        jsonExpectedForYml = readFile("fixtures/expected_results/json_result_for_yml.txt");
    }

    static Stream<Arguments> testCaseProvider() {
        return Stream.of(
                Arguments.of("json", stylishExpectedForJson, plainExpectedForJson, jsonExpectedForJson),
                Arguments.of("yml", stylishExpectedForYml, plainExpectedForYml, jsonExpectedForYml)
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void testGenerateDiffWithFormats(String format, String stylishExpected, String plainExpected, String jsonExpected)
            throws Exception {
        String filePath1 = getFixturePathString("file1." + format);
        String filePath2 = getFixturePathString("file2." + format);

        assertThat(Differ.generate(filePath1, filePath2, "stylish")).isEqualTo(stylishExpected);
        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(stylishExpected);
        assertThat(Differ.generate(filePath1, filePath2, "plain")).isEqualTo(plainExpected);
        assertThat(Differ.generate(filePath1, filePath2, "json")).isEqualTo(jsonExpected);
    }
}
