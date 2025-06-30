package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DiffEntry {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final StatusEnum status;
}
