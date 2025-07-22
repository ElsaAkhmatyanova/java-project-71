package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatterSelection;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        if (format == null || format.isEmpty()) return generate(filepath1, filepath2);
        String content1 = Files.readString(Path.of(filepath1));
        String contentType1 = filepath1.substring(filepath1.lastIndexOf('.') + 1);

        String content2 = Files.readString(Path.of(filepath2));
        String contentType2 = filepath2.substring(filepath2.lastIndexOf('.') + 1);

        Map<String, Object> data1 = Parser.parseFromString(content1, contentType1);
        Map<String, Object> data2 = Parser.parseFromString(content2, contentType2);

        List<DiffEntry> diffEntryList = DiffBuilder.getDiff(data1, data2);
        return getFormattedDiff(diffEntryList, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static String getFormattedDiff(List<DiffEntry> diffEntryList, String format)
            throws Exception {
        Formatter formatter = FormatterSelection.getFormatter(format);
        return formatter.format(diffEntryList);
    }
}
