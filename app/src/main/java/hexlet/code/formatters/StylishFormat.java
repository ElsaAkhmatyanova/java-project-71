package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.List;

public class StylishFormat implements Formatter {
    @Override
    public String format(List<DiffEntry> entries) {
        StringBuilder result = new StringBuilder("{\n");

        for (DiffEntry entry : entries) {
            String key = entry.getKey();
            Object oldValue = entry.getOldValue();
            Object newValue = entry.getNewValue();

            switch (entry.getStatus()) {
                case UNCHANGED ->
                        result.append("    ").append(key).append(": ").append(stringify(oldValue)).append("\n");
                case UPDATED -> {
                    result.append("  - ").append(key).append(": ").append(stringify(oldValue)).append("\n");
                    result.append("  + ").append(key).append(": ").append(stringify(newValue)).append("\n");
                }
                case REMOVED ->
                        result.append("  - ").append(key).append(": ").append(stringify(oldValue)).append("\n");
                case ADDED ->
                        result.append("  + ").append(key).append(": ").append(stringify(newValue)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
