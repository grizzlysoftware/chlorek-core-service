package pl.grizzlysoftware.chlorek.core.mapper

import pl.grizzlysoftware.chlorek.core.model.ContainerType
import spock.lang.Specification
import spock.lang.Unroll

import static pl.grizzlysoftware.chlorek.core.model.ContainerType.IRRELEVANT

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
class StringToContainerTypeMapperTest extends Specification {
    def "returns IRRELEVANT if ContainerType for given String argument was not found"(stringContainerType) {
        given:
            def m = new StringToContainerTypeMapper()
        when:
            def output = m.apply(stringContainerType)
        then:
            output == IRRELEVANT
        where:
            stringContainerType << [null, "", "x", "IRRELEVANTS"]
    }

    @Unroll
    def "returns ContainerType for any given String argument matching ContainerType name"(stringContainerType, expectedContainerType) {
        given:
            def m = new StringToContainerTypeMapper()
        when:
            def output = m.apply(stringContainerType)
        then:
            output == expectedContainerType
        where:
            stringContainerType << Arrays.stream(ContainerType.values()).map({ e-> e.name()}).collect()
            expectedContainerType << ContainerType.values()
    }
}
