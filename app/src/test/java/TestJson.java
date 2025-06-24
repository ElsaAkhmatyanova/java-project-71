import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.Differ;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.junit.jupiter.api.Test;

public class TestJson {
    @Test
    void testFlatJsonComparison() throws Exception {
        Path temp1 = Files.createTempFile("file1", ".json");
        Path temp2 = Files.createTempFile("file2", ".json");

        try (InputStream is1 = getClass().getClassLoader().getResourceAsStream("file1.json"); InputStream is2 = getClass().getClassLoader().getResourceAsStream("file2.json")) {
            Files.copy(is1, temp1, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(is2, temp2, StandardCopyOption.REPLACE_EXISTING);
        }

        String actual = Differ.generate(temp1.toString(), temp2.toString());
        String expected = """
                {
                  "age": {
                    "oldValue": 30,
                    "newValue": 31
                  }
                }
                """.trim();

        assertThat(actual.trim()).isEqualTo(expected);
    }
}
