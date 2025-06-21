package hexlet.code;

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
        case CHANGED -> {
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
      }
    }
    sb.append("}");
    return sb.toString();
  }
}
