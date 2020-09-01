package pl.grizzlysoftware.chlorek.core.util

import pl.grizzlysoftware.chlorek.core.model.SpecialItem
import pl.grizzlysoftware.chlorek.core.model.SpecialItemImpl
import pl.grizzlysoftware.chlorek.core.model.Tag
import pl.grizzlysoftware.chlorek.core.model.TaggableImpl
import spock.lang.Specification

import static pl.grizzlysoftware.chlorek.core.model.TagRegistry.SPECIAL_ITEM_TAG_PREFIX

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class TaggableNotASpecialItemFilterTest extends Specification {
    def "Filters out Taggables that are special items"() {
        given:
            def filter = new TaggableNotASpecialItemFilter()
            def input = [
                    specialItem([specialTag(1), specialTag(2)]),
                    specialItem([tag("tag-1"), tag("tag-2")]),
                    specialItem([tag("tag-3"), tag("tag-4")]),
                    specialItem([tag("tag-5"), specialTag(3)]),
                    taggable(),
                    taggable()
            ]

        when:
            def output = input.stream().filter(filter).collect()
        then:
            output.size() == 4
            output
                    .stream()
                    .filter({ e -> e instanceof SpecialItem})
                    .map({ e -> (SpecialItem) e})
                    .filter( { e -> e.isSpecial()})
                    .count() == 0
    }

    def "taggable"() {
        return new TaggableImpl()
    }

    def "specialItem"(tags) {
        return new SpecialItemImpl(tags)
    }

    def "tag"(name) {
        return new Tag(name)
    }

    def "specialTag"(suffix) {
        return new Tag("${SPECIAL_ITEM_TAG_PREFIX}-tag-${suffix}")
    }
}
