package pl.grizzlysoftware.chlorek.core.resolver;

import org.apache.commons.lang3.StringUtils;
import pl.grizzlysoftware.chlorek.core.model.NullTag;
import pl.grizzlysoftware.chlorek.core.model.Tag;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class CsvTagParser implements TagParser {
    protected String separator;

    public CsvTagParser(String separator) {
        if (StringUtils.isEmpty(separator)) {
            throw new IllegalArgumentException("separator cannot be null or empty string");
        }
        this.separator = separator;
    }

    @Override
    public Tag parse(String tag) {
        if (tag == null || tag.isBlank()) {
            return NullTag.INSTANCE;
        }
        var parts = tag.split(separator);
        if (parts.length < 1) {
            return NullTag.INSTANCE;
        } else if (parts.length == 1) {
            return new Tag(parts[0]);
        } else {
            return new Tag(parts[0], parts[1]);
        }
    }
}
