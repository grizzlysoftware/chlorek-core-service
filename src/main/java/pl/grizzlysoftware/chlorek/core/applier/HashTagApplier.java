package pl.grizzlysoftware.chlorek.core.applier;

import pl.grizzlysoftware.chlorek.core.model.Tag;
import pl.grizzlysoftware.chlorek.core.model.Taggable;

import java.util.function.Consumer;

import static pl.grizzlysoftware.chlorek.core.model.TagRegistry.HASH_TAG;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class HashTagApplier implements Consumer<Taggable> {
    @Override
    public void accept(Taggable product) {
        product.removeTag(HASH_TAG);
        product.addTag(new Tag(HASH_TAG, product.hashCode()+""));
    }
}
