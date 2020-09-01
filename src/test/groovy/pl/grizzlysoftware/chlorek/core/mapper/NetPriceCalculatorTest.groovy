package pl.grizzlysoftware.chlorek.core.mapper

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class NetPriceCalculatorTest extends Specification {
    def "returns null for given null args"(netPrice, vat) {
        given:
            def m = new NetPriceCalculator()
        when:
            def output = m.apply(netPrice, vat)
        then:
            output == null
        where:
            netPrice | vat
            null     | 0
            0        | null
            null     | null
    }

    def "throws IllegalArgumentException if given vat is a negative number"() {
        given:
            def m = new NetPriceCalculator()
        when:
            m.apply(5, -1)
        then:
            thrown(IllegalArgumentException)
    }

    def "throws IllegalArgumentException if given vat is number greater than 1"() {
        given:
            def m = new NetPriceCalculator()
        when:
            m.apply(5, 5)
        then:
            thrown(IllegalArgumentException)
    }

    @Unroll
    def "calculates gross price properly"(grossPrice, vat, expectedOutput) {
        given:
            def m = new NetPriceCalculator()
        when:
            def output = m.apply(grossPrice, vat)
        then:
            output == expectedOutput
        where:
            grossPrice | vat  | expectedOutput
            12.3       | 0    | 12.3
            12.3       | 0.23 | 10
            -12.3      | 0.23 | -10
    }
}
