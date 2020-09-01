package pl.grizzlysoftware.chlorek.core.resolver

import pl.grizzlysoftware.chlorek.core.model.Taggable
import spock.lang.Specification

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class AbstractTagValueResolverTest extends Specification {
    static final RESOLVE_RESULT = "2 ARGUMENT RESOLVE"
    def "constructor throws IllegalArgumentException when given tagName is null or blank"(tagName) {
        when:
            new TestTagValueResolverTest(null)
        then:
            thrown(IllegalArgumentException)
        where:
            tagName << [null, "", " ", "      "]
    }

    def "resolve delegates invocation to bi argument resolve"() {
        given:
            def m = new TestTagValueResolverTest("TEST_TAG_NAME")
        when:
            def output = m.resolve(null)
        then:
            output == RESOLVE_RESULT
    }

    class TestTagValueResolverTest extends AbstractTagValueResolver {

        TestTagValueResolverTest(String tag) {
            super(tag)
        }

        @Override
        protected Object resolve(Taggable taggable, String tagName) {
            return RESOLVE_RESULT
        }
    }
}
