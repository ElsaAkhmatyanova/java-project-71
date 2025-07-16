package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class DifferTest {
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TestCase {
        private String file1;
        private String file2;
        private String format;
        private String expectedResultFile;
    }

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

    static Stream<TestCase> provideDiffTestCases() {
        return Stream.of(
                TestCase.builder()
                        .file1("fixtures/files/file1.json")
                        .file2("fixtures/files/file2.json")
                        .format("stylish")
                        .expectedResultFile("fixtures/expected_results/stylish_result_for_json_file.txt")
                        .build(),
                TestCase.builder()
                        .file1("fixtures/files/file1.json")
                        .file2("fixtures/files/file2.json")
                        .format("plain")
                        .expectedResultFile("fixtures/expected_results/plain_result_for_json_file.txt")
                        .build(),
                TestCase.builder()
                        .file1("fixtures/files/file1.json")
                        .file2("fixtures/files/file2.json")
                        .format("json")
                        .expectedResultFile("fixtures/expected_results/json_result_for_json_file.txt")
                        .build(),
                TestCase.builder()
                        .file1("fixtures/files/file1.yml")
                        .file2("fixtures/files/file2.yml")
                        .format("stylish")
                        .expectedResultFile("fixtures/expected_results/stylish_result_for_yml_file.txt")
                        .build());
    }

    @ParameterizedTest
    @MethodSource("provideDiffTestCases")
    @SneakyThrows
    void testGenerateDiff(TestCase testCase) {
        Path filePath1 = getResourseFilePath(testCase.file1);
        Path filePath2 = getResourseFilePath(testCase.file2);
        String expected = readFileToStringFromResources(testCase.expectedResultFile);
        String actual =
                Differ.generate(
                        filePath1.toAbsolutePath().normalize().toString(),
                        filePath2.toAbsolutePath().normalize().toString(),
                        testCase.format);
        assertEquals(expected.trim().replaceAll("\\s+", ""), actual.trim().replaceAll("\\s+", ""));
    }
}
