package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatterSelection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        List<DiffEntry> diffEntryList = getDiff(filepath1, filepath2);
        return getFormattedDiff(diffEntryList, format);
    }

    private static List<DiffEntry> getDiff(String filepath1, String filepath2) throws Exception {
        System.out.println("Diff for files: " + filepath1 + " " + filepath2);
        Map<String, Object> data1 = Parser.parseFromFile(filepath1);
        Map<String, Object> data2 = Parser.parseFromFile(filepath2);
        System.out.println("data1: " + data1);
        System.out.println("data2: " + data2);

        TreeSet<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        List<DiffEntry> result = new ArrayList<>();

        for (String key : allKeys) {
            Object val1 = data1.get(key);
            Object val2 = data2.get(key);
            if (!data1.containsKey(key) && data2.containsKey(key)) {
                result.add(new DiffEntry(key, StatusEnum.ADDED, val1, val2));
            } else if (data1.containsKey(key) && !data2.containsKey(key)) {
                result.add(new DiffEntry(key, StatusEnum.REMOVED, val1, val2));
            } else if (!Objects.equals(val1, val2)) {
                result.add(new DiffEntry(key, StatusEnum.UPDATED, val1, val2));
            } else {
                result.add(new DiffEntry(key, StatusEnum.UNCHANGED, val1, val2));
            }
        }
        System.out.println("Diff size: " + result.size());
        return result;
    }

    private static String getFormattedDiff(List<DiffEntry> diffEntryList, String format) throws Exception {
        System.out.println("Selected format: " + format);
        Formatter formatter = FormatterSelection.getFormatter(format);
        return formatter.format(diffEntryList);
    }
}
