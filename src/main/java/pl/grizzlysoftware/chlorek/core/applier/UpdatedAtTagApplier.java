package pl.grizzlysoftware.chlorek.core.applier;

import pl.grizzlysoftware.chlorek.core.model.Tag;
import pl.grizzlysoftware.chlorek.core.model.Taggable;

import java.util.function.Consumer;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static pl.grizzlysoftware.chlorek.core.model.TagRegistry.UPDATED_AT_TAG;
import static pl.grizzlysoftware.commons.TimeUtils.nowUtc;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class UpdatedAtTagApplier implements Consumer<Taggable> {
    @Override
    public void accept(Taggable taggable) {
        taggable.removeTag(UPDATED_AT_TAG);
        taggable.addTag(new Tag(UPDATED_AT_TAG, ISO_DATE_TIME.format(nowUtc())));
    }
}
