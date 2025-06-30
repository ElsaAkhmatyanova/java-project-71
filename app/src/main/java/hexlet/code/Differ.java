package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.JsonFormat;
import java.util.*;

public class Differ {
    public static List<DiffEntry> generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> data1 = Parser.parseFromFile(filepath1);
        Map<String, Object> data2 = Parser.parseFromFile(filepath2);

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        List<DiffEntry> result = new ArrayList<>();

        for (String key : allKeys) {
            Object val1 = data1.get(key);
            Object val2 = data2.get(key);

            if (!data2.containsKey(key)) {
                result.add(new DiffEntry(key, StatusEnum.REMOVED.name(), val1, null));
            } else if (!data1.containsKey(key)) {
                result.add(new DiffEntry(key, StatusEnum.ADDED.name(), null, val2));
            } else if (!Objects.equals(val1, val2)) {
                result.add(new DiffEntry(key, StatusEnum.ADDED.name(), val1, val2));
            } else {
                result.add(new DiffEntry(key, StatusEnum.UPDATED.name(), val1, null));
            }
        }
        return result;
    }
}
