package pl.grizzlysoftware.chlorek.core.util


import spock.lang.Specification

import java.util.function.Predicate

import static java.util.stream.Collectors.toList
import static pl.grizzlysoftware.commons.PredicateUtils.and

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class PredicateUtilsTest extends Specification {
    def "and returns true predicate if given collection is null or empty"(predicates) {
        given:
            def input = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
        when:
            def output = input.stream().filter(and(predicates)).collect(toList())
        then:
            output == input
        where:
            predicates << [null, []]
    }

    def "and returns combination of given predicates with AND operator"() {
        given:
            def input = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
            def predicates = [
                    { e -> e % 2 == 1 } as Predicate,
                    { e -> e * 2 >= 5 } as Predicate,
                    { e -> e + 5 >= 10 } as Predicate]
        when:
            def output = input.stream().filter(and(predicates)).collect(toList())
        then:
            output.size() == 6
            output.contains(5)
            output.contains(7)
            output.contains(9)
            output.contains(11)
            output.contains(13)
            output.contains(15)
    }
}
