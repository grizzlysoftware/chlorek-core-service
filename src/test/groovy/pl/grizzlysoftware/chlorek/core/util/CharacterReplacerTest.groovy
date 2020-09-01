package pl.grizzlysoftware.chlorek.core.util

import pl.grizzlysoftware.commons.CharacterReplacer
import spock.lang.Specification
import spock.lang.Unroll

class CharacterReplacerTest extends Specification {
    def "throws NullPointerException when given args are null"() {
        when:
            new CharacterReplacer(null)
        then:
            thrown(NullPointerException)
    }

    @Unroll
    def "replaces chars"(String input, String expectedOutput) {
        given:
            def m = new CharacterReplacer(Map.of(
                    "a", (char) 'c',
                    "b", (char) 'k',
                    "e", (char) 'f'
            ))
        when:
            def output = m.replace(input)
        then:
            output == expectedOutput
        where:
            input                | expectedOutput
            null                 | null
            "abe"                | "ckf"
            "aaaabbee"           | "cccckkff"
            "nothing to r3pl4c3" | "nothing to r3pl4c3"

    }
}
