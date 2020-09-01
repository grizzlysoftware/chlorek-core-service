package pl.grizzlysoftware.chlorek.core.util;

import pl.grizzlysoftware.chlorek.core.model.SpecialItem;
import pl.grizzlysoftware.chlorek.core.model.Taggable;

import java.util.function.Predicate;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public class TaggableNotASpecialItemFilter<T extends Taggable> implements Predicate<T> {

    @Override
    public boolean test(T taggable) {
        if (taggable instanceof SpecialItem) {
            var si = (SpecialItem) taggable;
            var isSpecial = si.isSpecial();
            return !isSpecial;
        }
        return true;
    }
}
