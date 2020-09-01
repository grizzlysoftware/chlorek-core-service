package pl.grizzlysoftware.chlorek.core.resolver;

import pl.grizzlysoftware.chlorek.core.model.Taggable;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface TagValueResolver<T> {
    T resolve(Taggable taggable);
}
