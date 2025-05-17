package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {

    @Option(
            names = {"-f", "--format"},
            description = "output format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish",
            paramLabel = "format"
    )
    private String format;

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Override
    public void run() {
        System.out.printf("Comparing files: %s and %s using '%s' format%n", filepath1, filepath2, format);
    }

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);

        String readFile1Path = "src/main/resources/file1.json";
        Path file1AbsPath = Paths.get(readFile1Path).toAbsolutePath().normalize();
        if (!Files.exists(file1AbsPath)) {
            throw new Exception("File '" + file1AbsPath + "' does not exist");
        }
        String file1Content = Files.readString(file1AbsPath);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parsedFile1Map
                = objectMapper.readValue(file1Content, new TypeReference<Map<String,Object>>(){});

        System.out.println(file1Content);
        System.out.println(parsedFile1Map);

        System.exit(exitCode);
    }
}
