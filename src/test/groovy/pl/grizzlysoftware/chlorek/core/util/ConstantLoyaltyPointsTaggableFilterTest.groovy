package pl.grizzlysoftware.chlorek.core.util

import pl.grizzlysoftware.chlorek.core.model.TaggableImpl
import spock.lang.Specification

import static java.util.stream.Collectors.toList
import static pl.grizzlysoftware.chlorek.core.model.TagRegistry.CONSTANT_LOYALTY_POINTS_TAG

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class ConstantLoyaltyPointsTaggableFilterTest extends Specification {
    def "return false for given Taggable with CONSTANT_LOYALTY_POINTS tag"() {
        given:
            def taggable = new TaggableImpl()
            taggable.addTag(CONSTANT_LOYALTY_POINTS_TAG as String)
            def filter = new ConstantLoyaltyPointsTaggableFilter()
        when:
            def output = filter.test(taggable)
        then:
            output == false
    }

    def "filters out Taggables with CONSTANT_LOYALTY_POINTS tag"() {
        given:
            def taggables = [new TaggableImpl([CONSTANT_LOYALTY_POINTS_TAG]),
                             new TaggableImpl([]),
                             new TaggableImpl([CONSTANT_LOYALTY_POINTS_TAG]),
                             new TaggableImpl(["TAG"]),
                             new TaggableImpl([CONSTANT_LOYALTY_POINTS_TAG]),
                             new TaggableImpl(["TAG"])]
            def filter = new ConstantLoyaltyPointsTaggableFilter()
        when:
            def output = taggables
                    .stream()
                    .filter(filter)
                    .collect(toList())
        then:
            output.size() == 3
            output.stream().filter({ e -> e.hasTag(CONSTANT_LOYALTY_POINTS_TAG) }).count() == 0
    }
}
