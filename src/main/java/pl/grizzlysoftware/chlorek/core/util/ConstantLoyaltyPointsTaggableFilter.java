package pl.grizzlysoftware.chlorek.core.util;

import pl.grizzlysoftware.chlorek.core.model.TagRegistry;
import pl.grizzlysoftware.chlorek.core.model.Taggable;

import java.util.function.Predicate;

import static pl.grizzlysoftware.chlorek.core.model.TagRegistry.CONSTANT_LOYALTY_POINTS_TAG;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class ConstantLoyaltyPointsTaggableFilter<T extends Taggable> implements Predicate<T> {

    @Override
    public boolean test(T taggable) {
        return !taggable.hasTag(TagRegistry.CONSTANT_LOYALTY_POINTS_TAG);
    }
}
