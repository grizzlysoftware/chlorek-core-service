package pl.grizzlysoftware.chlorek.core.resolver;

import pl.grizzlysoftware.chlorek.core.model.Tag;

import java.util.function.Function;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface TagParser extends Function<String, Tag> {
    Tag parse(String tag);

    @Override
    default Tag apply(String s) {
        return parse(s);
    }
}
