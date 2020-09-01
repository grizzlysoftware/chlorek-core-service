package pl.grizzlysoftware.chlorek.core.resolver

import pl.grizzlysoftware.chlorek.core.model.NullTag
import pl.grizzlysoftware.chlorek.core.model.Tag
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class CsvTagWriterTest extends Specification {
    def "throws IllegalArgumentException when given separator is null or empty"(separator) {
        when:
            def m = new CsvTagWriter(separator)
        then:
            thrown(IllegalArgumentException)
        where:
            separator << [null, ""]
    }

    @Unroll
    def "writes tag from given csv string "(tagName, tagValue, expectedOutput) {
        given:
            def tag = new Tag(tagName, tagValue)
            def m = new CsvTagWriter(':')
        when:
            def output = m.write(tag)
        then:
            output == expectedOutput
        where:
            tagName | tagValue      || expectedOutput
            "tag_1" | null          || "tag_1"
            "tag_1" | ""            || "tag_1"
            "tag_1" | " "           || "tag_1"
            "tag_1" | "       "     || "tag_1"
            "tag_1" | "tag_1_value" || "tag_1:tag_1_value"
    }

    def "returns null if NullTag is given"() {
        given:
            def m = new CsvTagWriter(':')
        when:
            def output = m.write(NullTag.INSTANCE)
        then:
            output == null
    }
}
