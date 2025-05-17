package hexlet.code;

import hexlet.code.parsers.JsonParser;

import java.util.*;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> map1 = JsonParser.parse(filepath1);
        Map<String, Object> map2 = JsonParser.parse(filepath2);

        List<DiffEntry> diffs = buildDiff(map1, map2);

        Formatter formatter = new FormatterStyle();
        return formatter.format(diffs);
    }

    private static List<DiffEntry> buildDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<DiffEntry> result = new ArrayList<>();

        for (String key : allKeys) {
            boolean inFirst = map1.containsKey(key);
            boolean inSecond = map2.containsKey(key);
            Object val1 = map1.get(key);
            Object val2 = map2.get(key);

            if (inFirst && !inSecond) {
                result.add(new DiffEntry(key, val1, null, DiffEntry.Status.REMOVED));
            } else if (!inFirst && inSecond) {
                result.add(new DiffEntry(key, null, val2, DiffEntry.Status.ADDED));
            } else {
                if (Objects.equals(val1, val2)) {
                    result.add(new DiffEntry(key, val1, val2, DiffEntry.Status.UNCHANGED));
                } else {
                    result.add(new DiffEntry(key, val1, val2, DiffEntry.Status.CHANGED));
                }
            }
        }
        return result;
    }
}
