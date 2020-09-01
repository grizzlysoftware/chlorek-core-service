package pl.grizzlysoftware.chlorek.core.resolver

import pl.grizzlysoftware.chlorek.core.model.NullTag
import spock.lang.Specification

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class CsvTagParserTest extends Specification {
    def "throws IllegalArgumentException when given separator is null or empty"(separator) {
        when:
            def m = new CsvTagParser(separator)
        then:
            thrown(IllegalArgumentException)
        where:
            separator << [null, ""]
    }

    def "parses tag from given csv string"() {
        given:
            def tagName = "tag_1"
            def tagValue = "tag_1_value"
            def tag = "${tagName}:${tagValue}"
            def m = new CsvTagParser(':')
        when:
            def output = m.parse(tag)
        then:
            output.name == tagName
            output.value == tagValue
    }

    def "ignores additional values"() {
        given:
            def tagName = "tag_1"
            def tagValue = "tag_1_value"
            def tag = "${tagName}:${tagValue}:some stuff here:additional stuff here"
            def m = new CsvTagParser(':')
        when:
            def output = m.parse(tag)
        then:
            output.name == tagName
            output.value == tagValue
    }

    def "returns NullTag instance if value is null or empty"(tag) {
        given:
            def m = new CsvTagParser(':')
        when:
            def output = m.parse(tag)
        then:
            output instanceof NullTag
        where:
            tag << [null, "", " ", "        "]
    }

    def "returns NullTag instance if value does not contain tag name"(tag) {
        given:
            def m = new CsvTagParser(':')
        when:
            def output = m.parse(tag)
        then:
            output instanceof NullTag
        where:
            tag << ["", ":", ":"]
    }
}
