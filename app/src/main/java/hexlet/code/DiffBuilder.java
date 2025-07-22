package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class DiffBuilder {
    public static List<DiffEntry> getDiff(Map<String, Object> data1, Map<String, Object> data2) throws Exception {
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
        return result;
    }
}
