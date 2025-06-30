package hexlet.code.formatters;

public class FormatterSelection {
    public static Formatter getFormatter(String format) {
        return switch (format) {
            case "stylish" -> new StylishFormat();
            case "plain" -> new PlainFormat();
            case "json" -> new JsonFormat();
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
