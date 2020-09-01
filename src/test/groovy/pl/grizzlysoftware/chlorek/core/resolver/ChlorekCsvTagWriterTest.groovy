package pl.grizzlysoftware.chlorek.core.resolver

import spock.lang.Specification

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class ChlorekCsvTagWriterTest extends Specification {
    def "writes uses same separator as parser"() {
        given:
            def parser = new ChlorekCsvTagParser()
            def writer = new ChlorekCsvTagWriter()
        when:
            def output = parser.separator == writer.separator
        then:
            output == true
    }
}
