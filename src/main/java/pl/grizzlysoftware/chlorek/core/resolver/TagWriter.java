package pl.grizzlysoftware.chlorek.core.resolver;

import pl.grizzlysoftware.chlorek.core.model.Tag;

import java.util.function.Function;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface TagWriter extends Function<Tag, String> {
    String write(Tag tag);

    @Override
    default String apply(Tag tag) {
        return write(tag);
    }
}
