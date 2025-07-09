package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DiffEntry {
    private final String key;
    private final StatusEnum status;
    private final Object oldValue;
    private final Object newValue;
}
