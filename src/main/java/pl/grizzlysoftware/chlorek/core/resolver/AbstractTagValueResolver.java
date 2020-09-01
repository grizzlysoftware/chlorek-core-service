package pl.grizzlysoftware.chlorek.core.resolver;

import pl.grizzlysoftware.chlorek.core.model.Taggable;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public abstract class AbstractTagValueResolver<T> implements TagValueResolver<T> {

    protected final String tagName;

    public AbstractTagValueResolver(String tagName) {
        if(isBlank(tagName)) {
            throw new IllegalArgumentException("Given tagName cannot be null or blank");
        }
        this.tagName = tagName;
    }

    protected abstract T resolve(Taggable taggable, String tagName);

    @Override
    public final T resolve(Taggable taggable) {
        return resolve(taggable, tagName);
    }
}
