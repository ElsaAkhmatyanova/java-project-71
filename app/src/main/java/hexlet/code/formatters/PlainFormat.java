package hexlet.code.formatters;

import hexlet.code.DiffEntry;
import java.util.List;

public final class PlainFormat implements Formatter {
    @Override
    public String format(List<DiffEntry> diffEntries) {
        StringBuilder result = new StringBuilder();

        for (DiffEntry entry : diffEntries) {
            String key = entry.getKey();
            Object oldValue = entry.getOldValue();
            Object newValue = entry.getNewValue();

            switch (entry.getStatus()) {
                case REMOVED -> result.append("Property '").append(key).append("' was removed\n");
                case ADDED -> {
                    result
                            .append("Property '")
                            .append(key)
                            .append("' was added with value: ")
                            .append(stringify(newValue))
                            .append("\n");
                }
                case UPDATED -> {
                    result
                            .append("Property '")
                            .append(key)
                            .append("' was updated. From ")
                            .append(stringify(oldValue))
                            .append(" to ")
                            .append(stringify(newValue))
                            .append("\n");
                }
                case UNCHANGED -> {
                    // no need action
                }
                default -> throw new IllegalStateException("Unknown status: " + entry.getStatus());
            }
        }

        return result.toString().trim();
    }

    private String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String || value instanceof Character) {
            return "'" + value + "'";
        }
        if (value instanceof List || value instanceof java.util.Map) {
            return "[complex value]";
        }
        return value.toString();
    }
}
