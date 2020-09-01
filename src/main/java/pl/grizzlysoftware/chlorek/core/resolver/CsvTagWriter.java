package pl.grizzlysoftware.chlorek.core.resolver;

import org.apache.commons.lang3.StringUtils;
import pl.grizzlysoftware.chlorek.core.model.NullTag;
import pl.grizzlysoftware.chlorek.core.model.Tag;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class CsvTagWriter implements TagWriter {
    protected final String separator;

    public CsvTagWriter(String separator) {
        if (StringUtils.isEmpty(separator)) {
            throw new IllegalArgumentException("separator cannot be null or empty string");
        }
        this.separator = separator;
    }

    @Override
    public String write(Tag tag) {
        if (tag == null || tag instanceof NullTag) {
            return null;
        }
        var out = new StringBuilder()
                .append(tag.name);

        if (!isBlank(tag.value)) {
            out
                    .append(separator)
                    .append(tag.value);
        }

        return out.toString();
    }
}
