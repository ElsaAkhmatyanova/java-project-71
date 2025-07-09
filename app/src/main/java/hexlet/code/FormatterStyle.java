package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.util.List;

public class FormatterStyle implements Formatter {
    @Override
    public String format(List<DiffEntry> diffEntries) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (DiffEntry entry : diffEntries) {
            switch (entry.getStatus()) {
                case UNCHANGED ->
                        sb.append("    ")
                                .append(entry.getKey())
                                .append(": ")
                                .append(entry.getNewValue())
                                .append("\n");
                case REMOVED ->
                        sb.append("  - ")
                                .append(entry.getKey())
                                .append(": ")
                                .append(entry.getOldValue())
                                .append("\n");
                case ADDED ->
                        sb.append("  + ")
                                .append(entry.getKey())
                                .append(": ")
                                .append(entry.getNewValue())
                                .append("\n");
                case UPDATED -> {
                    sb.append("  - ")
                            .append(entry.getKey())
                            .append(": ")
                            .append(entry.getOldValue())
                            .append("\n");
                    sb.append("  + ")
                            .append(entry.getKey())
                            .append(": ")
                            .append(entry.getNewValue())
                            .append("\n");
                }
                default -> throw new IllegalStateException("Unknown status: " + entry.getStatus());
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
